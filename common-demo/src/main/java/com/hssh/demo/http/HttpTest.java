package com.hssh.demo.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created by hssh on 2017/9/15.
 */
public class HttpTest
{

    private static CloseableHttpClient genHttpClient() {
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        requestConfigBuilder.setConnectTimeout(5000).setConnectionRequestTimeout(10000);
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfigBuilder.build()).build();

        return httpclient;
    }

    private static HttpResult genHttpResult(CloseableHttpResponse response) {
        HttpResult httpResult = new HttpResult();

        if(response != null) {
            try {
                httpResult.setStatus(response.getStatusLine().getStatusCode());
                InputStream inputStream = response.getEntity().getContent();
                StringWriter writer = new StringWriter();
                IOUtils.copy(inputStream, writer, "utf-8");
                httpResult.setContent(writer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return httpResult;
    }

    public static HttpResult get(String url) {

        // 设置连接时间为5秒
        CloseableHttpClient httpClient = genHttpClient();

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return genHttpResult(response);

    }

    public static HttpResult getShortConn(String url) {

        // 设置连接时间为5秒
        CloseableHttpClient httpClient = genHttpClient();

        HttpGet httpGet = new HttpGet(url);

        httpGet.setProtocolVersion(HttpVersion.HTTP_1_0);
        httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return genHttpResult(response);

    }


    public static HttpResult post(String url, List<NameValuePair> nvps) {
        CloseableHttpClient httpClient = genHttpClient();

        HttpPost httpPost = new HttpPost(url);
        //拼接参数
        //List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //nvps.add(new BasicNameValuePair("username", "vip"));
        //nvps.add(new BasicNameValuePair("password", "secret"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return genHttpResult(response);
    }

    public static void httpsGet(String url) throws IOException
    {
        // 创建URL对象
        URL myURL = new URL(url);

        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();

        // 取得该连接的输入流，以读取响应内容
        InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());

        // 读取服务器的响应内容并显示
        int respInt = insr.read();
        while (respInt != -1) {
            System.out.print((char) respInt);
            respInt = insr.read();
        }
    }
}
