package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.TestDao;
import club.ragdollhouse.pojo.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@Service
@Transactional
public class Testservice {

    @Resource
    TestDao testDao;

    public String Test(){
        Test test = testDao.getAll().get(0);
        return test.getAge();
    }
}
