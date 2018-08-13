package club.ragdollhouse.controller;

import club.ragdollhouse.service.Testservice;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    Testservice testservice;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        return "123";
    }

    @RequestMapping(value = "testxml", method = RequestMethod.POST)
    public String testxml(HttpServletRequest request) throws IOException, DocumentException {

        ServletInputStream inputStreamReader = request.getInputStream();
/*        StringBuilder stringBuilder = new StringBuilder();
        byte[] b = new byte[1024];
        int lens = -1;
        while ((lens = inputStreamReader.read(b))>0){

            stringBuilder.append(new String(b,0,lens));

        }
        //将拿到的xml解析出来，转为字符串
        String testContent = stringBuilder.toString();
        //将xml文件的字符串格式打印出来看看有没有问题。
        System.out.println(testContent);*/
        //开始解析xml
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(inputStreamReader);
        //获取root（节点）的值
        Element root = doc.getRootElement();
        List ele = root.elements();
        for (int i = 0; i < ele.size(); i++) {
            Element el = (Element) ele.get(i);
            String name = el.getName();
            String value = el.getStringValue();
            System.out.println(name + ";" + value);
        }

        return "123";
    }

    /**
     * 测试返回xml文件
     *
     * @param res
     */
    @RequestMapping(value = "/testxmlfile", method = RequestMethod.POST)
    public void testcml(HttpServletRequest request, HttpServletResponse res) throws IOException {

        res.setContentType("text/xml;charset=UTF-8");
        ServletOutputStream out = res.getOutputStream();

        OutputStreamWriter ow = new OutputStreamWriter(out, "UTF-8");
        ow.write("<xml>");
        ow.write("\r\n");
        ow.write("<ToUserName><![CDATA[公众号]]></ToUserName>");
        ow.write("\r\n");
        ow.write("<ToUserName><![CDATA[公众21212号]]></ToUserName>");
        ow.write("</xml>");
        ow.flush();
        ow.close();

        out.close();

    }


    @RequestMapping(value = "/testSelect",method = RequestMethod.GET)
    public String testSelect(){

        return testservice.Test().toString();
    }
}
