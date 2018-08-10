package club.ragdollhouse.config;

import club.ragdollhouse.pojo.AccessToken;
import club.ragdollhouse.util.AccessTokenUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 微信公众号AccessToken的获取工具类
 * 1，一个是定时获取AccessToken方法（定时跑，每7000秒跑一次，7000 = 返回的目前生效时间【7200】 - 200）
 * 2,一个主动获取AccessToken的方法（当微信公众号后期调整失效时间后，一部分业务accesstoken失效，主动重新跟新全局AccessToken）
 *
 * @author Lee
 */
@Order(1)
@Component
public class GetAccessToken implements ApplicationRunner {

    static Logger logger = Logger.getLogger(GetAccessToken.class);

    //全局ACCESS_TOKEN
    public static String ACCESS_TOKEN;
    //全局ACCESS_TOKEN的生效时间(默认7000秒)
    public static int EXPIRES_IN = 7000;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //定时获取access_taken
        getAccessTokenTimer();
    }


    /**
     * 定时刷新access_token方法
     */
    public void getAccessTokenTimer() {

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //这里是拿access_token的逻辑
                try {
                    AccessToken accessToken = JSON.parseObject(AccessTokenUtil.getStringAccessToken(), AccessToken.class);
                    ACCESS_TOKEN = accessToken.getAccessToken();
                    EXPIRES_IN = accessToken.getExpiresIn();
                    logger.info("定时刷新新的ACCESS_TOKEN的数据:" + ACCESS_TOKEN + ";date===>" + new Date());
                    logger.info("定时刷新新的EXPIRES_IN的数据:" + EXPIRES_IN + ";date===>" + new Date());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0L, 1000L * EXPIRES_IN);
    }

    /**
     * 触发类刷新并获取ACCESS_TOKEN
     * @return ACCESS_TOKEN
     */
    public static String getAccessToken() {

        try {
            AccessToken accessToken = JSON.parseObject(AccessTokenUtil.getStringAccessToken(), AccessToken.class);
            ACCESS_TOKEN = accessToken.getAccessToken();
            EXPIRES_IN = accessToken.getExpiresIn();
            logger.info("被动调取新的ACCESS_TOKEN的数据:" + ACCESS_TOKEN + ";date===>" + new Date());
            logger.info("被动调取新的EXPIRES_IN的数据:" + EXPIRES_IN + ";date===>" + new Date());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ACCESS_TOKEN;

    }

}
