package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.ReptiliaCheck;
import club.ragdollhouse.pojo.TagPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 新闻查询
 */
@Mapper
public interface NewsInfoDao {

    //当天新闻查询
    @Select("select id,title,rep_time,newsabstract,tag from reptilia_check where rep_time = #{date}  limit #{pageNo},#{pageSize}")
    List<ReptiliaCheck> newsInfo(@Param("date") String date,@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

    //当天新闻总数查询
    @Select("SELECT COUNT(1) FROM reptilia_check where rep_time = #{date}")
    int countNews(@Param("date") String date);

    //新闻tag列表
    @Select("SELECT DISTINCT rep_time,tag,\"icon-tag\" AS classes FROM reptilia_check WHERE rep_time > DATE_SUB(CURDATE(), INTERVAL 10 DAY) ORDER BY rep_time DESC LIMIT 0,20")
    List<TagPojo> tagList();

    @Select("SELECT title,url_addr,content FROM reptilia_check WHERE id = #{id}")
    ReptiliaCheck newsDetail(@RequestParam("id") Integer id);
}
