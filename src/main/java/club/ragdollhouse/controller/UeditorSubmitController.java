package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.BlogEditor;
import club.ragdollhouse.service.UeditorService;
import club.ragdollhouse.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * ueditor提交入库接口
 */
@RestController
public class UeditorSubmitController {

    @Autowired
    UeditorService ueditorService;

    @RequestMapping(value = "/ueditorSubmit",method = RequestMethod.POST)
    public Map<String,String> dosubmit(HttpServletRequest req , HttpServletResponse rep){
        rep.setContentType("text/html;charset=utf-8");
        //博客标题
        String title = req.getParameter("title");
        //博客作者
        String author = req.getParameter("author");
        //博客类型
        String getblogtype = req.getParameter("getblog_type");
        //博客文本主体。
        String blogcontent = req.getParameter("blogcontent");
        //博客概要
        String samary = blogcontent.substring(0,180);

        //开始正则替换图片路径暂时不做，因为只对我一个人开放编辑
        //审核逻辑也pass掉
        //那就直接入库吧
        BlogEditor blogEditor = new BlogEditor();
        blogEditor.setTitle(title);
        blogEditor.setAuthor(author);
        blogEditor.setType(getblogtype);
        blogEditor.setContent(blogcontent);
        blogEditor.setBlog_date(DateUtil.beforeDate(0));
        blogEditor.setSamary(samary);
        int insert_count = ueditorService.blog_insert(blogEditor);
        Map<String,String> map = new HashMap<>();
        if (insert_count == 1){
            map.put("insert_state","success");
        }else {
            map.put("insert_state","fail");
        }
        return map;
    }
}
