package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.ReptiliaCheckMapper;
import club.ragdollhouse.pojo.ReptiliaCheck;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReptiliaService {

    @Resource
    ReptiliaCheckMapper reptiliaCheckMapper;

    //爬取内容入库
    public int reptiliaInsert(List<ReptiliaCheck> list){

        return reptiliaCheckMapper.insertReptiliaCheck(list);
    }
}
