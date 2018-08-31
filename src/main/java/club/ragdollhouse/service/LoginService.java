package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.LoginDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    LoginDao loginDao;

    //用户是否存在
    public String UserEmailExists (String email){
        return loginDao.UserEmailExists(email);
    }

    //密码是否正确
    public String UserPwsCheck(String email,String md5pwd){
        return loginDao.UserPwsCheck(email,md5pwd);
    }
}
