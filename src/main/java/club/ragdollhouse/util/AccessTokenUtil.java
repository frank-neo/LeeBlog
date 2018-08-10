package club.ragdollhouse.util;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取access_token模块
 */
public class AccessTokenUtil {

    private static Logger logger = Logger.getLogger(AccessTokenUtil.class);

    //微信公众号控制台可查
    //appID
    private static String appID = "wx349041c1355d4ab7";
    //appSecret
    private static String appSecret = "655495e1ef39b7ec0f19e2b5de1c955b";

    private static String accessToken;

    /**
     * http使用get方法获取access_token实例
     *
     * @return access_token
     */
    public static String getStringAccessToken() throws IOException {

        //设置地址
        URL conn_url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appID + "&secret=" + appSecret);
        //新建http客户端
        HttpURLConnection conn = (HttpURLConnection) conn_url.openConnection();

        try {
            //get方式
            conn.setRequestMethod("GET");
            // 5秒 从主机读取数据的超时时间（单位：毫秒）
            conn.setReadTimeout(5000);
            // 5秒 连接主机的超时时间（单位：毫秒）
            conn.setConnectTimeout(5000);
            //建立连接
            conn.connect();

            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str;
                StringBuffer buffer = new StringBuffer();
                //如果返回的结果有/n，那么这个解析是有bug的
                while ((str = bufferedReader.readLine()) != null) {
                    buffer = buffer.append(str);
                }
                //关闭io资源
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                accessToken = buffer.toString();
                logger.info("accesstokenHttp状态返回正常");

            } else {
                accessToken = null;
                logger.info("微信控制台返回状态码异常，ResponseCode：" + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭http连接，释放资源
            conn.disconnect();
        }
        return accessToken;
    }
}
