package com.cmbchina.activity.tran.restful.activity.op.vo;

import java.util.Date;

/**
 * Created by wangtingbang on 16/1/13.
 */
public class ActivityRequest {

  private String roleId; //角色ID
  private String deptId; //部门ID
  private Date startTime; //活动时间起始
  private Date endTime; //活动时间结束
  private Date commitTimeStart; //提交时间起始
  private Date commitTimeEnd; //提交时间结束
  private Date commitUserName; //提交人
  private Byte[] statusList; //活动状态

  public ActivityRequest(){}

  public ActivityRequest(String roleId, String deptId, Date startTime, Date endTime,
    Date commitTimeStart, Date commitTimeEnd, Date commitUserName, Byte[] statusList) {
    this.roleId = roleId;
    this.deptId = deptId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.commitTimeStart = commitTimeStart;
    this.commitTimeEnd = commitTimeEnd;
    this.commitUserName = commitUserName;
    this.statusList = statusList;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Date getCommitTimeStart() {
    return commitTimeStart;
  }

  public void setCommitTimeStart(Date commitTimeStart) {
    this.commitTimeStart = commitTimeStart;
  }

  public Date getCommitTimeEnd() {
    return commitTimeEnd;
  }

  public void setCommitTimeEnd(Date commitTimeEnd) {
    this.commitTimeEnd = commitTimeEnd;
  }

  public Date getCommitUserName() {
    return commitUserName;
  }

  public void setCommitUserName(Date commitUserName) {
    this.commitUserName = commitUserName;
  }

  public Byte[] getStatusList() {
    return statusList;
  }

  public void setStatusList(Byte[] statusList) {
    this.statusList = statusList;
  }
}
