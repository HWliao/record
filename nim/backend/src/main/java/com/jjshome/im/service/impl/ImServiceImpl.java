package com.jjshome.im.service.impl;

import com.jjshome.im.dao.NIMAccountDao;
import com.jjshome.im.dao.UserDao;
import com.jjshome.im.entity.NIMAccount;
import com.jjshome.im.entity.User;
import com.jjshome.im.service.ImService;
import com.jjshome.im.service.NIMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liaohongwei on 2016/9/22.
 */
@Service("imService")
public class ImServiceImpl implements ImService {

  @Autowired
  private UserDao userDao;
  @Autowired
  private NIMService nimService;
  @Value("${nim.appkey}")
  private String appKey;

  @Override
  public User register(User user) {
    user = userDao.save(user);
    // 注册云信帐号,并绑定用户
    nimService.registerNIMAccount(user.getId());
    return user;
  }

  @Override
  public boolean hasOne(User user) {
    List<User> users = userDao.findByAccount(user.getAccount());
    return users != null&&users.size() != 0;
  }

  @Override
  public Map<String, Object> authenticate(User user) {
    List<User> tmps = this.userDao.finByAccountAndPwd(user.getAccount(),user.getPwd());
    if(tmps != null && tmps.size() == 1){
      NIMAccount account = this.nimService.findNIMAccountByUserId(tmps.get(0).getId());
      Map<String,Object> result = new HashMap<String,Object>();
      result.put("appKey",this.appKey);
      result.put("nimAccount",account);
      result.put("user",tmps.get(0));
      return result;
    }else{
      return null;
    }
  }
}
