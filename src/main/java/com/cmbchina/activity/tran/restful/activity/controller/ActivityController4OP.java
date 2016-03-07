package com.cmbchina.activity.tran.restful.activity.controller;

import java.util.Date;
import java.util.List;

import com.cmbchina.commons.bean.BusinessException;
import com.google.common.collect.Lists;
import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.busi.act.dto.ActActivity;
import com.cmbchina.activity.busi.act.dto.ActivityRequest;
import com.cmbchina.activity.busi.act.dto.ActivityResponse;
import com.cmbchina.activity.busi.act.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "operation/activity")
public class ActivityController4OP {

  private static final Logger log = LoggerFactory.getLogger(ActivityController4OP.class);

  @Autowired
  private ActivityService activityService;

  /**
   * 活动列表 URL: listActivities
   * 
   * @param userId
   * @param roleId
   * @param deptId
   * @param startTime
   * @param endTime
   * @param commitTimeStart
   * @param commitTimeEnd
   * @param commitUserName
   * @param status
   * @return
   */
  @RequestMapping(value = "listActivities", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public List listAcitivties(String userId, String roleId, String deptId, Date startTime,
      Date endTime, Date commitTimeStart, Date commitTimeEnd, String commitUserName, Byte status,
      HttpServletRequest request) {

    String cookies = request.getCookies().toString();
    String session = request.getSession().toString();
    log.info("cookies:{}, session:{}", cookies, session);
    try {
      List result = activityService.listActivities(null, userId, roleId, deptId, startTime, endTime,
          commitTimeStart, commitTimeEnd, commitUserName, Lists.newArrayList(status));
      return result;
    } catch (BusinessException e) {
      log.error("error:{}", e);
      return null;
    } catch (Exception e) {
      log.error("error:{}", e);
      return null;
    }
  }

  /**
   * 活动查看 URL: getActivityInfo
   * 
   * @param actGroupId
   * @return
   */
  @RequestMapping(value = "getActivityInfo", method = RequestMethod.POST)
  @ResponseBody
  public String findActivity(String actGroupId) {

    return null;
  }

  /**
   * 活动配置/新增 URL: addActivity
   * 
   * @param actGroup
   * @param activities
   * @param actExtends
   * @return
   */
  @RequestMapping(value = "addActivity", method = RequestMethod.POST)
  @ResponseBody
  public String addActivity(Object actGroup, List<ActActivity> activities, Object actExtends) {
    return null;
  }

  /**
   * 活动修改 URL: updateActivity
   * 
   * @param actGroup
   * @param activities
   * @param actExtends
   * @return
   */
  @RequestMapping(value = "updateActivity", method = RequestMethod.POST)
  @ResponseBody
  public String updateActivity(Object actGroup, List<ActActivity> activities, Object actExtends) {
    return null;
  }

  /**
   * 活动提交 commitActivity
   * 
   * @param actGroupId
   * @return
   */
  @RequestMapping(value = "commitActivity", method = RequestMethod.POST)
  @ResponseBody
  public String commitActivity(String actGroupId) {
    return null;
  }

  /**
   * 活动审批 URL: approveActivity
   * 
   * @param actGroupId
   * @param approvalResult
   * @param approvalMessage
   * @return
   */
  @RequestMapping(value = "approveActivity", method = RequestMethod.POST)
  public String approveActivity(String actGroupId, Byte approvalResult, String approvalMessage) {
    return null;
  }

  /**
   * 活动上下线 URL: setActivityOnline
   * 
   * @param actGroupId
   * @param flag
   * @return
   */
  @RequestMapping(value = "setActivityOnline", method = RequestMethod.POST)
  public String setActivityOnline(String actGroupId, Byte flag) {
    return null;
  }

  /**
   * 活动效果概览 URL: monitorActivity
   * 
   * @param actGroupId
   * @param commitUserName
   * @param startTime
   * @param endTime
   * @param areaCodeList
   * @param statusList
   * @return
   */
  @RequestMapping(value = "monitorActivity", method = RequestMethod.POST)
  public String monitorActivity(String actGroupId, String commitUserName, Date startTime,
      Date endTime, List<String> areaCodeList, List<Byte> statusList) {
    return null;
  }

  /**
   * 活动效果详情 URL: monitorActivityDetail
   * 
   * @param actGroupId
   * @param time
   * @param status
   * @return
   */
  @RequestMapping(value = "monitorActivityDetail", method = RequestMethod.POST)
  public String monitorActivityDetail(String actGroupId, Date time, Byte status) {
    return null;
  }

  /**
   * 活动参与详情 URL: monitorActivityByUser
   * 
   * @param customerId
   * @param actGroupId
   * @param status
   * @return
   */
  @RequestMapping(value = "monitorActivityByUser", method = RequestMethod.POST)
  public String monitorActivityByUser(String customerId, String actGroupId, Byte status) {
    return null;
  }

}
