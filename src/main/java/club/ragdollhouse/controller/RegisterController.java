package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.RegisterCode;
import club.ragdollhouse.service.LoginService;
import club.ragdollhouse.service.MailSendService;
import club.ragdollhouse.util.DateUtil;
import club.ragdollhouse.util.Md5;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@RestController
public class RegisterController {

    static Logger logger = Logger.getLogger(RegisterController.class);

    //后期这些常量值我会统一规划到枚举值中
    static String SESSION_OUT_TIME = "0";//session超时
    static String SESSION_IN_TIME = "99";//session有效
    static String CODE_IS_NULL = "10";//验证码为空
    static String CODE_IS_WRONG = "11";//验证码错误
    static String CODE_IS_RIGHT = "12";//验证码正确
    static String USEREMAIL_IS_WRONG = "21";//用户登录账号已经注册，错误
    static String USEREMAIL_IS_RIGHT = "22";//用户登录账号未注册，可以注册
    static String PWD_IS_NULL = "30";//密码为空（密码结构校验前端写）
    static String NICKNAME_IS_WRONG = "31";//昵称已经存在，错误
    static String NICKNAME_IS_RIGHT = "32";//昵称不存在，可以注册

    @Autowired
    LoginService loginService;

    @Autowired
    MailSendService mailSendService;

    @RequestMapping(value = "/RegisterCheck", method = RequestMethod.POST)
    public void RegisterCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String validateC = (String) request.getSession().getAttribute("checkcode");
        //从前台传来的用户输入的验证码
        String veryCode = request.getParameter("c");
        String userEmail = request.getParameter("u");
        String passWord = request.getParameter("p");
        String sex = request.getParameter("s");
        String nickName = request.getParameter("n");
        RegisterCode registerCode = new RegisterCode();
        PrintWriter out = response.getWriter();
        //第一步验证码要对
        if (validateC != null) {
            registerCode.setSessionval(SESSION_IN_TIME);
            if (veryCode == null || "".equals(veryCode)) {
                registerCode.setCode(CODE_IS_NULL);
            } else {
                //忽略大小写处理：
                if (!validateC.equalsIgnoreCase(veryCode)) {
                    registerCode.setCode(CODE_IS_WRONG);
                } else {
                    registerCode.setCode(CODE_IS_RIGHT);
                }
            }
        } else {
            //session的有效时间是占用server资源的，我们不去延长时间，前端仅仅判断失效然后刷新
            registerCode.setSessionval(SESSION_OUT_TIME);
        }

        //用户名验证
        if (loginService.UserEmailExists(userEmail) == null) {
            registerCode.setUsername(USEREMAIL_IS_RIGHT);
        } else {
            registerCode.setUsername(USEREMAIL_IS_WRONG);
        }
        //昵称验证
        if (loginService.nickNameExists(nickName) == null) {
            registerCode.setNickname(NICKNAME_IS_RIGHT);
        } else {
            registerCode.setNickname(NICKNAME_IS_WRONG);
        }

        //如果校验成功，我们就把注册信息入库，并且发送到邮箱进行验证
        if(registerCode.getNickname() == NICKNAME_IS_RIGHT && registerCode.getUsername() == USEREMAIL_IS_RIGHT){
            //生成唯一激活码（后期我们会定时清理失效的激活码）
            String code = UUID.randomUUID().toString();
            //注册码失效时间
            String expiryDate = DateUtil.afterDate(1);
            //MD5加密后的密码
            String md5Pwd = Md5.textToMD5L32(passWord);
            RegisterCode rg = new RegisterCode();
            rg.setNickname(nickName);
            rg.setUsername(userEmail);
            rg.setCode(code);
            rg.setPassword(passWord);
            rg.setSex(sex);
            rg.setMd5pwd(md5Pwd);
            rg.setCodeefftime(expiryDate);
            loginService.RegisterInfInsert(rg);
            mailSendService.sendRegistEmail(userEmail,code);
        }

        //把注册的校验状态回传给页面
        String statu = JSON.toJSONString(registerCode);
        out.println(statu);
        out.flush();
        out.close();
    }

}
