package com.cmbchina.activity.tran.restful.activity.op.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.busi.act.dto.ActActivity;
import com.cmbchina.activity.busi.act.dto.ActivityRequest;
import com.cmbchina.activity.busi.act.dto.ActivityResponse;
import com.cmbchina.activity.busi.act.service.ActivityService;
import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "operation/activity")
public class ActivityController4OP {

  private static final Logger log = LoggerFactory.getLogger(ActivityController4OP.class);

  @Reference
  private ActivityService activityService;

  public void setActivityService(ActivityService activityService){
    this.activityService = activityService;
  }

  /**
   * 活动列表
   * URL: listActivities
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
//  @RequestMapping(value = "listActivities", method = RequestMethod.POST)
  @RequestMapping(value = "listActivities", method = RequestMethod.GET) // TODO GET for Test
  @ResponseBody
  public String listAcitivties(String userId, String roleId, String deptId,
    Date startTime, Date endTime, Date commitTimeStart, Date commitTimeEnd,
    String commitUserName, Byte status){

    ActivityRequest request = new ActivityRequest();
    request.setActivityId("111");
    request.setActivityName("dubbox-->dubbox-demo-->com.alibaba.dubbo.demo.consumer.DemoActivityAction");

    //TODO
    //ActivityResponse response = activityService.queryActivity(request);
    //String result = JSONObject.toJSONString(response);
    return null; //result;
  }

  /**
   * 活动查看
   * URL: getActivityInfo
   * @param actGroupId
   * @return
   */
  @RequestMapping(value = "getActivityInfo", method = RequestMethod.POST)
  @ResponseBody
  public String findActivity(String actGroupId){
    return null;
  }

  /**
   * 活动配置/新增
   * URL: addActivity
   * @param actGroup
   * @param activities
   * @param actExtends
   * @return
   */
  @RequestMapping(value = "addActivity", method = RequestMethod.POST)
  @ResponseBody
  public String addActivity(Object actGroup, List<ActActivity> activities, Object actExtends ){
    return null;
  }

  /**
   * 活动修改
   * URL: updateActivity
   * @param actGroup
   * @param activities
   * @param actExtends
   * @return
   */
  @RequestMapping(value = "updateActivity", method = RequestMethod.POST)
  @ResponseBody
  public String updateActivity(Object actGroup, List<ActActivity> activities, Object actExtends ){
    return null;
  }

  /**
   * 活动提交
   * commitActivity
   * @param actGroupId
   * @return
   */
  @RequestMapping(value = "commitActivity", method = RequestMethod.POST)
  @ResponseBody
  public String commitActivity(String actGroupId){
    return null;
  }

  /**
   * 活动审批
   * URL: approveActivity
   * @param actGroupId
   * @param approvalResult
   * @param approvalMessage
   * @return
   */
  public String approveActivity(String actGroupId, Byte approvalResult, String approvalMessage){
    return null;
  }

  /**
   * 活动上下线
   * URL: setActivityOnline
   * @param actGroupId
   * @param flag
   * @return
   */
  public String setActivityOnline(String actGroupId, Byte flag){
    return null;
  }

  /**
   * 活动效果概览
   * URL: monitorActivity
   * @param actGroupId
   * @param commitUserName
   * @param startTime
   * @param endTime
   * @param areaCodeList
   * @param statusList
   * @return
   */
  public String monitorActivity(String actGroupId, String commitUserName, Date startTime, Date endTime,
    List<String> areaCodeList, List<Byte> statusList ){
    return null;
  }

  /**
   * 活动效果详情
   * URL: monitorActivityDetail
   * @param actGroupId
   * @param time
   * @param status
   * @return
   */
  public String monitorActivityDetail( String actGroupId, Date time, Byte status ){
    return null;
  }

  /**
   * 活动参与详情
   * URL: monitorActivityByUser
   * @param customerId
   * @param actGroupId
   * @param status
   * @return
   */
  public String monitorActivityByUser( String customerId, String actGroupId, Byte status){
    return null;
  }

}
