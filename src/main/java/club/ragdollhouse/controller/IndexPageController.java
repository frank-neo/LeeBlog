package club.ragdollhouse.controller;

import club.ragdollhouse.service.IndexPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 首页的一些接口
 */
@RestController
public class IndexPageController {

    @Autowired
    IndexPageService indexPageService;

    //监测记住登录的查询接口
    @RequestMapping(value = "/logonStatu",method = RequestMethod.POST)
    public void logonStatu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String buildtoken = request.getParameter("buildtoken");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (indexPageService.loginStatu(username,buildtoken) != null
                && indexPageService.loginStatu(username,buildtoken).equals("1")){

            out.println(1);
        }else {
            out.print(0);
        }
        out.flush();
        out.close();
    }
}
