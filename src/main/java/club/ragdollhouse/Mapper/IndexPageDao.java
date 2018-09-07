package club.ragdollhouse.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 首页相关的数据库操作
 */
@Mapper
public interface IndexPageDao {

    //校验用户登录状态
    @Select("SELECT 1 FROM login_taken WHERE email = #{email} AND  taken = #{taken}")
    String loginStatu(@Param("email") String email,@Param("taken") String taken);
}
