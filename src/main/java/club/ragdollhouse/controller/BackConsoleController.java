package club.ragdollhouse.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 后台跟新首页接口
 */
@RestController
public class BackConsoleController {

    //图片上传路径
    @Value("${homepage.path}")
    String path;

    @RequestMapping(value = "/backConsoleCheck", method = RequestMethod.POST)
    public void backConsoleCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获取数据类型
        String type = request.getParameter("type");
        if (!type.isEmpty()){
            if (type.equals("B")){
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
                

                out.println(1);
                out.flush();
                out.close();

            }
            if (type.equals("Z")){
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

                out.println(1);
                out.flush();
                out.close();

            }
            if (type.equals("T")){
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

                out.println(1);
                out.flush();
                out.close();

            }
        }



//        System.out.println("||||||||||||||||||||||||||||||||||||||====>title1_title" + title1_title);
//        System.out.println("||||||||||||||||||||||||||||||||||||||====>work_link" + work_link);
//        System.out.println("||||||||||||||||||||||||||||||||||||||====>abs_text" + abs_text);
//        System.out.println("||||||||||||||||||||||||||||||||||||||====>multipartFile大小" + multipartFile.getOriginalFilename());


    }
}
