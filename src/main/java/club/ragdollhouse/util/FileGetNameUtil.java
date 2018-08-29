package club.ragdollhouse.util;

import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * java获取指定路径下所有文件的名称
 * idea修改classpath会用到
 */
public class FileGetNameUtil {

    @Test
    public void fileName() {
        //路径
        String path = "E:\\var\\LeeBlog\\out\\artifacts\\LeeBlog_jar";
        //文件数量
        int count;
        File file = new File(path);
        String[] fileName = file.list();
        for (int i = 0; i < (count = fileName.length); i++) {
            System.out.println(fileName[i]);
        }
        System.out.println("文件总数为：" + count + "个。");
    }
}
