package com.cmbchina.activity.tran.restful.activity.h5.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmbchina.activity.busi.act.service.ActivityService;
import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangtingbang on 16/1/26.
 */
@Controller
@RequestMapping(value = "h5/activity")
public class ActivityController4H5 {
  private static final Logger log = LoggerFactory.getLogger(ActivityController4H5.class);

  @Reference
  private ActivityService activityService;

  public void setActivityService(ActivityService activityService){
    this.activityService = activityService;
  }


  /**
   * 手机端活动列表
   * URL: getActivityInfo4H5
   * @param areaCode
   * @param channel
   * @return
   */
  public String listActivities4H5(String areaCode, Byte channel){
    return null;
  }

  /**
   * 手机端活动查看
   * URL: getActivityInfo4H5
   * @param activityId
   * @return
   */
  public String getActivityInfo4H5(String activityId){
    return null;
  }

  /**
   * 活动参与(领取、抽奖)
   * URL: partakeActivity
   * @param customerId
   * @param actGroupId
   * @param activityId
   * @return
   */
  public String partakeActivity(String customerId, String actGroupId, String activityId ){
    return null;
  }
}
