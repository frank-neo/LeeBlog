package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.LoginDao;
import club.ragdollhouse.pojo.LoginAccessToken;
import club.ragdollhouse.pojo.RegisterCode;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    LoginDao loginDao;

    //用户是否存在
    public String UserEmailExists(String email) {
        return loginDao.UserEmailExists(email);
    }

    //密码是否正确
    public String UserPwsCheck(String email, String md5pwd) {
        return loginDao.UserPwsCheck(email, md5pwd);
    }

    //登录成功后，查询昵称，email，配合时间戳加密生成token
    public LoginAccessToken loginAccess(String email) {
        return loginDao.loginAccess(email);
    }

    //记住登录状态数据入库
    public int loginAccessInsert(LoginAccessToken loginAccessToken){
        return loginDao.loginAccessInsert(loginAccessToken);
    }

    //昵称重复校验
    public String nickNameExists(String nickName){
        return loginDao.nickNameExists(nickName);
    }

    //注册信息入库
    public int RegisterInfInsert( RegisterCode registerCode){
        return loginDao.RegisterInfInsert(registerCode);
    };

    //退出登录删除数据token
    public void outOfLogin(String appname,String taken){ loginDao.outOfLogin(appname,taken);}

}
