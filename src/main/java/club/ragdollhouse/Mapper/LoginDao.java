package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.LoginAccessToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 登录校验查询
 */
@Mapper
public interface LoginDao {

    //用户名是否存在
    @Select("SELECT 1 FROM USER_INF WHERE email = #{email}")
    String UserEmailExists(@Param("email") String email);

    //密码是否正确
    @Select("SELECT 1 FROM USER_INF WHERE email = #{email} AND  md5pwd = #{md5pwd}")
     String UserPwsCheck(@Param("email") String email,@Param("md5pwd") String md5pwd);

    //登录成功后，查询昵称，email，配合时间戳加密生成token
    @Select("SELECT appname , email FROM USER_INF WHERE email = #{email}")
    LoginAccessToken loginAccess(@Param("email") String email);

    //登录状态cookie设置成功后入库
    @Insert("INSERT INTO login_taken(email,appname,taken,taken_time) VALUES" +
            "(#{loginAccessToken.email}," +
            "#{loginAccessToken.appname}," +
            "#{loginAccessToken.taken}," +
            "#{loginAccessToken.taken_time})")
    int loginAccessInsert(@Param("loginAccessToken") LoginAccessToken loginAccessToken);

}