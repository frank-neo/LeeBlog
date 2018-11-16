package club.ragdollhouse.util;

import club.ragdollhouse.pojo.PageHelp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 博客的分页
 */
public class BlogPageUtil {

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
            pageNo = (Integer.parseInt(mapParam.get("pageNo")) - 1) * 8;
        }
        return pageNo;
    }


    //后端一页页数
    public static int getPageSizeBack(Map<String, String> mapParam) {

        //翻页页数
        int pageSize;

        if (mapParam.size() == 0 || mapParam.get("pageNo") == null) {
            pageSize = 8;
        } else {
            pageSize = 8;
        }
        return pageSize;
    }


    /**
     * 前段分页
     */
    public static List<PageHelp> frontPageList(Map<String, String> mapParam, int countNews) {

        List<PageHelp> list = new ArrayList<>();
        if (mapParam.get("type") == null && mapParam.get("pageNo") == null) {

            int i_mode = countNews / 8 + 1;
            for (int i = 1; i <= i_mode; i++) {
                PageHelp pageHelp = new PageHelp();
                pageHelp.setPage(i + "");
                pageHelp.setHref("/blog?pageNo=" + i + "&pageSize=" + 8);
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
            pageHelp1.setHref("/blog?pageNo=" + 1 + "&pageSize=" + 8);
            list.add(0, pageHelp1);
            PageHelp pageHelp2 = new PageHelp();
            pageHelp2.setPage("下一页");
            pageHelp2.setHref("/blog?pageNo=" + 2 + "&pageSize=" + 8);
            pageHelp2.setClasses("");
            list.add(pageHelp2);

        } else if (mapParam.get("type") != null && mapParam.get("pageNo") == null) {
            String type = (mapParam.get("type"));
            int i_mode = countNews / 8 + 1;
            for (int i = 1; i <= i_mode; i++) {
                PageHelp pageHelp = new PageHelp();
                pageHelp.setPage(i + "");
                pageHelp.setHref("/blog?pageNo=" + i + "&pageSize=" + 8 + "&type=" + type);
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
            pageHelp1.setHref("/blog?pageNo=" + 1 + "&pageSize=" + 8 + "&type=" + type);
            list.add(0, pageHelp1);
            PageHelp pageHelp2 = new PageHelp();
            pageHelp2.setPage("下一页");
            if (i_mode == 1) {
                pageHelp2.setHref("/blog?pageNo=" + 1 + "&pageSize=" + 8 + "&type=" + type);
            } else {
                pageHelp2.setHref("/blog?pageNo=" + 2 + "&pageSize=" + 8 + "&type=" + type);
            }
            pageHelp2.setClasses("");
            list.add(pageHelp2);
        } else if (mapParam.get("type") != null && mapParam.get("pageNo") != null) {

            int i_fm = 8;
            int i_mode = countNews / i_fm + 1;
            int now_page = Integer.parseInt(mapParam.get("pageNo"));
            String type = mapParam.get("type");
            for (int i = 1; i <= i_mode; i++) {
                PageHelp pageHelp = new PageHelp();
                pageHelp.setPage(i + "");
                pageHelp.setHref("/blog?pageNo=" + i + "&pageSize=" + 8 + "&type=" + type);
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
                pageHelp1.setHref("/blog?pageNo=" + 1 + "&pageSize=" + 8 + "&type=" + type);
                list.add(0, pageHelp1);
                PageHelp pageHelp2 = new PageHelp();
                pageHelp2.setPage("下一页");
                if (now_page == i_mode) {
                    pageHelp2.setHref("/blog?pageNo=" + 1 + "&pageSize=" + 8 + "&type=" + type);
                } else {
                    pageHelp2.setHref("/blog?pageNo=" + 2 + "&pageSize=" + 8 + "&type=" + type);
                }
                pageHelp2.setClasses("");
                list.add(pageHelp2);
            } else {
                PageHelp pageHelp1 = new PageHelp();
                pageHelp1.setPage("上一页");
                pageHelp1.setClasses("");
                pageHelp1.setHref("/blog?pageNo=" + (now_page - 1) + "&pageSize=" + 8 + "&type=" + type);
                list.add(0, pageHelp1);
                if (now_page == i_mode || i_mode == 1) {
                    PageHelp pageHelp2 = new PageHelp();
                    pageHelp2.setPage("下一页");
                    pageHelp2.setHref("/blog?pageNo=" + i_mode + "&pageSize=" + 8 + "&type=" + type);
                    pageHelp2.setClasses("");
                    list.add(pageHelp2);
                } else {
                    PageHelp pageHelp2 = new PageHelp();
                    pageHelp2.setPage("下一页");
                    pageHelp2.setHref("/blog?pageNo=" + (now_page + 1) + "&pageSize=" + 8 + "&type=" + type);
                    pageHelp2.setClasses("");
                    list.add(pageHelp2);
                }
            }

        } else {
            int i_fm = 8;
            int i_mode = countNews / i_fm + 1;
            int now_page = Integer.parseInt(mapParam.get("pageNo"));
            for (int i = 1; i <= i_mode; i++) {
                PageHelp pageHelp = new PageHelp();
                pageHelp.setPage(i + "");
                pageHelp.setHref("/blog?pageNo=" + i + "&pageSize=" + 8);
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
                pageHelp1.setHref("/blog?pageNo=" + 1 + "&pageSize=" + 8);
                list.add(0, pageHelp1);
                PageHelp pageHelp2 = new PageHelp();
                pageHelp2.setPage("下一页");
                if (now_page == i_mode) {
                    pageHelp2.setHref("/blog?pageNo=" + 1 + "&pageSize=" + 8);
                } else {
                    pageHelp2.setHref("/blog?pageNo=" + 2 + "&pageSize=" + 8);
                }
                pageHelp2.setClasses("");
                list.add(pageHelp2);
            } else {
                PageHelp pageHelp1 = new PageHelp();
                pageHelp1.setPage("上一页");
                pageHelp1.setClasses("");
                pageHelp1.setHref("/blog?pageNo=" + (now_page - 1) + "&pageSize=" + 8);
                list.add(0, pageHelp1);
                if (now_page == i_mode || i_mode == 1) {
                    PageHelp pageHelp2 = new PageHelp();
                    pageHelp2.setPage("下一页");
                    pageHelp2.setHref("/blog?pageNo=" + i_mode + "&pageSize=" + 8);
                    pageHelp2.setClasses("");
                    list.add(pageHelp2);
                } else {
                    PageHelp pageHelp2 = new PageHelp();
                    pageHelp2.setPage("下一页");
                    pageHelp2.setHref("/blog?pageNo=" + (now_page + 1) + "&pageSize=" + 8);
                    pageHelp2.setClasses("");
                    list.add(pageHelp2);
                }
            }
        }
        return list;
    }
}
