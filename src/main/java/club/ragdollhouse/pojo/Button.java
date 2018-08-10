package club.ragdollhouse.pojo;

/**
 * 菜单对象的pojo类（父类）
 */
public class Button {
    //按钮类型
    private String type;
    //按钮名称
    private String name;
    //子按钮组
    private Button[] sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
