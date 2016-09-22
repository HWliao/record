package com.jjshome.im.command;

import java.io.Serializable;

/**
 * 接收前端参数
 * Created by liaohongwei on 2016/9/22.
 */
public class RegisterCommand implements Serializable {
  private static final long serialVersionUID = 2028624332618236183L;

  private String account;

  private String nick;

  private String pwd;

  public RegisterCommand() {
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  @Override
  public String toString() {
    return "RegisterCommand{" +
      "account='" + account + '\'' +
      ", nick='" + nick + '\'' +
      ", pwd='" + pwd + '\'' +
      '}';
  }
}
