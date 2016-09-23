package com.jjshome.im.service;

import com.jjshome.im.entity.NIMAccount;

/**
 * 与NIM交互
 * Created by liaohongwei on 2016/9/23.
 */
public interface NIMService {

  /**
   * 注册运行帐号
   * @param userId 用户id
   * @return nim账户信息
   */
  public NIMAccount registerNIMAccount(int userId);
}
