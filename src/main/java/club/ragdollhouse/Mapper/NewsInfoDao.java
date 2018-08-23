package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.ReptiliaCheck;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 新闻查询
 */
@Mapper
public interface NewsInfoDao {

    @Select("select title,rep_time,newsabstract from reptilia_check where rep_time = #{date}")
    List<ReptiliaCheck> newsInfo(String date);
}
