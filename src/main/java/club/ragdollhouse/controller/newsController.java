package club.ragdollhouse.controller;

import club.ragdollhouse.service.NewsInfoService;
import club.ragdollhouse.util.DateUtil;
import club.ragdollhouse.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class newsController {

    @Autowired
    NewsInfoService newsInfoService;

    @RequestMapping(value = "/newsInfo",method = RequestMethod.GET)
    public String newsInfo(Model model, @RequestParam Map<String,String> mapParam){
        String date = DateUtil.timeStamp2Date(DateUtil.timeStamp(), "yyyy-MM-dd");
        model.addAttribute("newsList",newsInfoService.newsInfo(date,PageUtil.getPageNoBack(mapParam),PageUtil.getPageSizeBack(mapParam)));
        model.addAttribute("pageList",PageUtil.frontPageList(mapParam,newsInfoService.countNews(date)));
        return "ITNews";
    }
}
