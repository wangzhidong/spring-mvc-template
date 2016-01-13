package com.cmbchina.activity.tran.restful.activity.op.vo;

import java.util.Date;

/**
 * Created by wangtingbang on 16/1/13.
 */
public class ActivityListResponse {
  private String actGroupId;// 活动组ID
  private String quaGroupId;// 资格组ID
  private String actGroupName;// 活动组名
  private Date onlineTime;// 活动上线时间
  private Date offlineTime;// 活动下线时间
  private Date startTime;// 活动开始时间
  private Date endTime;// 活动结束时间
  private int channel;// 活动渠道
  private int status;// 活动状态
  private String commitUserName;// 提交人
  private Date commitTime;// 提交时间

  public ActivityListResponse(){}

  public ActivityListResponse(String actGroupId, String quaGroupId, String actGroupName,
    Date onlineTime, Date offlineTime, Date startTime, Date endTime, int channel, int status,
    String commitUserName, Date commitTime) {
    this.actGroupId = actGroupId;
    this.quaGroupId = quaGroupId;
    this.actGroupName = actGroupName;
    this.onlineTime = onlineTime;
    this.offlineTime = offlineTime;
    this.startTime = startTime;
    this.endTime = endTime;
    this.channel = channel;
    this.status = status;
    this.commitUserName = commitUserName;
    this.commitTime = commitTime;
  }

  public String getActGroupId() {
    return actGroupId;
  }

  public void setActGroupId(String actGroupId) {
    this.actGroupId = actGroupId;
  }

  public String getQuaGroupId() {
    return quaGroupId;
  }

  public void setQuaGroupId(String quaGroupId) {
    this.quaGroupId = quaGroupId;
  }

  public String getActGroupName() {
    return actGroupName;
  }

  public void setActGroupName(String actGroupName) {
    this.actGroupName = actGroupName;
  }

  public Date getOnlineTime() {
    return onlineTime;
  }

  public void setOnlineTime(Date onlineTime) {
    this.onlineTime = onlineTime;
  }

  public Date getOfflineTime() {
    return offlineTime;
  }

  public void setOfflineTime(Date offlineTime) {
    this.offlineTime = offlineTime;
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

  public int getChannel() {
    return channel;
  }

  public void setChannel(int channel) {
    this.channel = channel;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getCommitUserName() {
    return commitUserName;
  }

  public void setCommitUserName(String commitUserName) {
    this.commitUserName = commitUserName;
  }

  public Date getCommitTime() {
    return commitTime;
  }

  public void setCommitTime(Date commitTime) {
    this.commitTime = commitTime;
  }
}
