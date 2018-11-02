package club.ragdollhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 博客栏目的控制器
 */
@Controller
public class BlogController {

    //博客列表页面
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String blog(Model model) {

        model.addAttribute("bloglist");
        return "blog";
    }

}
