package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.BlogEditor;
import club.ragdollhouse.pojo.BlogType;
import club.ragdollhouse.pojo.PageHelp;
import club.ragdollhouse.service.BlogService;
import club.ragdollhouse.util.BlogPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 博客栏目的控制器
 */
@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    //博客列表页面
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public String blog(Model model, @RequestParam Map<String,String> mapParam) {

        //获取博客种类
        String type = mapParam.get("type");
        //文章列表
        List<BlogEditor> list = blogService
                .blogList(BlogPageUtil.getPageNoBack(mapParam),BlogPageUtil.getPageSizeBack(mapParam),type);
        //文章种类
        List<BlogType> list_type = blogService.blogTypeList();
        //分页
        List<PageHelp> blagPageList =BlogPageUtil.frontPageList(mapParam,blogService.blogCount(type));

        model.addAttribute("bloglist",list);
        model.addAttribute("list_type",list_type);
        model.addAttribute("pageList",blagPageList);
        return "blog";
    }
}
