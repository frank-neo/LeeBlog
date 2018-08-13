package club.ragdollhouse.util;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬虫工具类【通过正则表达式来检索我们要的内容】
 *
 * @author Lee
 */
public class ReptiliaUtil {

    private Logger logger = Logger.getLogger(ReptiliaUtil.class);

    //正则核心方法
    @Test
    public void regexMain() {
        String url = "http://www.infoq.com/cn/news";
        String result = ReptiliaHttp.ReptiliaResult(url);
        getNewsInfo(result);
    }

    //正则具体实现
    private void getNewsInfo(String result) {

        //焦点新闻爬虫内容
        String focus_news = null;

        //正则匹配模板对象
        Pattern pattern_ulist = Pattern.compile
                ("<span class=\"followers\" style=\"display: inline;\"> 他的粉丝</span>([\\s\\S]+?)<span class=\"author\">");
        //声明并实例化string内容中符合模板的对象集
        Matcher matcher_ulist = pattern_ulist.matcher(result);
        while (matcher_ulist.find()) {
            focus_news = matcher_ulist.group(1);
            logger.info("=========================正在爬取新闻内容==============================");
            //System.out.println("============================================>"+focus_news);
            //从焦点新闻里面经常明细匹配
            //匹配标题
            Pattern focusNewsTitle = Pattern.compile("title=\"(.+?)\"");
            if (focus_news != null) {
                Matcher focusNewsTitleMatcher = focusNewsTitle.matcher(focus_news);
                while (focusNewsTitleMatcher.find()) {
                    //标题
                    String newsTitle = focusNewsTitleMatcher.group(1);
                    logger.info("文章标题：" + newsTitle);
                    Pattern newsLink = Pattern.compile("href=\"(.+?)\"");
                    Matcher newsLinkMatcher = newsLink.matcher(focus_news);
                    if (newsLinkMatcher.find()) {
                        //连接地址
                        String newsUrl = newsLinkMatcher.group(1);
                        logger.info("文章地址：http://www.infoq.com/" + newsUrl);

                        if (newsUrl != null) {
                            String newsurl = "http://www.infoq.com/" + newsUrl;
                            //打印拼接地址
                            //System.out.println(newsurl);
                            String newsthml = ReptiliaHttp.ReptiliaResult(newsurl);
                            //开始爬文章正文
                            Pattern contentDiv = Pattern.compile("<div class=\"text_info\">([\\s\\S]*)<div id=\"contentRatingWidget\">");
                            if (newsthml != null) {
                                Matcher contentDivMather = contentDiv.matcher(newsthml);
                                if (contentDivMather.find()) {
                                    String newsContent = contentDivMather.group(1);
                                    logger.info("新闻文本内容：" + newsContent);
                                }

                            }
                        }

                        String repTime = new Date().toString();
                        System.out.println("---------------->时间："+
                                DateUtil.timeStamp2Date(DateUtil.timeStamp(),"yyyy-MM-dd HH:mm:ss"));
                    }
                }
            }
        }
    }
}
