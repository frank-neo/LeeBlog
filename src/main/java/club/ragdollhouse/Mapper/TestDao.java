package club.ragdollhouse.Mapper;

import club.ragdollhouse.pojo.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestDao {

    /**
     * mybatis查询
     */
    @Select("select * from test")
    List<Test> getAll();

    /**
     * 测试批量插入
     *
     * @param list list
     * @return 返回的数字是插入数据的条数
     */
    @Insert("<script>" +
            "insert into test(name,age,hobby,work,lover) VALUES " +
            "<foreach item='item' index='index' collection='list' separator=',' >" +
            "(#{item.name},#{item.age},#{item.hobby},#{item.work},#{item.lover})" +
            "</foreach>" +
            "</script>")
    int insertTest(@Param("list") List<Test> list);


    //测试事务第一步插入
    @Insert("INSERT INTO test(NAME,age,hobby,WORK,lover) VALUES('qwe','66666666','lol','ppp','asd');")
    int testTransacionPart1();
    //测试事务第二步插入【报错sql】
    @Insert("INSERT INTO test_transacion(testname,age) VALUES('test1111',(SELECT age FROM test WHERE NAME = 'qwe'));")
    int testTransacionPart2();

}
