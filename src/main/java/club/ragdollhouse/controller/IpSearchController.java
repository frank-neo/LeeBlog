package club.ragdollhouse.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * ip查询信息控制器
 */

@RestController
public class IpSearchController {

    //搜狐的
    @RequestMapping(value = "/ipInfo",method = RequestMethod.GET)
    public String ipInfo(HttpServletRequest request){
        return "";
    }


    //淘宝api的方法
    @RequestMapping(value = "/ipInfo1", method = RequestMethod.GET)
    public String ipInfo1(HttpServletRequest request) {

        //淘宝IP地址库：http://ip.taobao.com/instructions.php
        String add = null;
        String ip = getIp(request);
        try {
            //URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=114.111.166.72");
            URL U = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) U.openConnection();
            connection.setConnectTimeout(50000);
            connection.setReadTimeout(50000);
            connection.setDoInput(true);
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            in.close();
            JSONObject jsonObject = JSONObject.parseObject(result);
            Map<String, Object> map = (Map) jsonObject;
            String code = String.valueOf(map.get("code"));//0：成功，1：失败。
            if ("1".equals(code)) {//失败
                String data = String.valueOf(map.get("data"));//错误信息
            } else if ("0".equals(code)) {//成功
                Map<String, Object> data = (Map<String, Object>) map.get("data");

                String country = String.valueOf(data.get("country"));//国家
                String area = String.valueOf(data.get("area"));//
                String city = String.valueOf(data.get("city"));//省（自治区或直辖市）
                String region = String.valueOf(data.get("region"));//市（县）
                add = country + "-" + city + "-" + region;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return add;
    }


    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
