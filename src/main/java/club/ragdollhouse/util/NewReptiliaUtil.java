package club.ragdollhouse.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 新爬虫工具类
 * 针对infQ官网跟新，写的全新的爬虫工具类
 */
public class NewReptiliaUtil {

    static Document doc = null;

    static String agent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0";


    public static String getHtmlDemo(String url) {

        try {
            doc = Jsoup.connect(url)
                    .userAgent(agent)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true) //这个很重要
                    .cookie("auth", "token")
                    .timeout(8000)
                    //.proxy("116.228.146.90", 80)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + " **************** get");
        }

        return doc.toString();
//        System.out.println("================="+doc.toString());
//        if (doc != null) {
//            System.out.println(doc.body().text());
//        }
    }

}
