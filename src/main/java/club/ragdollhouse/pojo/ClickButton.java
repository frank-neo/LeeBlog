package club.ragdollhouse.pojo;

/**
 * 菜单对象的pojo类（点击类）
 */
public class ClickButton extends Button {
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;
}
