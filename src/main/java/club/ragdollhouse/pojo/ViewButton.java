package club.ragdollhouse.pojo;

/**
 * 菜单对象的pojo类(视图类)
 */
public class ViewButton extends Button {

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

}
