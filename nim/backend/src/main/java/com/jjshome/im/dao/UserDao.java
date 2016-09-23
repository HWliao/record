package com.jjshome.im.dao;

import com.jjshome.im.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * user表操作
 * Created by liaohongwei on 2016/9/22.
 */
public interface UserDao extends CrudRepository<User,Long> {

  /**
   * 根据account查询用户
   * @param account 账户
   * @return 用户
   */
  @Query("select u from User u where u.account =:account")
  List<User> findByAccount(@Param("account") String account);

  @Query("select u from User u where u.account=:account and u.pwd=:pwd")
  List<User> finByAccountAndPwd(@Param("account") String account, @Param("pwd") String pwd);
}
