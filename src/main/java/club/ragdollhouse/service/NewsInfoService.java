package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.NewsInfoDao;
import club.ragdollhouse.pojo.DatePojo;
import club.ragdollhouse.pojo.ReptiliaCheck;
import club.ragdollhouse.pojo.TagPojo;
import club.ragdollhouse.util.DateUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsInfoService {

    @Resource
    NewsInfoDao newsInfoDao;

    //新闻列表
    public List<ReptiliaCheck> newsInfo(String date,int pageNo,int pageSize){

        return newsInfoDao.newsInfo(date,pageNo,pageSize);
    }

    //新闻数量
    public int countNews(String date){

        return newsInfoDao.countNews(date);
    }

    //往期日期列表
    public List<DatePojo> beforeDateList(){

        List<DatePojo> dateList = new ArrayList<>();
        for (int i = 1; i <10 ; i++) {
            DatePojo datePojo = new DatePojo();
            datePojo.setDate(DateUtil.beforeDate(i));
            datePojo.setClasses("icon-angle-right");
            dateList.add(datePojo);
        }
        return dateList;
    }

    //标签列表
    public List<TagPojo> tagList(){

        return newsInfoDao.tagList();
    }
}
