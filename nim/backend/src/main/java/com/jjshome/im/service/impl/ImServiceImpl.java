package com.jjshome.im.service.impl;

import com.jjshome.im.dao.NIMAccountDao;
import com.jjshome.im.dao.UserDao;
import com.jjshome.im.entity.NIMAccount;
import com.jjshome.im.entity.User;
import com.jjshome.im.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liaohongwei on 2016/9/22.
 */
@Service("imService")
public class ImServiceImpl implements ImService {

  @Autowired
  private UserDao userDao;


  @Override
  public User register(User user) {
    return userDao.save(user);
  }

  @Override
  public boolean hasOne(User user) {
    List<User> users = userDao.findByAccount(user.getAccount());
    return users == null||users.size() == 0;
  }
}
