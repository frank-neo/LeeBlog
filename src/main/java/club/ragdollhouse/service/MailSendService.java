package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.RegisteDao;
import club.ragdollhouse.util.MailUtill;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 注册邮件发送服务
 * 注册激活服务
 */
@Service
public class MailSendService {
    @Resource
    RegisteDao registeDao;

    //注册发送邮件
    @Async
    public void sendRegistEmail(String email, String code){
        MailUtill mailUtill = new MailUtill(email,code);
        mailUtill.sendRegisterEmail();
    }

    //注册激活码激活
    public int activeRegisterStatu(String code){
        return registeDao.activeRegisterStatu(code);
    }
}
