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
  public NIMAccount registerNIMAccount(long userId);

  /**
   * 根据userid查询nim账户信息
   * @param id
   * @return nim账户信息
   */
  NIMAccount findNIMAccountByUserId(long id);
}
