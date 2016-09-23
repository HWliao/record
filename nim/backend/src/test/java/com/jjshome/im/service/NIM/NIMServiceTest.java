package com.jjshome.im.service.NIM;

import com.jjshome.im.entity.NIMAccount;
import com.jjshome.im.service.NIMService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * NIM服务测试
 * Created by liaohongwei on 2016/9/23.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NIMServiceTest {
  @Autowired
  private NIMService nimService;

  @Test
  public void register(){
    NIMAccount acc = this.nimService.registerNIMAccount(2);
    System.out.println(acc);
  }

}
