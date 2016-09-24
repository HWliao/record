package com.jjshome.im.controller;

import com.jjshome.im.command.RegisterCommand;
import com.jjshome.im.entity.NIMAccount;
import com.jjshome.im.entity.User;
import com.jjshome.im.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Im控制器
 * Created by liaohongwei on 2016/9/22.
 */
@RestController
public class ImController {
  @Autowired
  private ImService imService;

  @RequestMapping("/im/register")
  public Object register(RegisterCommand command){
    User user = new User();
    user.setAccount(command.getAccount());
    user.setNick(command.getNick());
    user.setPwd(command.getPwd());
    boolean flag = imService.hasOne(user);
    if(!flag){
      user = imService.register(user);
      return ControllerUtil.wrapSuccessRes(user);
    }else{
      return ControllerUtil.wrapFailRes();
    }
  }

  @RequestMapping("/im/login")
  public Object login(RegisterCommand command){
    User user = new User();
    user.setAccount(command.getAccount());
    user.setPwd(command.getPwd());
    Map<String,Object> result = this.imService.authenticate(user);
    if(result == null){
      return ControllerUtil.wrapFailRes();
    }else{
      return ControllerUtil.wrapSuccessRes(result);
    }
  }

}
