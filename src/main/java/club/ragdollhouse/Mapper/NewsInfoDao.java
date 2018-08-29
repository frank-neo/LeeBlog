package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.ReptiliaCheck;
import club.ragdollhouse.pojo.TagPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 新闻查询
 */
@Mapper
public interface NewsInfoDao {

    //当天新闻查询
    @Select("select title,rep_time,newsabstract,tag from reptilia_check where rep_time = #{date}  limit #{pageNo},#{pageSize}")
    List<ReptiliaCheck> newsInfo(@Param("date") String date,@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

    //当天新闻总数查询
    @Select("SELECT COUNT(1) FROM reptilia_check where rep_time = #{date}")
    int countNews(@Param("date") String date);

    //新闻tag列表
    @Select("SELECT DISTINCT tag,\"icon-tag\" AS classes FROM reptilia_check")
    List<TagPojo> tagList();
}
