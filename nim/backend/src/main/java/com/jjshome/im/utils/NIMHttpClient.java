package com.jjshome.im.utils;

import com.jjshome.im.entity.NIMAccount;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 用于链接nim
 * Created by liaohongwei on 2016/9/23.
 */
public class NIMHttpClient {
  /**
   * 创建云信id
   */
  public static final String URL_CREATE_NIM_ACCID = "https://api.netease.im/nimserver/user/create.action";

  /**
   * 链接nim统一处理方法
   *
   * @param url       url地址
   * @param appKey    nim appkey
   * @param appSecret nim appSecret
   * @param nonce     随机数
   * @param curDate   当前时间
   * @param nvps      form表单参数
   * @return 响应
   */
  public static HttpResponse doHttpPost(String url, String appKey, String appSecret, String nonce,
                                        Date curDate, List<NameValuePair> nvps) throws IOException {

    HttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);

    String curTime = String.valueOf(curDate.getTime() / 1000L);
    String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);

    // 设置请求的header
    post.addHeader("AppKey", appKey);
    post.addHeader("Nonce", nonce);
    post.addHeader("CurTime", curTime);
    post.addHeader("CheckSum", checkSum);
    post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    // 设置请求参数
    post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

    HttpResponse response = client.execute(post);
    return response;
  }

  public static void main(String[] args) throws IOException {
    Random random = new Random(100000000);

    String appKey = "7ca1eb5e5cd9416289ef35d78c67085a";
    String appSecret = "d85b48dadd51";
    String nonce = String.valueOf(random.nextInt());
    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("accid", "124567"));
    HttpResponse response = doHttpPost(URL_CREATE_NIM_ACCID, appKey, appSecret, nonce, new Date(), nvps);
    System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));
  }
}
