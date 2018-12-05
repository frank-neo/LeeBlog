package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.LoginAccessToken;
import club.ragdollhouse.pojo.UserInf;
import club.ragdollhouse.service.LoginService;
import club.ragdollhouse.service.MailSendService;
import club.ragdollhouse.util.DateUtil;
import club.ragdollhouse.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 登录注册页面跳转控制器
 * 邮箱链接注册跳转页面
 */
@Controller
public class LoginController {

    @Autowired
    MailSendService mailSendService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login2";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register2";
    }


    //退出登录跳转
    @RequestMapping(value = "/outlogin", method = RequestMethod.GET)
    public void outlogin(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam Map<String,String> paramMap) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        Cookie delete1 = new Cookie("buildtoken", null);//假如要删除名称为username的Cookie
        delete1.setMaxAge(0); //立即删除型
        delete1.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        response.addCookie(delete1);
        Cookie delete2 = new Cookie("nickname", null);
        delete2.setMaxAge(0);
        delete2.setPath("/");
        //数据库删除记录
        loginService.outOfLogin(paramMap.get("nickname"),paramMap.get("buildtoken"));
        response.addCookie(delete2);
        response.sendRedirect("/");
    }

    //邮箱链接激活接口
    @RequestMapping(value = "/activateRegister", method = RequestMethod.GET)
    public String RegisterStatu(@RequestParam Map<String, Object> paramMap,
                                HttpServletRequest request, HttpServletResponse response) {
        String code = paramMap.get("code").toString();
        if (mailSendService.activeRegisterStatu(code) == 1) {
            LoginAccessToken loginAccessToken =new LoginAccessToken();
            //注册成功自动登录
            UserInf userInf = mailSendService.autoLoginRegister(code);
            //token的生成是email+时间戳的md5加密字符串
            String email = userInf.getEmail();
            String token = Md5.textToMD5L32(email + DateUtil.timeStamp());
            String appname = userInf.getAppname();
            //昵称+token添加到浏览器cookie中
            response.addCookie(UserPasCookie("nickname",appname,3));
            response.addCookie(UserPasCookie("buildtoken",token,3));
            loginAccessToken.setTaken(token);
            loginAccessToken.setAppname(appname);
            loginAccessToken.setEmail(email);
            //设置记住登录状态数据3天时效（数据库sql我减了一天也就是2天）
            loginAccessToken.setTaken_time(DateUtil.afterDate(3));
            loginService.loginAccessInsert(loginAccessToken);
            return "registerSuccess";
        } else {
            if (mailSendService.reactiveCheack(code) == null){
                return "registerFailuer";
            }else {
                return "reactive";
            }
        }
    }

    //邮箱激活提示
    @RequestMapping(value = "/messgaeIssue",method = RequestMethod.GET)
    public String messgaeIssue(){
        return "messgaeIssue";
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
