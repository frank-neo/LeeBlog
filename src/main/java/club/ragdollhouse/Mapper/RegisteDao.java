package club.ragdollhouse.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RegisteDao {

    @Update("UPDATE USER_INF SET statu = 'Y' WHERE  CODE = #{code}")
    int activeRegisterStatu(@Param("code") String code);
}
