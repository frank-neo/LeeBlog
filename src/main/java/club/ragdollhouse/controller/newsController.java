package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.DatePojo;
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

    //新闻列表页面
    @RequestMapping(value = "/newsInfo", method = RequestMethod.GET)
    public String newsInfo(Model model, @RequestParam Map<String, String> mapParam) {
        String date;
        //如果时间为空，则默认为查询今天的数据
        if (mapParam.get("date") == null) {
            date = DateUtil.timeStamp2Date(DateUtil.timeStamp(), "yyyy-MM-dd");
            mapParam.put("date", date);
        } else {
            date = mapParam.get("date");
        }
        DatePojo datePojo = new DatePojo();
        datePojo.setDate("跟新时间：" + date);
        model.addAttribute("newsdate", datePojo);
        model.addAttribute("newsList", newsInfoService.newsInfo(date, PageUtil.getPageNoBack(mapParam), PageUtil.getPageSizeBack(mapParam)));
        model.addAttribute("pageList", PageUtil.frontPageList(mapParam, newsInfoService.countNews(date)));
        model.addAttribute("dateList", newsInfoService.beforeDateList());
        model.addAttribute("tagList", newsInfoService.tagList());
        return "ITNews";
    }

    //新闻明细页面
    @RequestMapping(value = "/newDetail", method = RequestMethod.GET)
    public String newDetail(@RequestParam Map<String, String> mapParm,Model model) {
        String newsid = mapParm.get("newsid");
        model.addAttribute("newsdetail",newsInfoService.newsDetail(newsid));
        return "newsDetail";
    }
}
