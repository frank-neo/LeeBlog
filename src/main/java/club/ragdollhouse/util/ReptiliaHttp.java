package club.ragdollhouse.util;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 爬虫的专属http客户端【测试爬的百度新闻】
 * @author Lee
 */
public class ReptiliaHttp {

    @Test
    public static String ReptiliaResult(String address){

        HttpURLConnection conn = null;
        URL url;
        InputStream in = null;
        BufferedReader reader = null;
        StringBuffer stringBuffer = null;
        try {
            url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.connect();
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
            stringBuffer = new StringBuffer();
            String line ;
            while((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (conn != null) {
                conn.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stringBuffer.toString();
    }
}
