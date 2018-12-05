package club.ragdollhouse.service;

import club.ragdollhouse.Mapper.BackConsoleMapper;
import club.ragdollhouse.pojo.BackConsole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 首页后台控制发布服务
 */
@Service
public class BackConsoleService {

    @Resource
    BackConsoleMapper backConsoleMapper;

    //首页后台控制发布title数据入库
    public int titleInsert(BackConsole backConsole){
        return backConsoleMapper.titleInsert(backConsole);
    }

}
