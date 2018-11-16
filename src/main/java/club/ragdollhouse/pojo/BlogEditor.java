package club.ragdollhouse.pojo;

/**
 * 博客入库文章本体bean
 */
public class BlogEditor {

    private String title;
    private String author;
    private String content;
    private String blog_date;
    private String type;
    //摘要
    private String samary;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBlog_date() {
        return blog_date;
    }

    public void setBlog_date(String blog_date) {
        this.blog_date = blog_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSamary() {
        return samary;
    }

    public void setSamary(String samary) {
        this.samary = samary;
    }

}
