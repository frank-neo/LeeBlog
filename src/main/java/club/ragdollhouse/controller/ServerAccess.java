package club.ragdollhouse.controller;

import club.ragdollhouse.util.CheckUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerAccess {

    private static Logger logger = Logger.getLogger(ServerAccess.class);

    @RequestMapping(value = "/wechataccess",method = RequestMethod.GET)
    public String accessStat(String signature,String timestamp,String nonce,String echostr){

        logger.info("微信后台发来的接入申请参数:signature ====>"+signature+",timestamp ====>"+timestamp+",nonce ====>"+nonce+",echostr ====>"+echostr);

        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            System.out.print(echostr);        // 校验通过，原样返回echostr参数内容
            return echostr;
        }
        logger.info("微信后台接入失败！");
        return "wrong ServerAccess!";
    }
}
