package com.info.client;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/9/7 / 17:21
 * 获取公众号新闻内容客户端
 */

public class NewsClient {

    @Value("${news-appID}")
    private String appId;

    @Value("${news-appSecret}")
    private String appSecret;


    private String getToken() throws  IOException {
        String path = " https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
        URL url = new URL(path+"&appid=" + appId + "&secret=" + appSecret);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream in = connection.getInputStream();
        byte[] b = new byte[100];
        int len = -1;
        StringBuffer sb = new StringBuffer();
        while((len = in.read(b)) != -1) {
            sb.append(new String(b,0,len));
        }
        in.close();
        return sb.toString();
    }

    //获取文章列表
    public String getContentList(int offset,int count) throws IOException {
        String token=this.getToken();
        String path = " https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("content-type", "application/json;charset=utf-8");
        connection.connect();
        // post发送的参数
        Map<String, Object> map = new HashMap<>();
        map.put("type", "news"); // news表示图文类型的素材，具体看API文档
        map.put("offset", offset);
        map.put("count", count);
        // 将map转换成json字符串
        String paramBody = JSON.toJSONString(map);

        OutputStream out = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(paramBody); // 向流中写入参数字符串
        bw.flush();

        InputStream in = connection.getInputStream();
        byte[] b = new byte[100];
        int len = -1;
        StringBuffer sb = new StringBuffer();
        while((len = in.read(b)) != -1) {
            sb.append(new String(b,0,len));
        }

        in.close();
        return sb.toString();
    }

}
