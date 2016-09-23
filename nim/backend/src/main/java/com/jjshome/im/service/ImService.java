package com.jjshome.im.service;

import com.jjshome.im.entity.NIMAccount;
import com.jjshome.im.entity.User;

import java.util.Map;

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

  /**
   * 验证用户是否有登入权限
   * @param user 登入用户
   * @return 用户省份信息,包括nim账户信息,用户信息
   */
  Map<String,Object> authenticate(User user);
}
