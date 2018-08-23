package club.ragdollhouse.controller;

import club.ragdollhouse.service.NewsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class newsController {

    @Autowired
    NewsInfoService newsInfoService;

    @RequestMapping(value = "/newsInfo",method = RequestMethod.GET)
    public String newsInfo(Model model){

        model.addAttribute("newsList",newsInfoService.newsInfo());

        return "ITNews";

    }
}
