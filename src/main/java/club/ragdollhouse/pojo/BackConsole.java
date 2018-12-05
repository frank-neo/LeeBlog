package club.ragdollhouse.pojo;

/**
 * 后台发布首页信息对象
 */
public class BackConsole {

    private int id;
    private String type;
    private String title;
    private String picture_link;
    private String abstracts;
    private String date;
    private String works_link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorks_link() {
        return works_link;
    }

    public void setWorks_link(String works_link) {
        this.works_link = works_link;
    }
}
