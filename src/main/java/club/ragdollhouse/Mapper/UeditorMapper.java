package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.BlogEditor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//ueditor数据库操作
@Mapper
public interface UeditorMapper {

    //博客在线编辑入库操作
    @Insert("INSERT INTO blog(title,author,content,blog_date,TYPE,value_if) " +
            "VALUES(#{blogEditor.title},#{blogEditor.author},#{blogEditor.content},#{blogEditor.blog_date},#{blogEditor.type},'Y')")
    int blog_insert(@Param("blogEditor") BlogEditor blogEditor);
}
