package club.ragdollhouse.config;

import club.ragdollhouse.service.DeleteTimerService;
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

/**
 * 定时删除数据库登录信息缓存数据
 */
@Order(4)
@Component
public class DeleteTimer implements ApplicationRunner {

    static Logger logger = Logger.getLogger(DeleteTimer.class);

    @Autowired
    DeleteTimerService deleteTimerService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        //等待别的业务都启动之后启动
        Thread.sleep(1000L);
        Timer timer = new Timer();
        //设置时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1); // 控制时
        calendar.set(Calendar.MINUTE, 0);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒
        Date time = calendar.getTime();     // 得出执行任务的时间,此处为今天的 1：00：00
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deleteTimerService.deleteLoginInf();
                logger.info("昨日登录失效数据清除以完成。");
                deleteTimerService.deleteUselessRegister();
                logger.info("昨日注册失效数据已经删除。");
            }
        },time,1000L * 60 * 60 * 24);

    }
}
