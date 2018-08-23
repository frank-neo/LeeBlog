package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.NewsInfoDao;
import club.ragdollhouse.pojo.ReptiliaCheck;
import club.ragdollhouse.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsInfoService {

    @Resource
    NewsInfoDao newsInfoDao;

    public List<ReptiliaCheck> newsInfo(){

        String date = DateUtil.timeStamp2Date(DateUtil.timeStamp(), "yyyy-MM-dd");

        return newsInfoDao.newsInfo(date);
    }
}
