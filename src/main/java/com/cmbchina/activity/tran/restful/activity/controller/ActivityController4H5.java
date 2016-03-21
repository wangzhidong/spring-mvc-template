package com.cmbchina.activity.tran.restful.activity.controller;

import com.cmbchina.activity.busi.act.dto.ActBusiContext;
import com.cmbchina.activity.busi.act.service.ActivityService;
import com.cmbchina.commons.bean.BusinessException;
import com.cmbchina.commons.util.KeyGenerator;
import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by wangtingbang on 16/1/26.
 */
@Controller
@RequestMapping(value = "h5/activity")
public class ActivityController4H5 {

  private static final Logger log = LoggerFactory.getLogger(ActivityController4H5.class);

  @Autowired
  private ActivityService activityService;

  /**
   * @param areaCode
   * @param channel
   * @return
   */
  @RequestMapping(value = "listActivities4H5", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public Object listActivities4H5(String areaCode, Byte channel) {
    ActBusiContext context = new ActBusiContext();
    String reqSeqNo = KeyGenerator.RegularPrefixIDGenerator.Id("AC4H5-LA4H5-");//类名缩写-方法名缩写,TODO
    context.setRequestSeqNo(reqSeqNo);

    try {
      Map groups = activityService.listActivityGroups(context, areaCode, channel);
      return groups;
    } catch (BusinessException e) {
      log.error("error:", e);
      return null;
    }
  }

  /**
   * 手机端活动查看--抽奖类 URL: getActivityInfo4H5Draw
   * 
   * @param activityId
   * @param customerId
   * @return
   */
  @RequestMapping(value = "getActivityInfo4H5Draw", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public String getActivityInfo4H5Draw(String activityId, String customerId) {

    return null;
  }

  /**
   * 手机端活动查看--非抽奖类 URL: getActivityInfo4H5Comm
   * 
   * @param activityId
   * @param customerId
   * @return
   */
  @RequestMapping(value = "getActivityInfo4H5Comm", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public String getActivityInfo4H5Comm(String activityId, String customerId) {

    return null;
  }

  /**
   * 活动参与(领取、抽奖) URL: partakeActivity
   * 
   * @param customerId
   * @param actGroupId
   * @param activityId
   * @return
   */
  public String partakeActivity(String customerId, String actGroupId, String activityId) {
    return null;
  }
}
