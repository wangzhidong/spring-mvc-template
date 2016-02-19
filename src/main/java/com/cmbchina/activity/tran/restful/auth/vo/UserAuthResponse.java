package com.cmbchina.activity.tran.restful.auth.vo;

/**
 * Created by wangtingbang on 16/1/29.
 */
public class UserAuthResponse {
  /**
   * session id
   */
  private String session;

  /**
   * 用户认证Token
   */
  private String token;

  /**
   * 跳转URL
   */
  private String forward;

  /**
   * 用户角色
   */
  private Byte roleId;

  /**
   * 用户部门ID
   */
  private String deptId;

  /**
   * 用户部门
   */
  private String deptName;

  public UserAuthResponse() {
  }

  public String getSession() {
    return session;
  }

  public void setSession(String session) {
    this.session = session;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getForward() {
    return forward;
  }

  public void setForward(String forward) {
    this.forward = forward;
  }

  public Byte getRoleId() {
    return roleId;
  }

  public void setRoleId(Byte roleId) {
    this.roleId = roleId;
  }

  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
}
