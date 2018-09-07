package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.LoginAccessToken;
import club.ragdollhouse.pojo.LoginCode;
import club.ragdollhouse.service.LoginService;
import club.ragdollhouse.util.DateUtil;
import club.ragdollhouse.util.Md5;
import com.alibaba.fastjson.JSON;
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
public class ResultCheck {

    static String SESSION_OUT_TIME = "0";
    static String CODE_IS_NULL = "10";
    static String CODE_IS_WRONG = "11";
    static String CODE_IS_RIGHT = "12";
    static String USEREMAIL_IS_NULL = "20";
    static String USEREMAIL_IS_WRONG = "21";
    static String USEREMAIL_IS_RIGHT = "22";
    static String PWD_IS_NULL = "30";
    static String PWD_IS_WRONG = "31";
    static String PWD_IS_RIGHT = "32";

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/ResultServlet", method = RequestMethod.POST)
    public void resultCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String validateC = (String) request.getSession().getAttribute("checkcode");
        //从前台传来的用户输入的验证码
        String veryCode = request.getParameter("c");
        String userEmail = request.getParameter("u");
        String passWord = request.getParameter("p");
        String remember_flag = request.getParameter("r");
        LoginCode loginCode = new LoginCode();
        PrintWriter out = response.getWriter();
        //第一步验证码要对
        if (validateC != null) {
            String SESSION_IN_TIME = "99";
            loginCode.setSessionval(SESSION_IN_TIME);
            if (veryCode == null || "".equals(veryCode)) {
                loginCode.setCode(CODE_IS_NULL);
            } else {
                //if (validateC.equals(veryCode)) {
                //忽略大小写处理：
                if (validateC.equalsIgnoreCase(veryCode)) {
                    loginCode.setCode(CODE_IS_RIGHT);
                } else {
                    loginCode.setCode(CODE_IS_WRONG);
                }
            }
        } else {
            //session的有效时间是占用cpu的，我们不去延长时间，前端仅仅判断失效然后刷新
            loginCode.setSessionval(SESSION_OUT_TIME);
        }
        //用户名验证
        if (userEmail == null || "".equals(userEmail)) {
            loginCode.setUsername(USEREMAIL_IS_NULL);
        } else {
            if (loginService.UserEmailExists(userEmail) != null && loginService.UserEmailExists(userEmail).equals("1")) {
                loginCode.setUsername(USEREMAIL_IS_RIGHT);
            } else {
                loginCode.setUsername(USEREMAIL_IS_WRONG);
            }
        }
        //用户密码验证
        if (passWord == null || "".equals(passWord)) {
            loginCode.setPassword(PWD_IS_NULL);
        } else {
            if (loginService.UserPwsCheck(userEmail, passWord) != null && loginService.UserPwsCheck(userEmail, passWord).equals("1")) {
                loginCode.setPassword(PWD_IS_RIGHT);
            } else {
                loginCode.setPassword(PWD_IS_WRONG);
            }
        }

        //登录验证正确后，存储用户姓名，登录taken，失效时间到浏览器cookie中
        if (loginCode.getCode().equals(CODE_IS_RIGHT) && loginCode.getPassword().equals(PWD_IS_RIGHT)
                && loginCode.getUsername().equals(USEREMAIL_IS_RIGHT)) {

            LoginAccessToken loginAccessToken = loginService.loginAccess(userEmail);
            //token的生成是email+时间戳的md5加密字符串
            String token = Md5.textToMD5L32(userEmail+ DateUtil.timeStamp());
            //昵称+token添加到浏览器cookie中
            response.addCookie(UserPasCookie("nickname",loginAccessToken.getAppname(),3));
            response.addCookie(UserPasCookie("buildtoken",token,3));
            //设置完之后数据入库
            loginAccessToken.setTaken(token);
            //设置记住登录状态数据3天时效
            loginAccessToken.setTaken_time(DateUtil.afterDate(3));
            loginService.loginAccessInsert(loginAccessToken);
        }

        //记住账号密码
        if (remember_flag.equals("1")) {
            if (loginCode.getUsername().equals(USEREMAIL_IS_RIGHT)) {
                response.addCookie(UserPasCookie("username", userEmail,7));
            }
            if (loginCode.getPassword().equals(PWD_IS_RIGHT)) {
                response.addCookie(UserPasCookie("pswd", passWord,7));
            }
        }

        //是否清除cookie(登录页“记住我取消按钮”)
        if (remember_flag.equals("2")) {
            Cookie delete1 = new Cookie("username", null);//假如要删除名称为username的Cookie
            delete1.setMaxAge(0); //立即删除型
            delete1.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
            response.addCookie(delete1);
            Cookie delete2 = new Cookie("pswd", null);
            delete2.setMaxAge(0);
            delete2.setPath("/");
            response.addCookie(delete2);
        }

        String l = JSON.toJSONString(loginCode);
        out.println(l);
        out.flush();
        out.close();
    }

    /**
     * 设置cookie
     */
    private Cookie UserPasCookie(String key, String value,int expiryday) {
        //创建cookie1，为其指定键名是userName，值是输入的用户名。
        Cookie cookie1 = new Cookie(key, value);
        //指定过期几天。
        cookie1.setMaxAge(60 * 60 * 24 * expiryday);
        return cookie1;
    }
}
