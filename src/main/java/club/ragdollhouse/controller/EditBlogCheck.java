package club.ragdollhouse.controller;

import club.ragdollhouse.service.LoginService;
import club.ragdollhouse.service.PermissionsCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 编辑博客权限校验接口
 */
@RestController
public class EditBlogCheck {

    @Autowired
    PermissionsCheckService permissionsCheckService;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/EditBlogCheck", method = RequestMethod.POST)
    public void editcheck(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=utf-8");
        String buildtoken = req.getParameter("buildtoken");
        String nickname = req.getParameter("nickname");
        PrintWriter out = res.getWriter();
        System.out.println(permissionsCheckService.loginStatu(nickname, buildtoken)+"||"+nickname+"||"+buildtoken);
        if (buildtoken.equals("undefined") || nickname.equals("nickname")
                ||permissionsCheckService.loginStatu(nickname,buildtoken) == null) {
            loginService.outOfLogin(nickname,buildtoken);//先清除数据库登录takon
            Cookie delete1 = new Cookie("buildtoken", null);//假如要删除名称为username的Cookie
            delete1.setMaxAge(0); //立即删除型
            delete1.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
            res.addCookie(delete1);
            Cookie delete2 = new Cookie("nickname", null);
            delete2.setMaxAge(0);
            delete2.setPath("/");
            res.addCookie(delete2);
            out.println("{\"checkdata\": \"outoflogin\"}");
        } else {
            String checkdata = permissionsCheckService.editblogcheck(nickname);
            out.println("{\"checkdata\": \"" + checkdata + "\"}");
        }

        out.flush();
        out.close();

    }
}
