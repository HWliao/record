package com.jjshome.im.entity;

import com.sun.javafx.beans.annotations.Default;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 云信账户,登入云信用
 * Created by liaohongwei on 2016/9/23.
 */
@Entity
@Table(name = "nim_account")
public class NIMAccount implements Serializable{

  private static final long serialVersionUID = 8519820529415154938L;
  /**
   * 有效状态
   */
  public static final int STATUS_YX = 1;
  /**
   * 无效状态
   */
  public static final int STATUS_WX = 0;

  public NIMAccount() {
  }

  /**
   * 主键
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /**
   * 对应user表id
   */
  @NotNull
  @Column(name = "user_id")
  private long userId;

  /**
   * nim 账户
   */
  @NotNull
  @Column(name = "acc_id")
  private String accId;

  /**
   * nim 账户密码
   */
  @NotNull
  @Column(name = "token")
  private String token;

  /**
   * 最后的更新日期
   */
  @NotNull
  @Column(name = "update_date")
  private Date updateDate;

  /**
   * 额外信息,json格式
   */
  @Column(name = "prop")
  private String props;

  /**
   * 状态,1 有效,0 无效
   */
  @NotNull
  @Column(name = "status")
  private int status;

  public String getProps() {
    return props;
  }

  public void setProps(String props) {
    this.props = props;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getAccId() {
    return accId;
  }

  public void setAccId(String accId) {
    this.accId = accId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "NIMAccount{" +
      "id=" + id +
      ", userId=" + userId +
      ", accId='" + accId + '\'' +
      ", token='" + token + '\'' +
      ", updateDate=" + updateDate +
      ", props='" + props + '\'' +
      ", status=" + status +
      '}';
  }
}
