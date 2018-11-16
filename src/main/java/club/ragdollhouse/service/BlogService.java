package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.BlogMapper;
import club.ragdollhouse.pojo.BlogEditor;
import club.ragdollhouse.pojo.BlogType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客业务
 */
@Service
public class BlogService {

    @Resource
    BlogMapper blogMapper;

    //博文列表
    public List<BlogEditor> blogList(int pageNo,int pageSize,String type){
        return blogMapper.blogList(pageNo,pageSize,type);
    }

    //博文种类
    public List<BlogType> blogTypeList(){
        return blogMapper.blogTypeList();
    }

    //文章条数
    public int blogCount(String type){
        return blogMapper.blogCount(type);
    }
}
