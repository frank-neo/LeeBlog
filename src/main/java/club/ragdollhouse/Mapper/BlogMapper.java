package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.BlogEditor;
import club.ragdollhouse.pojo.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 博客mapper
 */
@Mapper
public interface BlogMapper {

    //查询博文列表
    @Select("<script>" +
                "SELECT id,title,author,content,blog_date,TYPE,samary FROM blog " +
                "<choose>"+
                    "<when test=\"type==null\">"+
                        "WHERE value_if = 'Y' limit #{pageNo},#{pageSize}" +
                    "</when>"+
                    "<otherwise>"+
                        "WHERE value_if = 'Y' and type = #{type} limit #{pageNo},#{pageSize}"+
                    "</otherwise>"+
                "</choose>"+
            "</script>")
    List<BlogEditor> blogList(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize,@Param("type") String type);

    //查询博客类别
    @Select("SELECT DISTINCT TYPE FROM blog WHERE value_if = 'Y'")
    List<BlogType> blogTypeList();

    //文章条数
    @Select("<script>" +
                "SELECT COUNT(1) FROM blog " +
                "<choose>" +
                    "<when test=\"type==null\">" +
                        "where 1 = 1" +
                    "</when>" +
                    "<otherwise>" +
                        "where type = #{type}" +
                    "</otherwise>" +
                "</choose>" +
            "</script>")
    int blogCount(@Param("type") String type);

    //博客明细
    @Select("SELECT title,author,content,blog_date,TYPE FROM blog WHERE value_if = 'Y' AND id = #{id}")
    BlogEditor blogDetail(@Param("id") int id);
}
