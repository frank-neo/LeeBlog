package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.NewsInfoDao;
import club.ragdollhouse.pojo.ReptiliaCheck;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsInfoService {

    @Resource
    NewsInfoDao newsInfoDao;

    public List<ReptiliaCheck> newsInfo(String date,int pageNo,int pageSize){

        return newsInfoDao.newsInfo(date,pageNo,pageSize);
    }

    public int countNews(String date){

        return newsInfoDao.countNews(date);
    }
}
