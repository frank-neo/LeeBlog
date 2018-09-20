package club.ragdollhouse.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传接口
 */
@RestController
public class ueditImagController {
    //这里upfile是config.json中图片提交的表单名称
    @RequestMapping(value = "/UedtorConfig", method = RequestMethod.POST)
    public Map<String, String> screenshot(@RequestParam Map<String, String> mapParam, MultipartFile upfile) {
        String action = mapParam.get("action");
        Map<String, String> map = new HashMap<>();
        if (action == null) {
            map.put("state", "FAILURE");
            return map;
        } else if (action.equals("uploadimage")) {
            if (upfile.isEmpty()) {
                map.put("state", "FAILURE");
                return map;
            }
            String fileName = upfile.getOriginalFilename();
            int size = (int) upfile.getSize();

            //上传位置路径
            String path = "E:/testUeditorFile/111";
            //为了避免重复简单处理
            String nowName = new Date().getTime() + "_" + fileName;
            File dest = new File(path + "/" + nowName);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            String  pathcopy = "E:/testUeditorFile/222";
            File copyfile = new File(pathcopy +"/"+nowName);
            if (!copyfile.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                upfile.transferTo(dest); //保存文件
                FileCopyUtils.copy(dest,copyfile);
                //是否上传成功
                map.put("state", "SUCCESS");
                //现在文件名称
                map.put("title", nowName);
                //文件原名称
                map.put("original", fileName);
                //文件类型 .+后缀名
                map.put("type", fileName.substring(upfile.getOriginalFilename().lastIndexOf(".")));
                //文件路径
                map.put("url", path + "/" + nowName);
                //文件大小（字节数）
                map.put("size", upfile.getSize() + "");
                return map;
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("state", "FAILURE");
                return map;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("state", "FAILURE");
                return map;
            }
        } else {
            map.put("state", "SUCCESS");
            return map;
        }
    }
}
