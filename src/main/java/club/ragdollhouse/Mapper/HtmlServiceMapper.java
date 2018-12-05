package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.BackConsole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 页面渲染mapper
 */
@Mapper
public interface HtmlServiceMapper {

    //标题查询
    @Select("(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'B1' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'B2' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'B3' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'B4' ORDER BY id DESC LIMIT 1)")
    List<BackConsole> titleRelaoad();

    //咨询
    @Select("(SELECT title,DATE,abstracts,works_link FROM home_page_manager WHERE TYPE = 'Z1' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,DATE,abstracts,works_link FROM home_page_manager WHERE TYPE = 'Z2' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,DATE,abstracts,works_link FROM home_page_manager WHERE TYPE = 'Z3' ORDER BY id DESC LIMIT 1)")
    List<BackConsole> ziXunReload();

    //推博
    @Select("(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'T1' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'T2' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'T3' ORDER BY id DESC LIMIT 1)\n" +
            "UNION ALL\n" +
            "(SELECT title,picture_link,abstracts,works_link FROM home_page_manager WHERE TYPE = 'T4' ORDER BY id DESC LIMIT 1)")
    List<BackConsole> tuiBoReload();

}
