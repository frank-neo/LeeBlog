package club.ragdollhouse.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

/**
 * 新爬虫工具类
 * 针对infQ官网跟新，写的全新的爬虫工具类
 */
public class NewReptiliaUtil {

    static Document doc = null;

    static String agent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0";

    public static String getHtmlDemo(String url) {


        try {
            URL link_url = new URL(url);
            //防止若防盗链的
            String referer = link_url.getProtocol() + "://" + link_url.getHost();
            //System.out.println("============>>>>>>referer:"+referer);
            doc = Jsoup.connect(url)
                    .userAgent(agent)
                    .ignoreHttpErrors(true) //这个很重要
                    .cookie("auth", "token")
                    .timeout(8000)
                    //.proxy
                    .ignoreContentType(true).referrer(referer)
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
