package com.cmbchina.activity.tran.restful.activity.op.controller;

import com.cmbchina.activity.tran.restful.activity.op.vo.ActivityListResponse;
import com.google.common.collect.Lists;
import net.spy.memcached.compat.log.Logger;
import net.spy.memcached.compat.log.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "activity")
public class ActivityController {

  private static final Logger log = LoggerFactory.getLogger(ActivityController.class);

  /**
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
  @RequestMapping(value = "listActivities", method = RequestMethod.POST)
  @ResponseBody
  public List listAcitivties(String userId, String roleId, String deptId,
    Date startTime, Date endTime, Date commitTimeStart, Date commitTimeEnd,
    String commitUserName, Byte status){
    return null;
  }
}
