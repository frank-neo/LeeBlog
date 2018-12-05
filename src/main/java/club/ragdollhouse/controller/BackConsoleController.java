package club.ragdollhouse.controller;

import club.ragdollhouse.pojo.BackConsole;
import club.ragdollhouse.service.BackConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 后台跟新首页接口
 */
@RestController
public class BackConsoleController {

    @Autowired
    BackConsoleService backConsoleService;

    //图片上传路径
    @Value("${homepage.path}")
    String path;

    //图片预览地址
    @Value("${homepage.viewurl}")
    String viewurl;

    @RequestMapping(value = "/backConsoleCheck", method = RequestMethod.POST)
    public void backConsoleCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //插入数据条数
        int sinsert_num = 0;
        //获取数据类型
        String type = request.getParameter("type");
        if (!type.isEmpty()) {
            if (type.equals("B")) {
                String index = request.getParameter("index");
                // 获取标题
                String title1_title = request.getParameter("title1_title");
                //获取文章地址
                String work_link = request.getParameter("work_link");
                //获取摘要
                String abs_text = request.getParameter("abs_text");
                //取出form-data中的二进制字段
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile multipartFile = multipartRequest.getFile("file");//file是form-data中二进制字段对应的name
                //List<MultipartFile> files = multipartRequest.getFiles("file");//多个文件
                String fileName = multipartFile.getOriginalFilename();
                //为了避免重复简单处理
                String nowName = new Date().getTime() + "_" + fileName;
                File dest = new File(path + "/" + nowName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                multipartFile.transferTo(dest); //保存文件
                BackConsole backConsole = new BackConsole();
                backConsole.setTitle(title1_title);
                backConsole.setType(type + index);
                backConsole.setWorks_link(work_link);
                backConsole.setAbstracts(abs_text);
                backConsole.setPicture_link(viewurl + nowName);
                sinsert_num = backConsoleService.titleInsert(backConsole);
            }
            if (type.equals("Z")) {
                //下标
                String index = request.getParameter("index");
                // 获取标题
                String title1_title = request.getParameter("title1_title");
                //获取文章地址
                String work_link = request.getParameter("work_link");
                //获取摘要
                String abs_text = request.getParameter("abs_text");
                //获取日期
                String date = request.getParameter("data");
                BackConsole backConsole = new BackConsole();
                backConsole.setType(type + index);
                backConsole.setTitle(title1_title);
                backConsole.setWorks_link(work_link);
                backConsole.setAbstracts(abs_text);
                backConsole.setDate(date);
                sinsert_num = backConsoleService.titleInsert(backConsole);
            }
            if (type.equals("T")) {
                // 获取标题
                String title1_title = request.getParameter("title1_title");
                //下标
                String index = request.getParameter("index");
                //获取文章地址
                String work_link = request.getParameter("work_link");
                //获取摘要
                String abs_text = request.getParameter("abs_text");
                //取出form-data中的二进制字段
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile multipartFile = multipartRequest.getFile("file");//file是form-data中二进制字段对应的name
                String fileName = multipartFile.getOriginalFilename();
                //为了避免重复简单处理
                String nowName = new Date().getTime() + "_" + fileName;
                File dest = new File(path + "/" + nowName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                multipartFile.transferTo(dest); //保存文件
                BackConsole backConsole = new BackConsole();
                backConsole.setTitle(title1_title);
                backConsole.setType(type + index);
                backConsole.setWorks_link(work_link);
                backConsole.setAbstracts(abs_text);
                backConsole.setPicture_link(viewurl + nowName);
                sinsert_num = backConsoleService.titleInsert(backConsole);
            }

            out.println(sinsert_num);
            out.flush();
            out.close();
        }
    }
}
