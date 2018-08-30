package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.LoginCode;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class ResultCheck {

    static String SESSION_IN_TIME = "99";
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

    @RequestMapping(value = "/ResultServlet", method = RequestMethod.POST)
    public void resultCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String validateC = (String) request.getSession().getAttribute("checkcode");
        System.out.println("validateC--------->" + validateC);
        //从前台传来的用户输入的验证码
        String veryCode = request.getParameter("c");
        String userEmail = request.getParameter("u");
        String passWord = request.getParameter("p");
        System.out.println("-------------->>>" + veryCode + ";" + userEmail + ";" + passWord);
        LoginCode loginCode = new LoginCode();
        PrintWriter out = response.getWriter();
        //第一步验证码要对
        if (validateC != null) {
            loginCode.setSessionval(SESSION_IN_TIME);
            if (veryCode == null || "".equals(veryCode)) {
                //out.println("验证码为空");
                //out.println(l);
                loginCode.setCode(CODE_IS_NULL);
            } else {
                //if (validateC.equals(veryCode)) {
                //忽略大小写处理：
                if (validateC.equalsIgnoreCase(veryCode)) {
                    //out.println(1);
                    //out.println(l);
                    loginCode.setCode(CODE_IS_RIGHT);
                } else {
                    //out.println("验证码输入错误！");
                    //out.println(l);
                    loginCode.setCode(CODE_IS_WRONG);
                }
            }
        } else {
            //session的有效时间是占用cpu的，我们不去延长时间，前端仅仅判断失效然后刷新
            //out.println(0);
            //out.println(l);
            loginCode.setSessionval(SESSION_OUT_TIME);
        }
        //用户名验证
        if (userEmail == null || "".equals(userEmail)){
            loginCode.setUsername(USEREMAIL_IS_NULL);
        }

        String l = JSON.toJSONString(loginCode);
        out.println(l);
        out.flush();
        out.close();
    }
}
