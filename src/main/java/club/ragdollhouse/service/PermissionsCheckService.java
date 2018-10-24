package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.LoginDao;
import club.ragdollhouse.Mapper.PermissionsCheckDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 权限查询服务
 */
@Service
public class PermissionsCheckService {
    @Resource
    PermissionsCheckDao  permissionsCheckDao;

    @Resource
    LoginDao loginDao;

    //登录信息是否失效
    public String loginStatu(String nickname, String taken){
        return loginDao.loginStatu(nickname,taken);
    }

    //权限查询
    public String editblogcheck(String nickname){
        return permissionsCheckDao.blogcheck(nickname);
    }

}
