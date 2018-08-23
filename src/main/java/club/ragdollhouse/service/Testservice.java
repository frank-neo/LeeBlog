package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.TestDao;
import club.ragdollhouse.pojo.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Testservice {

    @Resource
    TestDao testDao;

    public List<Test> Test(){
        return testDao.getAll();
    }


    public int insertTest(){
        List<Test> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Test test = new Test();
            test.setAge(i+"");
            test.setHobby("爱好"+i);
            test.setLover("爱人"+i);
            test.setName("姓名"+i);
            test.setWork("工作"+i);
            list.add(test);
        }

        int testInt = testDao.insertTest(list);
        return testInt;
    }
}

