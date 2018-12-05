package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.HtmlServiceMapper;
import club.ragdollhouse.pojo.BackConsole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 页面渲染业务
 */
@Service
public class HtmlService {

    @Resource
    HtmlServiceMapper htmlServiceMapper;

    //标题查询
    public List<BackConsole> indexPageTitle() {
        return htmlServiceMapper.titleRelaoad();
    }
    //咨询

    public List<BackConsole> indexZiXun() {
        return htmlServiceMapper.ziXunReload();
    }
    //推博
    public List<BackConsole> indexTuiBo(){
        return htmlServiceMapper.tuiBoReload();
    }
}
