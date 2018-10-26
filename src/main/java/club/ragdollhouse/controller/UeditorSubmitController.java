package club.ragdollhouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ueditor提交入库接口
 */
@RestController
public class UeditorSubmitController {

    @RequestMapping(value = "/ueditorSubmit",method = RequestMethod.POST)
    public void dosubmit(HttpServletRequest req , HttpServletResponse rep){
        rep.setContentType("text/html;charset=utf-8");
        String blogdata = req.getParameter("blog_data");
        System.out.println("======================>"+blogdata);
    }
}
