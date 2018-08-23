package club.ragdollhouse.config;

import club.ragdollhouse.service.ReptiliaService;
import club.ragdollhouse.util.ReptiliaUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Order(3)
@Component
public class Reptlilia implements ApplicationRunner {

    static Logger logger = Logger.getLogger(Reptlilia.class);

    @Autowired
    ReptiliaService reptiliaService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //等待别的业务都启动之后启动爬虫
        Thread.sleep(10000L);
        //定时器
        Timer timer = new Timer();
        //设置时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18); // 控制时
        calendar.set(Calendar.MINUTE, 30);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒
        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的 18：30：00

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ReptiliaUtil reptiliaUtil = new ReptiliaUtil();
                //入库
                int news_count = reptiliaService.reptiliaInsert(reptiliaUtil.regexMain());
                logger.info("爬虫数据插入数据库：" + news_count + "条。");
            }
        }, time, 1000L * 60 * 60 * 24);

    }
}
