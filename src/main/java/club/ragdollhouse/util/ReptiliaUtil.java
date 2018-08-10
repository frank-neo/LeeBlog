package club.ragdollhouse.util;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬虫工具类【通过正则表达式来检索我们要的内容】
 * @author Lee
 */
public class ReptiliaUtil {

    //正则核心方法
    @Test
    public void regexMain() {
        String url = "https://news.baidu.com/guoji";
        String result = ReptiliaHttp.ReptiliaResult(url);
        //System.out.println(result);
/*        getNewsInfo("<ul class=\"ulist \"  >\n" +
                "<li class=\"bold-item\"><a href=\"http://news.cctv.com/2018/08/10/ARTIal6JUd9u6kkddE1SrKMc180810.shtml\" mon=\"col=1&amp;a=2&pn=1\" target=\"_blank\">中国成南太岛国最大捐助国 英媒：澳对此感到不安</a></li>\n" +
                "<li><a href=\"http://www.cyzone.cn/a/20180810/338242.html\" mon=\"col=1&amp;a=2&pn=2\" target=\"_blank\">海外报道丨纽约市颁布法令：将限制Uber和Lyft..</a></li>\n" +
                "<li><a href=\"http://news.cctv.com/2018/08/09/ARTIE2qzWeOvbVnyEYd9boc0180809.shtml\" mon=\"col=1&amp;a=2&pn=3\" target=\"_blank\">印尼龙目岛再发6级左右强震 此前地震已致数百人死亡</a></li>\n" +
                "<li><a href=\"http://gold.hexun.com/2018-08-10/193740290.html\" mon=\"col=1&amp;a=2&pn=4\" target=\"_blank\">俄方回应美国制裁：以彼之道还施彼身 黄金或借此翻身..</a></li>\n" +
                "<li><a href=\"http://news.sina.com.cn/o/2018-08-10/doc-ihhnunsq4597881.shtml\" mon=\"col=1&amp;a=2&pn=5\" target=\"_blank\">澳大利亚遭遇“百年不遇”大旱 农场主卖畜抗旱</a></li>\n" +
                "<li><a href=\"http://news.sina.com.cn/o/2018-08-10/doc-ihhnunsq4257492.shtml\" mon=\"col=1&amp;a=2&pn=6\" target=\"_blank\">0810早报｜马英九痛批蔡英文害苦民众 大喊“起义..</a></li>\n" +
                "</ul>");*/

        getNewsInfo(result);
    }

    //正则具体实现
    private String getNewsInfo(String result) {

        //正则匹配模板对象
        Pattern pattern_ulist = Pattern.compile("^<([\\s\\S]*)</script></html>$");
        System.out.println("11111111111>");
        //声明并实例化string内容中符合模板的对象集
        Matcher matcher_ulist =pattern_ulist.matcher(result);
        while (matcher_ulist.find()){
            System.out.println("匹配成功");
            String news_content = matcher_ulist.group();
            System.out.println("----------->"+news_content);
        }

        return "1";

    }


}
