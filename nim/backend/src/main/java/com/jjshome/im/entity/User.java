package com.jjshome.im.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * user实体类
 * Created by liaohongwei on 2016/9/22.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 7046114235942185969L;

  /**
   * 主键id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /**
   * 账户
   */
  @NotNull
  private String account;

  /**
   * 昵称
   */
  @NotNull
  private String nick;

  /**
   * 密码
   */
  @NotNull
  private String pwd;

  public User() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
    return "User{" +
      "id=" + id +
      ", account='" + account + '\'' +
      ", nick='" + nick + '\'' +
      ", pwd='" + pwd + '\'' +
      '}';
  }
}
