package club.ragdollhouse.config;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class MenuInit implements ApplicationRunner {

    static Logger logger = Logger.getLogger(GetAccessToken.class);

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

/*        //等待access_token线程启动
        Thread.sleep(5000L);
        //发布自定义菜单
        HttpClient httpClient = new HttpClient();
        String menuResult = httpClient.doPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=",GetAccessToken.ACCESS_TOKEN,InitMenu.initMenu());
        logger.info("菜单解析结果 ==========>" + menuResult );*/
        logger.info("自定义菜单的功能等订阅号重新升级为公众号再放出吧。。。");
    }
}
