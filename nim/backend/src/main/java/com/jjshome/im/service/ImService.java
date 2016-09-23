package com.jjshome.im.service;

import com.jjshome.im.entity.NIMAccount;
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

  /**
   * 是否已经存在了
   * @param user
   * @return 存在 true 不存在 false
   */
  public boolean hasOne(User user);
}
