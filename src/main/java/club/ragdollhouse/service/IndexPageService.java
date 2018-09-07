package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.IndexPageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexPageService {

    @Resource
    IndexPageDao indexPageDao;

    //校验用户登录状态
    public String loginStatu(String email,String taken){
        return indexPageDao.loginStatu(email,taken);
    }
}
