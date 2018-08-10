package club.ragdollhouse.util;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 因为经常调取微信后台接口。我们封装一个Http客户端的工具类
 */
public class HttpClient {

    private static Logger logger = Logger.getLogger(HttpClient.class);

    //get方法
    public void doGet() {

    }

    //post方法
    public String doPost(String url, String access_token, String json) {

        String result = "";
        BufferedReader reader = null;
        try {
            URL conn_url = new URL(url + access_token);
            HttpURLConnection conn = (HttpURLConnection) conn_url.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
/*           http的keep-alive是说，你发出的这个请求建议服务器端保留连接，这样下次你向同一个服务器发请求时可以走同一个连接。
            这里的连接是指tcp连接。首先，http是无状态的；其次，keep-alive只是一个建议，是否真能保持连接取决于双方的实现。
            假如服务器不支持，你就算请求头里有keep-alive，服务器发回的响应也可能是close。*/
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept", "application/json");
            if (json != null) {
                byte[] writebytes = json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                logger.info("doJsonPost: resposeStatu：" + conn.getResponseCode());

                if (conn.getResponseCode() == 200) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    //如果返回的结果有/n，那么这个解析是有bug的
                    result = reader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
