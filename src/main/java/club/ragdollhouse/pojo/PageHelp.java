package club.ragdollhouse.pojo;

/**
 * 分页小工具
 */
public class PageHelp {

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    //页码
    private String page;
    //样式
    private String classes;
    //连接
    private String href;
}
