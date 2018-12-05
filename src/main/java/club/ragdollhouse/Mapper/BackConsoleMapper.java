package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.BackConsole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 首页后台控制发布mapper
 */
@Mapper
public interface BackConsoleMapper {

    //首页后台控制发布title数据入库
    @Insert("INSERT INTO home_page_manager(TYPE,title,picture_link,abstracts,DATE,works_link) " +
            "VALUES(#{backConsole.type},#{backConsole.title},#{backConsole.picture_link}," +
            "#{backConsole.abstracts},#{backConsole.date},#{backConsole.works_link})")
    int titleInsert(@Param("backConsole") BackConsole backConsole);
}
