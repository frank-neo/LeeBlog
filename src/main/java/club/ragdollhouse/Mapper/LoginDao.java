package club.ragdollhouse.Mapper;

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

}