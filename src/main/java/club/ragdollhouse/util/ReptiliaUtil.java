package club.ragdollhouse.util;

import club.ragdollhouse.pojo.ReptiliaCheck;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬虫工具类【通过正则表达式来检索我们要的内容】
 * infq官网的修改，也就意味着这个爬虫工具类的下岗了。
 * 不过ITeye还是可以爬的
 *
 * @author Lee
 */
public class ReptiliaUtil {

    private Logger logger = Logger.getLogger(ReptiliaUtil.class);

    //正则核心方法
    public List<ReptiliaCheck> regexMain() {
        String url = "https://news.cnblogs.com/";
        String result = ReptiliaHttp.ReptiliaResult(url);
        return getNewsInfo(result);
    }

    //正则具体实现
    private List<ReptiliaCheck> getNewsInfo(String result) {

        //焦点新闻爬虫内容
        String focus_news = null;

        //正则匹配模板对象
        Pattern pattern_ulist = Pattern.compile
                ("<li><a href=\"/\" class=\"current\">最新发布</a></li>([\\s\\S]+?)<div class=\"pager\">");
        //声明并实例化string内容中符合模板的对象集
        Matcher matcher_ulist = pattern_ulist.matcher(result);
        //爬取内容结果集
        List<ReptiliaCheck> list_r = new ArrayList<ReptiliaCheck>();
        //储存爬虫内容的相关数据
        List<String> news_Titles = new ArrayList<String>();//文章标题
        List<String> abs_text = new ArrayList<String>();//文章摘要
        List<String> news_url = new ArrayList<String>();//文章地址
        List<String> news_body = new ArrayList<String>();//文章内容
        List<String> news_dates = new ArrayList<String>();//文章日期

        //爬虫时间戳
        String repTime = DateUtil.timeStamp2Date(DateUtil.timeStamp(), "yyyy-MM-dd HH:mm:ss");

        if (matcher_ulist.find()) {
            focus_news = matcher_ulist.group(1);
            //logger.info("=========================正在爬取新闻内容==============================");
            System.out.println("=========================正在爬取新闻内容==============================");
            //System.out.println("============================================>"+focus_news);
            //从焦点新闻里面进行明细匹配
            Pattern focusNewsTitle = Pattern.compile("<h2 class=\"news_entry\">(.+?)</a>");
            if (focus_news != null) {
                Matcher focusNewsTitleMatcher = focusNewsTitle.matcher(focus_news);
                while (focusNewsTitleMatcher.find()) {
                    //爬虫审核对象
                    ReptiliaCheck reptiliaCheck = new ReptiliaCheck();
                    //标题
                    String newsTitle = focusNewsTitleMatcher.group(1);
                    if (newsTitle != null) {
                        String newsTitleSon = newsTitle.trim().substring(37);
                        logger.info("文章标题：" + newsTitleSon);
                        news_Titles.add(newsTitleSon);
                    }
                }

                //开始爬文章摘要
                //Pattern newsabstract = Pattern.compile("class=\"topic_img\" alt=\"\"/></a>([\\s\\S]*)<div class=\"entry_footer\">");
                //Pattern newsabstract = Pattern.compile("class=\"topic_img\" alt=\"\"/></a>(.+?)<div class=\"entry_footer\">");
                Pattern newsabstract = Pattern.compile("<div class=\"entry_summary\" style=\"display: block;\">(.+?)<div class=\"entry_footer\">");
                Matcher newsabstractMatcher = newsabstract.matcher(focus_news);
                while (newsabstractMatcher.find()) {
                    String nabstract = newsabstractMatcher.group(1);
                    if (nabstract != null) {
                        //去除首位空格
                        String tri_nabstract = nabstract.trim()
                                .replaceAll("<a href=\"(.+?)</a>","")//去除有图片的连接文字
                                .replaceAll("</div>", "")
                                .replaceAll("<a href=\"", "")
                                .replaceAll("</a>", "");
                        logger.info("文章摘要：" + tri_nabstract);
                        abs_text.add(tri_nabstract);
                    }
                }
                //开始爬文章地址
                Pattern newsLink = Pattern.compile("<h2 class=\"news_entry\">(.+?)\" target=\"_blank\">");
                Matcher newsLinkMatcher = newsLink.matcher(focus_news);
                while (newsLinkMatcher.find()) {
                    //连接地址
                    String newsUrl = newsLinkMatcher.group(1).trim().substring(9);
                    if (newsUrl != null) {
                        String newsurl = "https://news.cnblogs.com" + newsUrl;
                        logger.info("文章地址：" + newsurl);
                        news_url.add(newsurl);
                        String newsthml = NewReptiliaUtil.getHtmlDemo(newsurl);
                        //开始爬文章正文
                        Pattern contentDiv = Pattern.compile("<div id=\"news_body\">([\\s\\S]*)<!--end: news_body -->");
                        if (newsthml != null) {
                            Matcher contentDivMather = contentDiv.matcher(newsthml);
                            if (contentDivMather.find()) {
                                String newsContent = contentDivMather.group(1)
                                        .replaceAll("</div>", "")
                                        .replaceAll("src=\"","src=\"http:");
                                if (newsContent != null) {
                                    logger.info("新闻文本内容：" + newsContent);
                                    news_body.add(newsContent);
                                    news_dates.add(repTime);
                                    logger.info("---------------->爬虫时间：" + repTime);
                                }
                            }
                        }
                    }
                }
                //循环封装list_r
                int newssizes = news_Titles.size();
                //System.out.println("|||||||||||||||||||||||||||||||||爬虫数组的长度为："+newssizes);
                for (int i = 0; i < newssizes; i++) {
                    ReptiliaCheck rc = new ReptiliaCheck();
                    rc.setTitle(news_Titles.get(i));
                    rc.setNewsAbstract(abs_text.get(i));
                    rc.setUrl_addr(news_url.get(i));
                    rc.setContent(news_body.get(i));
                    rc.setRep_time(news_dates.get(i));
                    list_r.add(rc);
                    //System.out.println("======================================>此时i的值为："+i);
                }
            }
        }
        return list_r;
    }
}