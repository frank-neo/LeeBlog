package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.LoginAccessToken;
import club.ragdollhouse.pojo.RegisterCode;
import org.apache.ibatis.annotations.*;

/**
 * 登录校验查询
 */
@Mapper
public interface LoginDao {

    //用户名是否存在
    @Select("SELECT 1 FROM USER_INF WHERE email = #{email} and statu = 'Y'")
    String UserEmailExists(@Param("email") String email);

    //密码是否正确
    @Select("SELECT 1 FROM USER_INF WHERE email = #{email} AND  md5pwd = #{md5pwd} and statu = 'Y'")
     String UserPwsCheck(@Param("email") String email,@Param("md5pwd") String md5pwd);

    //登录成功后，查询昵称，email，配合时间戳加密生成token
    @Select("SELECT appname , email FROM USER_INF WHERE email = #{email} and statu = 'Y'")
    LoginAccessToken loginAccess(@Param("email") String email);

    //登录状态cookie设置成功后入库
    @Insert("INSERT INTO login_taken(email,appname,taken,taken_time) VALUES" +
            "(#{loginAccessToken.email}," +
            "#{loginAccessToken.appname}," +
            "#{loginAccessToken.taken}," +
            "#{loginAccessToken.taken_time})")
    int loginAccessInsert(@Param("loginAccessToken") LoginAccessToken loginAccessToken);

    //昵称存在验证
    @Select("SELECT 1 FROM USER_INF WHERE appname = #{nickName} and statu = 'Y'")
    String nickNameExists(@Param("nickName") String nickName);

    //注册信息入库
    @Insert("INSERT INTO USER_INF(email,appname,sex,password,md5pwd,code,codeefftime) VALUES" +
            "(#{registerCode.username}," +
            "#{registerCode.nickname}," +
            "#{registerCode.sex}," +
            "#{registerCode.password}," +
            "#{registerCode.md5pwd}," +
            "#{registerCode.code}," +
            "#{registerCode.codeefftime})")
    int RegisterInfInsert(@Param("registerCode") RegisterCode registerCode);

    //登出系统，删除登录token
    @Delete("DELETE FROM login_taken WHERE appname = #{appname} AND taken = #{taken}")
    void outOfLogin(@Param("appname") String appname,@Param("taken") String taken);

}