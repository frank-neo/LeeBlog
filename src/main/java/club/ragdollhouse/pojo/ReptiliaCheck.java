package club.ragdollhouse.pojo;

/**
 * 推送审核新闻实体类
 */
public class ReptiliaCheck {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCheck_statu() {
        return check_statu;
    }

    public void setCheck_statu(String check_statu) {
        this.check_statu = check_statu;
    }

    public String getPush_flag() {
        return push_flag;
    }

    public void setPush_flag(String push_flag) {
        this.push_flag = push_flag;
    }

    public String getRep_time() {
        return rep_time;
    }

    public void setRep_time(String rep_time) {
        this.rep_time = rep_time;
    }

    public String getUrl_addr() {
        return url_addr;
    }

    public void setUrl_addr(String url_addr) {
        this.url_addr = url_addr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    //审核新闻id
    private int id;
    //审核新闻标题
    private String title;
    //审核状态
    private String check_statu;
    //是否推送
    private String push_flag;
    //爬虫时间
    private String rep_time;
    //文章地址
    private String url_addr;
    //文章内容
    private String content;
    //文章摘要
    private String newsAbstract;
}
