package club.ragdollhouse.util;

import club.ragdollhouse.pojo.Button;
import club.ragdollhouse.pojo.ClickButton;
import club.ragdollhouse.pojo.Menu;
import club.ragdollhouse.pojo.ViewButton;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

/**
 * 初始化菜单类
 */
public class InitMenu {
    private static Logger logger = Logger.getLogger(InitMenu.class);

    public static String initMenu(){

        //这是菜单也就是json最外面那个{}
        Menu menu = new Menu();

        //菜单里面具体的按钮
        ClickButton button11 = new ClickButton();
        button11.setKey("11");
        button11.setType("click");
        button11.setName("这是点击按钮1");

        ClickButton button12 = new ClickButton();
        button12.setKey("12");
        button12.setType("click");
        button12.setName("这是点击按钮2");


        ViewButton button21 = new ViewButton();
        button21.setName("LEE的博客（视图按钮）");
        button21.setType("view");
        button21.setUrl("http://ragdollhouse.club");


        ClickButton button31 = new ClickButton();
        button31.setName("李少雄");
        button31.setType("click");
        button31.setKey("31");

        Button button1 = new Button();
        button1.setName("点击按钮"); //将11/12两个button作为二级菜单封装第一个一级菜单
        button1.setSub_button(new Button[]{button11,button12});

        Button button2 = new Button();
        button2.setName("相关网址"); //将21单独作为二级菜单封装
        button2.setSub_button(new Button[]{button21});

        menu.setButtons(new Button[]{button1,button2,button31});// 将31Button直接作为一级菜单

        String buttonJson = JSON.toJSONString(menu);
        logger.info("菜单json====》"+buttonJson);

        return buttonJson;
    }

}
