package com.cmbchina.activity.tran.restful.test.controller;

import java.util.Date;

/**
 * Created by wangtingbang on 16/1/13.
 */
public class TestVO {

  private String id;
  private int status;
  private String name;
  private boolean isNew;
  private String description;
  private Date createTime;

  public TestVO() {
  }

  public TestVO(String id, int status, String name, boolean isNew, String description,
                Date createTime) {
    this.id = id;
    this.status = status;
    this.name = name;
    this.isNew = isNew;
    this.description = description;
    this.createTime = createTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isNew() {
    return isNew;
  }

  public void setNew(boolean isNew) {
    this.isNew = isNew;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
