package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.UeditorMapper;
import club.ragdollhouse.pojo.BlogEditor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Ueditor工具业务类
 */
@Service
public class UeditorService {

    @Resource
    UeditorMapper ueditorMapper;

    //博文入库
    public int blog_insert(BlogEditor blogEditor){
        return ueditorMapper.blog_insert(blogEditor);
    }
}
