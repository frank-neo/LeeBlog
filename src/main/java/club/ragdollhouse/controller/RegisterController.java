package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.LoginAccessToken;
import club.ragdollhouse.pojo.RegisterCode;
import club.ragdollhouse.service.LoginService;
import club.ragdollhouse.util.DateUtil;
import club.ragdollhouse.util.Md5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class RegisterController {

    static Logger logger = Logger.getLogger(RegisterController.class);

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

    @RequestMapping(value = "/RegisterCheck", method = RequestMethod.POST)
    public void RegisterCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String validateC = (String) request.getSession().getAttribute("checkcode");
        //从前台传来的用户输入的验证码
        String veryCode = request.getParameter("c");
        String userEmail = request.getParameter("u");
        String passWord = request.getParameter("p");
        String remember_flag = request.getParameter("r");
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

        //注册直接登录，其实就是存储登录状态
        if (remember_flag.equals("1")) {
            if (registerCode.getUsername().equals(USEREMAIL_IS_RIGHT) &&
                    registerCode.getNickname().equals(NICKNAME_IS_RIGHT) &&
                    registerCode.getCode().equals(CODE_IS_RIGHT)) {
                String token = Md5.textToMD5L32(userEmail + DateUtil.timeStamp());
                //昵称+token添加到浏览器cookie中
                LoginAccessToken loginAccessToken = loginService.loginAccess(userEmail);
                //token的生成是email+时间戳的md5加密字符串
                response.addCookie(UserPasCookie("nickname", loginAccessToken.getAppname(), 3));
                response.addCookie(UserPasCookie("buildtoken", token, 3));
                //设置完之后数据入库
                loginAccessToken.setTaken(token);
                //设置记住登录状态数据3天时效（数据库sql我减了一天也就是2天）
                loginAccessToken.setTaken_time(DateUtil.afterDate(3));
                loginService.loginAccessInsert(loginAccessToken);
                //注册信息入库

            }
        }
    }


    /**
     * 设置cookie
     */
    private Cookie UserPasCookie(String key, String value, int expiryday) {
        //创建cookie1，为其指定键名是userName，值是输入的用户名。
        Cookie cookie1 = new Cookie(key, value);
        //指定过期几天。
        cookie1.setMaxAge(60 * 60 * 24 * expiryday);
        return cookie1;
    }
}
