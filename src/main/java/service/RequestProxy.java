package service;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RequestProxy {

    public static String request(String prompt, String token) {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost("https://api-inference.huggingface.co/models/HuggingFaceH4/zephyr-7b-beta");
        // 设置长连接
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Content-Type", "application/json");
        // 设置 Cookie
        httpPost.setHeader("Authorization", "Bearer " + token);

        CloseableHttpResponse httpResponse = null;
        try {
            // 创建 HttpPost 参数
            StringEntity entity = new StringEntity("{\"inputs\":\"" + prompt + "\"}","UTF-8");
            // 设置 HttpPost 参数
            httpPost.setEntity(entity);
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            // 输出请求结果
            return EntityUtils.toString(httpEntity, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 无论如何必须关闭连接
        finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
