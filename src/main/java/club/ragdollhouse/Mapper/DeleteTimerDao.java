package club.ragdollhouse.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * 删除数据库登录信息缓存数据
 */
@Mapper
public interface DeleteTimerDao {

    //每天清清登录状态数据库
    @Delete("DELETE FROM login_taken WHERE taken_time <= CURDATE()")
    void DeleteLoginInf();

    //每天清除失效注册信息
    @Delete("DELETE FROM role_user WHERE user_id IN (SELECT id FROM USER_INF WHERE statu = 'N' AND codeefftime <= CURDATE())")
    void deleteUselessRegisterRole();
    @Delete("DELETE FROM USER_INF WHERE statu = 'N' AND codeefftime <= CURDATE()")
    void deleteUselessRegister();

    //每天清除无效的新闻数据
    @Delete("DELETE FROM reptilia_check WHERE rep_time < DATE_SUB(NOW() ,INTERVAL 10 DAY)")
    void deleteUselessInfNews();
}
