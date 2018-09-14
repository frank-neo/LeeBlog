package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.UserInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RegisteDao {

    //邮件激活
    @Update("UPDATE USER_INF SET statu = 'Y' WHERE  CODE = #{code} AND statu = 'N'")
    int activeRegisterStatu(@Param("code") String code);

    //查询否重复注册
    @Select("SELECT 1 FROM USER_INF WHERE CODE = #{code} and statu = 'Y'")
    String reactiveCheack(@Param("code") String code);

    //注册成功后直接登录查询数据
    @Select("SELECT appname,email FROM USER_INF WHERE CODE = #{code}")
    public UserInf autoLoginRegister(@Param("code") String code);
}
