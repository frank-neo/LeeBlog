package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.DeleteTimerDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 删除数据库登录信息缓存数据
 */
@Service
public class DeleteTimerService {

    @Resource
    DeleteTimerDao deleteTimerDao;

    //每天删除登录状态过期数据
    public void deleteLoginInf(){

        deleteTimerDao.DeleteLoginInf();
    }

    //注册失效数据清除
    @Transactional
    public void deleteUselessRegister(){
        deleteTimerDao.deleteUselessRegisterRole();
        deleteTimerDao.deleteUselessRegister();
    }

    //清除过期新闻数据
    public void deleteUselessInfNews(){
        deleteTimerDao.deleteUselessInfNews();
    }

}
