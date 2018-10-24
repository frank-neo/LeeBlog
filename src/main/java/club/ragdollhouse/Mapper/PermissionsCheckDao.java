package club.ragdollhouse.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 系统权限查询mapper
 */
@Mapper
public interface PermissionsCheckDao {

    @Select("SELECT r.role_name FROM USER_INF ui LEFT JOIN role_user ru ON " +
            "ru.user_id = ui.id LEFT JOIN role r ON r.id = ru.role_id WHERE ui.appname = #{nickname}")
    String blogcheck(@Param("nickname") String nickname);
}
