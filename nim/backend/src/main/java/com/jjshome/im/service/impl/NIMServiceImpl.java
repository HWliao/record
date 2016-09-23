package com.jjshome.im.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jjshome.im.dao.NIMAccountDao;
import com.jjshome.im.entity.NIMAccount;
import com.jjshome.im.service.NIMService;
import com.jjshome.im.utils.CheckSumBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;

/**
 * Created by liaohongwei on 2016/9/23.
 */
@Service
public class NIMServiceImpl implements NIMService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * http客户端
   */
  private CloseableHttpClient httpClient;

  /**
   * 实例化httpClient
   */
  @PostConstruct
  private void init(){
    this.httpClient = HttpClients.createDefault();
  }

  /**
   * 回收httpClient
   */
  @PreDestroy
  private void destroy(){
    try {
      this.httpClient.close();
    } catch (IOException e) {
      this.logger.error("NIMServiceImpl@destroy:close http client error!e:",e);
    }
  }

  /**
   * 创建云信id
   */
  public static final String URL_CREATE_NIM_ACCID = "https://api.netease.im/nimserver/user/create.action";

  @Autowired
  private NIMAccountDao nimAccountDao;

  /**
   * 创建云信息账户并绑定系统用户
   * @param userId 用户id
   * @return
   */
  @Override
  public NIMAccount registerNIMAccount(long userId) {
    NIMAccount account = null;

    String accId = UUID.randomUUID().toString().replace("-","");
    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    nvps.add(new BasicNameValuePair("accid",accId));

    try {
      JSONObject jobj = this.doPost(URL_CREATE_NIM_ACCID,nvps);
      // nim 服务状态码
      String code = jobj.getString("code");

      if(!"200".equals(code)){
        this.logger.error("NIMServiceImpl@registerNIMAccount:url="+URL_CREATE_NIM_ACCID+",accid="+accId+
          ",result="+jobj.toJSONString());
        return account;
      }

      JSONObject info = jobj.getJSONObject("info");
      account = new NIMAccount();
      account.setUserId(userId);
      account.setAccId(info.getString("accid"));
      account.setStatus(NIMAccount.STATUS_YX);
      account.setToken(info.getString("token"));
      account.setProps("");
      account.setUpdateDate(new Date());

      account = this.nimAccountDao.save(account);

    } catch (IOException e) {
      this.logger.error("NIMServiceImpl@registerNIMAccount:url="+URL_CREATE_NIM_ACCID+",accid="+accId,e);
    }
    return account;
  }

  @Override
  public NIMAccount findNIMAccountByUserId(long id) {
    List<NIMAccount> accs = this.nimAccountDao.findByUserId(id);
    if(accs == null || accs.size() == 0 || accs.size()> 1){
      this.logger.error("NIMServiceImpl@findNIMAccountByUserId:userId="+id);
      throw new RuntimeException("系统数据异常!");
    }else{
      return accs.get(0);
    }
  }


  @Value("${nim.appkey}")
  private String appKey;
  @Value("${nim.appsecret}")
  private String appSecret;
  /**
   * post请求nim
   * @param url nim 服务地址
   * @param nvps form表单参数
   * @return json对象
   * @throws IOException
   */
  private JSONObject doPost(String url,List<NameValuePair> nvps) throws IOException{
    Random random = new Random(100000000);
    String nonce = String.valueOf(random.nextInt());
    String curTime = String.valueOf(new Date().getTime() / 1000L);
    String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);

    HttpPost post = new HttpPost(url);
    // 设置请求的header
    post.addHeader("AppKey", this.appKey);
    post.addHeader("Nonce", nonce);
    post.addHeader("CurTime", curTime);
    post.addHeader("CheckSum", checkSum);
    post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    // 设置请求参数
    post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

    CloseableHttpResponse response = this.httpClient.execute(post);
    StatusLine statusLine = response.getStatusLine();

    JSONObject result = new JSONObject();
    if(200 !=statusLine.getStatusCode()){
      this.logger.error("NIMServiceImpl@doPost:[request]:"+EntityUtils.toString(post.getEntity(),"utf-8")+
        ",[response]:status="+statusLine);
      return result;
    }

    try {
      String body = EntityUtils.toString(response.getEntity(),"utf-8");

      this.logger.debug("NIMServiceImpl@doPost:[request]:"+EntityUtils.toString(post.getEntity(),"utf-8")+
        ",[response]:status="+statusLine+",entity:"+body);

      result = JSON.parseObject(body);

      EntityUtils.consume(response.getEntity());
    }finally {
      response.close();
    }
    return result;
  }
}
