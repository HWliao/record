package com.jjshome.im.service;

import com.jjshome.im.entity.User;

/**
 * im先关操作
 * Created by liaohongwei on 2016/9/22.
 */
public interface ImService {
  /**
   * 注册用户
   * @param user 注册用用户信息
   * @return 注册完成后的用户信息
   */
  public User register(User user);

  public boolean isOne(User user);
}
