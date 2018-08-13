package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface  TestDao {

    /**
     * mybatis查询
     */
    @Select("select * from test")
    List<Test> getAll();

}
