package club.ragdollhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录注册页面跳转控制器
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login2";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }


    //退出登录跳转
    @RequestMapping(value = "/outlogin", method = RequestMethod.GET)
    public String outlogin(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");
        Cookie delete1 = new Cookie("buildtoken", null);//假如要删除名称为username的Cookie
        delete1.setMaxAge(0); //立即删除型
        delete1.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
        response.addCookie(delete1);
        Cookie delete2 = new Cookie("nickname", null);
        delete2.setMaxAge(0);
        delete2.setPath("/");
        response.addCookie(delete2);
        return "login2";
    }

}
