package club.ragdollhouse.util;

import club.ragdollhouse.pojo.PageHelp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装好的页面分页工具
 */
public class PageUtil {

    /**
     * 后端分页
     */
    //后端页码
    public static int getPageNoBack(Map<String, String> mapParam) {

        //翻页起始位置
        int pageNo;

        if (mapParam.size() == 0 || mapParam.get("pageNo") == null) {
            pageNo = 0;
        } else {
            pageNo = (Integer.parseInt(mapParam.get("pageNo")) - 1) * 4;
        }
        return pageNo;
    }

    //后端一页页数
    public static int getPageSizeBack(Map<String, String> mapParam) {

        //翻页页数
        int pageSize;

        if (mapParam.size() == 0 || mapParam.get("pageNo") == null) {
            pageSize = 4;
        } else {
            pageSize = 4;
        }
        return pageSize;
    }

    /**
     * 前段分页
     */
    public static List<PageHelp> frontPageList(Map<String, String> mapParam, int countNews) {

        List<PageHelp> list = new ArrayList<>();
        String date = mapParam.get("date");
        if (mapParam.size() == 0||mapParam.get("pageNo") == null) {

            int i_fm = 4;
            int i_mode = countNews / i_fm + 1;
            for (int i = 1; i <= i_mode; i++) {
                PageHelp pageHelp = new PageHelp();
                pageHelp.setPage(i + "");
                pageHelp.setHref("/newsInfo?pageNo=" + i + "&pageSize=" + 4 + "&date=" + date);
                if (i == 1) {
                    pageHelp.setClasses("current");
                } else {
                    pageHelp.setClasses("");
                }
                list.add(pageHelp);
            }
            PageHelp pageHelp1 = new PageHelp();
            pageHelp1.setPage("上一页");
            pageHelp1.setClasses("");
            pageHelp1.setHref("/newsInfo?pageNo=" + 1 + "&pageSize=" + 4 + "&date=" + date);
            list.add(0, pageHelp1);
            PageHelp pageHelp2 = new PageHelp();
            pageHelp2.setPage("下一页");
            pageHelp2.setHref("/newsInfo?pageNo=" + 2 + "&pageSize=" + 4 + "&date=" + date);
            pageHelp2.setClasses("");
            list.add(pageHelp2);

        } else {

            int i_fm = 4;
            int i_mode = countNews / i_fm + 1;
            int now_page = Integer.parseInt(mapParam.get("pageNo"));
            for (int i = 1; i <= i_mode; i++) {
                PageHelp pageHelp = new PageHelp();
                pageHelp.setPage(i + "");
                pageHelp.setHref("/newsInfo?pageNo=" + i + "&pageSize=" + 4 + "&date=" + date);
                if (i == now_page) {
                    pageHelp.setClasses("current");
                } else {
                    pageHelp.setClasses("");
                }
                list.add(pageHelp);
            }
            if (now_page == 1) {
                PageHelp pageHelp1 = new PageHelp();
                pageHelp1.setPage("上一页");
                pageHelp1.setClasses("");
                pageHelp1.setHref("/newsInfo?pageNo=" + 1 + "&pageSize=" + 4 + "&date=" + date);
                list.add(0, pageHelp1);
                PageHelp pageHelp2 = new PageHelp();
                pageHelp2.setPage("下一页");
                pageHelp2.setHref("/newsInfo?pageNo=" + 2 + "&pageSize=" + 4 + "&date=" + date);
                pageHelp2.setClasses("");
                list.add(pageHelp2);
            } else {
                PageHelp pageHelp1 = new PageHelp();
                pageHelp1.setPage("上一页");
                pageHelp1.setClasses("");
                pageHelp1.setHref("/newsInfo?pageNo=" + (now_page - 1) + "&pageSize=" + 4 + "&date=" + date);
                list.add(0, pageHelp1);
                if (now_page == i_mode) {
                    PageHelp pageHelp2 = new PageHelp();
                    pageHelp2.setPage("下一页");
                    pageHelp2.setHref("/newsInfo?pageNo=" + i_mode + "&pageSize=" + 4 + "&date=" + date);
                    pageHelp2.setClasses("");
                    list.add(pageHelp2);
                } else {
                    PageHelp pageHelp2 = new PageHelp();
                    pageHelp2.setPage("下一页");
                    pageHelp2.setHref("/newsInfo?pageNo=" + (now_page + 1) + "&pageSize=" + 4 + "&date=" + date);
                    pageHelp2.setClasses("");
                    list.add(pageHelp2);
                }
            }
        }

        return list;
    }

}
