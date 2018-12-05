package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.BackConsole;
import club.ragdollhouse.service.HtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 菜单页面跳转控制器
 */
@Controller
public class htmlController {

    @Autowired
    HtmlService htmlService;

    //跳转到博客编辑页面
    @RequestMapping(value = "/gotoEdit", method = RequestMethod.GET)
    public String gotoEdit() {
        return "blogEditPage";
    }

    //无权访问页面
    @RequestMapping(value = "/nopermission", method = RequestMethod.GET)
    public String nopermission() {
        return "nopermission";
    }

    //商业合作
    @RequestMapping(value = "/business_cop", method = RequestMethod.GET)
    public String business_cop() {
        return "business_cop";
    }

    //后台管理页面
    @RequestMapping(value = "/lishaoxiong",method = RequestMethod.GET)
    public String backConsle(){
        return "backConsle";
    }

    //默认登录页面
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String defult(Model model){
        List<BackConsole> titleList = htmlService.indexPageTitle();
        List<BackConsole> riTuiList = htmlService.indexZiXun();
        List<BackConsole> tuiboList = htmlService.indexTuiBo();
        model.addAttribute("titleList",titleList);
        model.addAttribute("riTuiList",riTuiList);
        model.addAttribute("tuiboList",tuiboList);
        return "index";
    }

}
