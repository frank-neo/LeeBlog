package club.ragdollhouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class ResultCheck {

    @RequestMapping(value = "/ResultServlet",method = RequestMethod.POST)
    public void resultCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String validateC = (String) request.getSession().getAttribute("checkcode");

        //从前台传来的用户输入的验证码
        String veryCode = request.getParameter("c");
        //System.out.println("-------------->>>"+veryCode);
        PrintWriter out = response.getWriter();
        if (veryCode == null || "".equals(veryCode)) {
            out.println("请输入验证码！");
        } else {
            //if (validateC.equals(veryCode)) {
            //忽略大小写处理：
            if (validateC.equalsIgnoreCase(veryCode)) {
                out.println(1);
            } else {
                out.println("验证码输入错误！");
            }
        }
        out.flush();
        out.close();
    }
}
