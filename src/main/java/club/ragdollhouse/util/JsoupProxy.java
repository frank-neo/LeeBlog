package club.ragdollhouse.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 利用Jsoup编写的浏览器代理工具类
 */
public class JsoupProxy {

    //get方式访问
    public void GetJsoup(){

        Document doc = null;

        try {

            //String agent="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36" ;
            String agent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0";

            doc = Jsoup.connect("http://pv.sohu.com/cityjson")
                    .userAgent(agent)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true) //这个很重要
                    .cookie("auth", "token")
                    .timeout(8000)
                    .proxy("118.190.95.35",9001)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage()+"  **************** get");
        }

        if (doc!=null) {
            System.out.println(doc.body().text());
        }
    }

    @Test
    public void testJsoup(){
        GetJsoup();
    }

}
