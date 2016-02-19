package com.cmbchina.activity.tran.restful.auth.vo;

/**
 * Created by wangtingbang on 16/1/29.
 */
public class UserAuthRequest {
  /**
   * 登录名
   */
  private String loginName;

  /**
   * 密码
   */
  private String password;

  public UserAuthRequest() {
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
