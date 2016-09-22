package com.jjshome.im.dao.user;

import com.jjshome.im.dao.UserDao;
import com.jjshome.im.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试user表crud
 * Created by liaohongwei on 2016/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

  @Autowired
  UserDao userDao;

  @Test
  public void add(){
    User user = new User();
    user.setAccount("test");
    user.setNick("test");
    user.setPwd("test");
    user = userDao.save(user);
    System.out.print(user.toString());
  }

  @Test
  public void find(){
    List<User> users = (ArrayList<User>) userDao.findAll();
    System.out.print(users.toString());
  }

  @Test
  public void delete(){
    List<User> users = (ArrayList<User>) userDao.findAll();
    userDao.delete(users.get(0));
  }
  @Test
  public void findByAccount(){
    List<User> users = userDao.findByAccount("test");
    System.out.print(users.toString());
  }

}
