package club.ragdollhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 菜单页面跳转控制器
 */
@Controller
public class htmlController {


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
}
