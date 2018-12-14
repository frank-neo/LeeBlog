package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.ReptiliaCheck;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 爬虫mapper
 */
@Mapper
public interface ReptiliaCheckMapper {

    /**
     * 爬虫入库
     */
    @Insert("<script>" +
            "insert into reptilia_check(title,rep_time,url_addr,content,newsabstract,tag) VALUES " +
            "<foreach item='item' index='index' collection='list' separator=',' >" +
            "(#{item.title},#{item.rep_time},#{item.url_addr},#{item.content},#{item.newsAbstract},#{item.tag})" +
            "</foreach>" +
            "</script>")
    int insertReptiliaCheck(List<ReptiliaCheck> list);

}
