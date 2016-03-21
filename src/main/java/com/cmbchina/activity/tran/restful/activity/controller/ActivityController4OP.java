package com.cmbchina.activity.tran.restful.activity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cmbchina.activity.busi.common.constant.MessageConstants;
import com.cmbchina.activity.busi.common.dto.ComMessage;
import com.cmbchina.activity.busi.common.service.ComMessageService;
import com.cmbchina.activity.busi.external.dto.ExternalProduct;
import com.cmbchina.activity.busi.external.service.ExternalProductService;
import com.cmbchina.commons.bean.exception.BusinessExceptionDic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.busi.act.constant.ActivityConstants;
import com.cmbchina.activity.busi.act.dto.ActActivity;
import com.cmbchina.activity.busi.act.dto.ActActivityArea;
import com.cmbchina.activity.busi.act.dto.ActActivityConfig;
import com.cmbchina.activity.busi.act.dto.ActActivityGift;
import com.cmbchina.activity.busi.act.dto.ActBusiContext;
import com.cmbchina.activity.busi.act.dto.ActGroup;
import com.cmbchina.activity.busi.act.dto.ActRecommend;
import com.cmbchina.activity.busi.act.service.ActivityService;
import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.commons.bean.BusinessException;
import com.cmbchina.commons.util.DateTimeUtils;
import com.cmbchina.commons.util.KeyGenerator;
import com.google.common.collect.Lists;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "operation/activity")
public class ActivityController4OP {

  private static final Logger log = LoggerFactory.getLogger(ActivityController4OP.class);

  @Autowired
  private ActivityService activityService;

  @Autowired
  private AuthorityService authorityService;

  @Autowired
  private ExternalProductService externalProductService;

  @Autowired
  private ComMessageService messageService;

  public enum OP_TYPE {
    CREATE((byte) 1), UPDATE((byte) 2);

    byte type;

    OP_TYPE(byte type) {
      this.type = type;
    }
  }

  /**
   * 活动列表 URL: listActivities
   *
   * @param req
   * @return
   */
  @RequestMapping(value = "listActivities", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  // TODO user role, auth, filter
  public List listAcitivties(@RequestBody HashMap req, HttpServletRequest request) {
    if (req == null) {
      return null;
    }
    Map param = (Map) req.get("param");

    String userId = (String) param.get("userId");
    String roleId = (String) param.get("roleId");
    String deptId = (String) param.get("deptId");
    Date startTime = param.get("startTime") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("startTime"));
    Date endTime = param.get("endTime") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("endTime"));
    Date commitTimeStart = param.get("commitTimeStart") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("commitTimeStart"));
    Date commitTimeEnd = param.get("commitTimeStart") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("commitTimeEnd"));
    String commitUserName = null;// TODO (String) param.get("commitUserName");
    String status = (String) param.get("status");
    // String cookies = request.getCookies().toString();
    // String session = request.getSession().toString();
    // log.info("cookies:{}, session:{}", cookies, session);
    log.info(String.format(
        "userId:%s, roleId:%s, deptId:%s, startTime:%s,endTime:%s, commitTimeStart:%s, commitTimeEnd:%s, commitUserName:%s, status:%s\n",
        userId, roleId, deptId, startTime, endTime, commitTimeStart, commitTimeEnd, commitUserName,
        status));

    try {
      List<Byte> statusList;
      if (StringUtils.isEmpty(status)) {
        statusList = Lists.newArrayList(ActivityConstants.ACTIVITY_STATUS.INIT.getValue(), //
            ActivityConstants.ACTIVITY_STATUS.TO_BE_APPROVE.getValue(), //
            ActivityConstants.ACTIVITY_STATUS.APPROVE_REJECTED.getValue(), //
            ActivityConstants.ACTIVITY_STATUS.APPROVED.getValue(), //
            ActivityConstants.ACTIVITY_STATUS.ONLINE.getValue(), //
            ActivityConstants.ACTIVITY_STATUS.MANUALLY_OFFLINE.getValue());
      } else {
        statusList = Lists.newArrayList(Byte.parseByte(status));
      }
      List result = activityService.listActivities(null, userId, roleId, deptId, startTime, endTime,
          commitTimeStart, commitTimeEnd, commitUserName, statusList);
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
   * 活动列表 URL: listActivities
   *
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "listActivities4Recommend",
      method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public Map listActivities4Recommend(@RequestBody HashMap req, HttpServletRequest request) {

    if (req == null) {
      return null;
    }

    // Map param = (Map) req.get("param");
    ActBusiContext context = new ActBusiContext();
    try {
      List<Byte> statusList =
          Lists.newArrayList(ActivityConstants.ACTIVITY_STATUS.ONLINE.getValue());
      Map result = activityService.listActivities4Recommend(context);

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
   * 活动列表 URL: listActivities
   *
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "listActivities4Approval",
      method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public List listActivities4Approval(@RequestBody HashMap req, HttpServletRequest request) {
    if (req == null) {
      return null;
    }

    Map param = (Map) req.get("param");
    String userId = (String) param.get("userId");
    String roleId = (String) param.get("roleId");
    String deptId = (String) param.get("deptId");
    Date startTime = param.get("startTime") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("startTime"));
    Date endTime = param.get("endTime") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("endTime"));
    Date commitTimeStart = param.get("commitTimeStart") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("commitTimeStart"));
    Date commitTimeEnd = param.get("commitTimeStart") == null ? null
        : DateTimeUtils.toDateTime((String) param.get("commitTimeEnd"));
    String commitUserName = null;// TODO (String) param.get("commitUserName");
    String status = (String) param.get("status");
    // String cookies = request.getCookies().toString();
    // String session = request.getSession().toString();
    // log.info("cookies:{}, session:{}", cookies, session);
    log.info(String.format(
        "userId:%s, roleId:%s, deptId:%s, startTime:%s,endTime:%s, commitTimeStart:%s, commitTimeEnd:%s, commitUserName:%s, status:%s\n",
        userId, roleId, deptId, startTime, endTime, commitTimeStart, commitTimeEnd, commitUserName,
        status));


    try {
      List<Byte> statusList = null;
      statusList = Lists.newArrayList(ActivityConstants.ACTIVITY_STATUS.TO_BE_APPROVE.getValue(), //
          ActivityConstants.ACTIVITY_STATUS.APPROVED.getValue(), //
          ActivityConstants.ACTIVITY_STATUS.ONLINE.getValue()//
      );
      List result = activityService.listActivities(null, userId, roleId, deptId, startTime, endTime,
          commitTimeStart, commitTimeEnd, commitUserName, statusList);
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
   * @param req
   * @return
   */
  @RequestMapping(value = "getActivityInfo", method = {RequestMethod.POST, RequestMethod.GET})
  @ResponseBody
  public Map findActivity(@RequestBody HashMap req, HttpServletRequest request) {
    String actGroupId = (String) ((Map) req.get("param")).get("actGroupId");

    try {
      // ActGroup group = activityService.getActGroupById(null, actGroupId);
      //
      // List<ActActivity> activities = activityService.listActByGroupId(null, actGroupId);

      // TODO context
      Map result = activityService.getActivity(null, actGroupId);

      // log.info(JSONObject.toJSONString(result));
      return result;
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 活动配置/新增 URL: addActivity
   *
   * @param req
   * @return
   */
  @RequestMapping(value = "addActivityTmp", method = RequestMethod.POST)
  @ResponseBody
  public String addActivityTmp(@RequestBody HashMap req, HttpServletRequest request) {

    log.info(JSONObject.toJSONString(req));
    Map param = (Map) req.get("param");

    // String activityId = KeyGenerator.uuid();//((String)param.get("activityId")) ;
    String actGroupId = KeyGenerator.uuid();// ((String)param.get("actGroupId")) ;
    // String actGroupName = ((String)param.get("actGroupName")) ;
    // String quaId = (String)param.get("quaId");
    // String quaGroupId = (String)param.get("quaGroupId");
    // String activityName = (String)param.get("activityName");
    // String activityType = (String)param.get("activityType");
    // String status = (String)param.get("status");

    // String actGroupIdStr = (String)param.get("actGroupId");
    String quaGroupIdStr = (String) param.get("quaGroupId");
    String actGroupNameStr = (String) param.get("actGroupName");
//    String activityTypeStr = (String) param.get("activityType");
    String onlineTimeStr = (String) param.get("onlineTime");
    String offlineTimeStr = (String) param.get("offlineTime");
    String startTimeStr = (String) param.get("startTime");
    String endTimeStr = (String) param.get("endTime");
    String channelStr = (String) param.get("channel");
    String descriptionStr = (String) param.get("description");
    String statusStr = (String) param.get("status");
    Byte activityTypeVar = ((Number)param.get("activityType")).byteValue();
    String picUrlStr = (String) param.get("picUrl");
    String commitUserIdStr = (String) param.get("commitUserId");
    String commitUserNameStr = (String) param.get("commitUserName");
    String commitTimeStr = (String) param.get("commitTime");
    String approvalUserIdStr = (String) param.get("approvalUserId");
    String approvalUserNameStr = (String) param.get("approvalUserName");
    String approvalTimeStr = (String) param.get("approvalTime");
//    String subActivityRelationStr = (String) param.get("subActivityRelation"); // TODO 子活動間關係
    String receiveSuccessText = (String) param.get("receiveSuccessText"); // TODO 領取成功提示
    String receiveIneligibleText = (String) param.get("receiveIneligibleText"); // TODO
                                                                                // 領取失敗提示（無資格提示）
    int seqNumber = (int) param.get("rankId");


    Set keySet;
    Iterator iterator;

    List<ActRecommend> recommends = new ArrayList<>();
    JSONArray recommendActIdJArray = (JSONArray) param.get("recommendActId");
    for (Object obj : recommendActIdJArray) {
      String recommendActId = (String) obj;
      // Map recommendActIdMap = (Map) param.get("recommendActId");
      // while (iterator.hasNext()) {
      // String recommendActId = (String) recommendActIdMap.get(iterator.next());
      ActRecommend recommend = new ActRecommend();
      recommend.setId(KeyGenerator.uuid());
      recommend.setActGroupId(actGroupId);
      recommend.setRecommendActGroupId(recommendActId);
      recommends.add(recommend);
    }

    // TODO new model
    Map cityMap = (Map) param.get("cityList");
    keySet = cityMap.keySet();
    iterator = keySet.iterator();
    List<ActActivityArea> areas = new ArrayList<>();
    while (iterator.hasNext()) {
      Map city = (Map) cityMap.get(iterator.next());
      ActActivityArea area = new ActActivityArea();
      area.setActGroupId(actGroupId);
      // area.setAreaId(null); //TODO
      // area.setArea(null); //TODO
      // area.setAreaCode(null); //TODO
      area.setProvinceId((String) city.get("provinceId"));
      area.setProvince((String) city.get("provinceName"));
      area.setCityId((String) city.get("cityId"));
      area.setCity((String) city.get("cityName"));
      areas.add(area);
    }

    JSONArray subActivitiesJArray = null;
    List subActivities = new ArrayList();
    List<ActActivityGift> gifts = new ArrayList<>();

    List<ActActivityConfig> configs = new ArrayList<>();
    ActActivityConfig config0 = new ActActivityConfig();
    config0.setObjectName(ActGroup.class.getName().toString());
    config0.setObjectId(actGroupId);
    config0.setMetaKey("receiveIneligibleText");
    config0.setMetaValue(receiveIneligibleText);
    ActActivityConfig config1 = new ActActivityConfig();
    config1.setObjectName(ActGroup.class.getName().toString());
    config1.setObjectId(actGroupId);
    config1.setMetaKey("receiveSuccessText");
    config1.setMetaValue(receiveSuccessText);
    configs.add(config0);
    configs.add(config1);

    // 普通活动
//    if ("1".equals((String) param.get("activityType"))) {
    if(ActivityConstants.ACTIVITY_TYPE.GENERAL.getValue().equals(activityTypeVar)){
      subActivitiesJArray = (JSONArray) param.get("child");
      for (Object obj : subActivitiesJArray) {
        String activityId = KeyGenerator.uuid();
        Map tmp = (Map) obj;
        ActActivity actTmp = new ActActivity();
        actTmp.setActivityId(activityId);
        actTmp.setActGroupId(actGroupId);
        actTmp.setQuaId((String) tmp.get("quaId"));
        actTmp.setActivityName((String) tmp.get("activityName"));
        actTmp.setStatus(ActivityConstants.ACTIVITY_STATUS.INIT.getValue());
        actTmp.setActivityCycle(((Number)tmp.get("activityCycle")).intValue());
        actTmp.setCycleUnit(Byte.parseByte((String) tmp.get("cycleUnit"))); // 週期類型：１－小時，２－天，３－周，４－月
        actTmp.setCycleMax(((int) tmp.get("cycleMax"))); // 週期內最大量（庫存）
        actTmp.setUserCycleMax(((int) tmp.get("userCycleMax"))); // 週期內用戶最大量
        actTmp.setUserMax(((Number)tmp.get("userMax")).intValue());
        actTmp.setSeqNumber(seqNumber);
        ActActivityConfig config = new ActActivityConfig();
        config.setObjectName(ActActivity.class.getName().toString());
        config.setObjectId(activityId);
        config.setMetaKey("buttonText");
        config.setMetaValue((String) tmp.get("buttonText"));

        ActActivityGift gift = new ActActivityGift();
        gift.setActivityId(activityId);
        gift.setProductId((String) tmp.get("productId"));
        subActivities.add(actTmp);
        gifts.add(gift);
      }
    }
    // 抽奖
    else if ("2".equals((String) param.get("activityType"))) {
      subActivitiesJArray = (JSONArray) param.get("childDraw");
      for (Object obj : subActivitiesJArray) {
        String activityId = KeyGenerator.uuid();
        Map tmp = (Map) obj;
        ActActivity actTmp = new ActActivity();
        actTmp.setActivityId(activityId);
        actTmp.setActGroupId(actGroupId);
        actTmp.setQuaId((String) tmp.get("quaId"));
        actTmp.setActivityName((String) tmp.get("activityName"));
        actTmp.setStatus(ActivityConstants.ACTIVITY_STATUS.INIT.getValue());
        actTmp.setActivityCycle(((Number)tmp.get("activityCycle")).intValue());
        actTmp.setCycleUnit(Byte.parseByte((String) tmp.get("cycleUnit"))); // 週期類型：１－小時，２－天，３－周，４－月
        actTmp.setCycleMax(((int) tmp.get("cycleMax"))); // 週期內最大量（庫存）
        actTmp.setUserCycleMax(((int) tmp.get("userCycleMax"))); // 週期內用戶最大量
        actTmp.setUserMax(((Number)tmp.get("userMax")).intValue());
        actTmp.setSeqNumber(seqNumber);
        subActivities.add(actTmp);
        JSONArray productsJArray = (JSONArray) tmp.get("productList");
        for (Object product : productsJArray) {
          Map prdTmp = (Map) product;
          float drawRate = Float.valueOf(prdTmp.get("drawRate").toString());
          String productId = (String) prdTmp.get("productId");

          ActActivityGift gift = new ActActivityGift();
          gift.setActivityId(activityId);
          gift.setProductId(productId);
          gift.setDrawRate(drawRate);
          gifts.add(gift);
        }
      }
    }

    ActGroup group = new ActGroup();

    group.setActGroupId(actGroupId);// (String actGroupId) {
    group.setQuaGroupId(quaGroupIdStr);// (String quaGroupId) {
    group.setActGroupName(actGroupNameStr);// (String actGroupName) {
    group.setActivityType(((Number) param.get("activityType")).byteValue());// (Short activityType) {
    group.setOnlineTime(StringUtils.isEmpty(onlineTimeStr) ? null
        : DateTimeUtils.toDate(onlineTimeStr, "yyyy/MM/dd HH:mm:ss"));
    group.setOfflineTime(StringUtils.isEmpty(offlineTimeStr) ? null
        : DateTimeUtils.toDate(offlineTimeStr, "yyyy/MM/dd HH:mm:ss"));
    group.setStartTime(StringUtils.isEmpty(startTimeStr) ? null
        : DateTimeUtils.toDate(startTimeStr, "yyyy/MM/dd HH:mm:ss"));
    group.setEndTime(StringUtils.isEmpty(endTimeStr) ? null
        : DateTimeUtils.toDate(endTimeStr, "yyyy/MM/dd HH:mm:ss"));
    group.setChannel((byte) 1);// group.setChannel(Byte.parseByte(channelStr));
    group.setDescription(descriptionStr);
    group.setStatus(ActivityConstants.ACTIVITY_STATUS.INIT.getValue());
    group.setSeqNumber(seqNumber);
    group.setSubActivityRelation(((Number)param.get("subActivityRelation")).byteValue());
    group.setPicUrl(picUrlStr);
    group.setCommitUserId(commitUserIdStr);
    group.setCommitUserName(commitUserNameStr);
    group.setCommitTime(DateTimeUtils.now());

    try {
      activityService.addActivity(null, group, subActivities, recommends, areas, gifts, null);
      log.info(JSONObject.toJSONString(group));
      return "success";
    } catch (BusinessException e) {
      e.printStackTrace();
      log.error(String.format("error:%s", e.getLocalizedMessage()));
    }
    return "fail";
  }

  /**
   * 活动新增 URL: addActivity
   *
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "addActivity", method = RequestMethod.POST)
  @ResponseBody
  public String addActivity(@RequestBody HashMap req, HttpServletRequest request) {

    if (log.isDebugEnabled()) {
      log.debug("request body:{}",req);
    }
    Map param = (Map) req.get("param");
    String token = null; // TODO

    try {
      this.processWrite(param, token, OP_TYPE.CREATE);
    } catch (BusinessException e) {
//      log.error(String.format("error:%s",e.getLocalizedMessage()));
      log.error("Exceptioin:{} ==>", e);
    } catch (Exception e) {
      log.error("Exceptioin:{} ==>", e);
    }

    return null;
  } 
  
  /**
   * 活动修改 URL: updateActivity
   *
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "updateActivity", method = RequestMethod.POST)
  @ResponseBody
  public String updateActivity(@RequestBody HashMap req, HttpServletRequest request) {

    if (log.isDebugEnabled()) {
      log.debug("request body:{}",req);
    }
    Map param = (Map) req.get("param");
    String token = null; // TODO

    try {
      this.processWrite(param, token, OP_TYPE.UPDATE);
    } catch (BusinessException e) {
//      log.error(String.format("error:%s",e.getLocalizedMessage()));
      log.error("Exceptioin:{} ==>", e);
    } catch (Exception e) {
      log.error("Exceptioin:{} ==>", e);
    }

    return null;
  }

  /**
   * 活动提交 commitActivity
   *
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "commitActivity", method = RequestMethod.POST)
  @ResponseBody
  // public String commitActivity(String actGroupId) {
  public String commitActivity(@RequestBody HashMap req, HttpServletRequest request) {

    Map param = (Map) req.get("param");
    String actGroupId = (String) param.get("actGroupId");
    try {

      List<String> productIds = activityService.getActivityGiftIdsByParam(actGroupId,null);

      Map producutCheckMap = externalProductService.checkProductStatusFromOMS(productIds);

      log.info("product check result:{}", producutCheckMap);

      int result = activityService.commitActivity(null, actGroupId);

      if(result == 1 ){ //TODO
        ComMessage message = new ComMessage();

        Short messageType = MessageConstants.MESSAGE_TYPE.CODE_100.getValue();
        String messageDesc = MessageConstants.MESSAGE_TYPE.CODE_100.getDesc();
        ActGroup actGroup = activityService.getActGroupById(null, actGroupId); //TODO
        message.setUserId(actGroup.getCommitUserId()); //TODO 查询归属关系
        message.setMessageType(messageType);
        message.setDescription(messageDesc);
        message.setCommitTime(DateTimeUtils.now());
        message.setCommitUserId(null);

        messageService.addMessage(null, message);
      }

      return result == 1 ? "success" : "fail";
    } catch (BusinessException e) {
      log.error(String.format("error:%s", e.getLocalizedMessage()));
    }
    return "fail";
  }

  /**
   * 活动审批 URL: approveActivity
   *
   * @param req
   * @return
   */
  @RequestMapping(value = "approveActivity", method = RequestMethod.POST)
  @ResponseBody
  public String approveActivity(@RequestBody HashMap req, HttpServletRequest request) {
   // String actGroupId, Byt approvalResult, String approvalMessage

    Map param = (Map) req.get("param");
    try {
      String actGroupId = (String) param.get("actGroupId");
      int approvalResult = (int) param.get("approvalResult");
      String approvalMessage = (String) param.get("approvalMessage");
      int result =
          activityService.approveActivity(null, actGroupId, (byte) approvalResult, approvalMessage);

      if(result == 1 ){ //TODO
        ComMessage message = new ComMessage();

        Short messageType = approvalResult == 1?MessageConstants.MESSAGE_TYPE.CODE_101.getValue()
          :MessageConstants.MESSAGE_TYPE.CODE_102.getValue();
        String messageDesc = approvalResult == 1?MessageConstants.MESSAGE_TYPE.CODE_101.getDesc()
          :MessageConstants.MESSAGE_TYPE.CODE_102.getDesc();
        ActGroup actGroup = activityService.getActGroupById(null, actGroupId); //TODO
        message.setUserId(actGroup.getCommitUserId());
        message.setMessageType(messageType);
        message.setDescription(messageDesc);
        message.setCommitTime(DateTimeUtils.now());
        message.setCommitUserId(null); //TODO

        messageService.addMessage(null, message);
      }

      return result == 1 ? "success" : "fail";
    } catch (BusinessException e) {
      log.error(String.format("error:%s", e.getLocalizedMessage()));
    }


    return null;
  }

  /**
   * 活动上下线 URL: setActivityOnline
   *
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "setActivityOnline", method = RequestMethod.POST)
  @ResponseBody
  public String setActivityOnline(@RequestBody HashMap req, HttpServletRequest request) {
    Map param = (Map) req.get("param");
    try {
      String actGroupId = (String) param.get("actGroupId");
      int flag = (int) param.get("flag");
      int result = activityService.setActivityOnline(null, actGroupId, (byte) flag);
      return result == 1 ? "success" : "fail";
    } catch (BusinessException e) {
      log.error(String.format("error:%s", e.getLocalizedMessage()));
      // throw e;// TODO
    }
    return "fail";
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

  private int processWrite(Map param, String token, OP_TYPE opType) throws BusinessException {
    int result = 0;

    // String activityId = KeyGenerator.uuid();//((String)param.get("activityId")) ;
    String actGroupId = null;
    if (opType == OP_TYPE.CREATE) {
    	actGroupId = KeyGenerator.uuid();
    } else if (opType == OP_TYPE.UPDATE) {
      actGroupId = (String) param.get("actGroupId");
      if(StringUtils.isEmpty(actGroupId)){
        log.error("opType:{}, error:{}", opType, BusinessExceptionDic.EX_GNR_NULL_PARAM);
        throw BusinessExceptionDic.EX_GNR_NULL_PARAM;
      }
    }
    String quaGroupIdStr = (String) param.get("quaGroupId");
    String actGroupNameStr = (String) param.get("actGroupName");
    Byte activityTypeVar = ((Number) param.get("activityType")).byteValue();
    String onlineTimeStr = (String) param.get("onlineTime");
    String offlineTimeStr = (String) param.get("offlineTime");
    String startTimeStr = (String) param.get("startTime");
    String endTimeStr = (String) param.get("endTime");
    // String channelStr = (String) param.get("channel");
    String descriptionStr = (String) param.get("description");
    // String statusStr = (String) param.get("status");
    String picUrlStr = (String) param.get("picUrl");
    String commitUserIdStr = (String) param.get("commitUserId");
    String commitUserNameStr = (String) param.get("commitUserName");

    int subActivityRelationVar = (int) param.get("subActivityRelation"); // TODO 子活動間關係
     String receiveSuccessText = (String) param.get("receiveSuccessText"); //TODO 領取成功提示
     String receiveIneligibleText = (String) param.get("receiveIneligibleText"); //TODO 領取失敗提示（無資格提示）

    int seqNumber = (int) param.get("rankId");

    /**
     * object for write
     */
    ActGroup group = new ActGroup();
    List<ActActivity> subActivities = new ArrayList();
    List<ActRecommend> recommends = new ArrayList<>();
    List<ActActivityGift> gifts = new ArrayList<>();
    List<ActActivityArea> areas = new ArrayList<>();

    Set keySet;
    Iterator iterator;

    // JSONArray recommendActIdJArray = (JSONArray) param.get("recommendActId");
    Object recommendActIdParam = param.get("recommendActId");

    // TODO
    if (recommendActIdParam instanceof JSONArray) {
      JSONArray recommendActIdJArray = (JSONArray) recommendActIdParam;
      for (Object obj : recommendActIdJArray) {
        String recommendActId = (String) obj;
        ActRecommend recommend = new ActRecommend();
        recommend.setId(KeyGenerator.uuid());
        recommend.setActGroupId(actGroupId);
        recommend.setRecommendActGroupId(recommendActId);
        recommends.add(recommend);
      }
    }
    if (recommendActIdParam instanceof JSONObject) {
      Map recommendActIdMap = (Map) recommendActIdParam;
      keySet = recommendActIdMap.keySet();
      iterator = keySet.iterator();
      while (iterator.hasNext()) {
        String recommendActId = (String) recommendActIdMap.get(iterator.next());
        ActRecommend recommend = new ActRecommend();
        recommend.setId(KeyGenerator.uuid());
        recommend.setActGroupId(actGroupId);
        recommend.setRecommendActGroupId(recommendActId);
        recommends.add(recommend);
      }
    }

    Map cityMap = (Map) param.get("cityList");
    keySet = cityMap.keySet();
    iterator = keySet.iterator();
    while (iterator.hasNext()) {
      Map city = (Map) cityMap.get(iterator.next());
      ActActivityArea area = new ActActivityArea();
      if(opType.equals(OP_TYPE.UPDATE)){
        area.setId((String)city.get("areaKey"));
      }
      area.setActGroupId(actGroupId);
      // area.setAreaId(null); //TODO
      // area.setArea(null); //TODO
      // area.setAreaCode(null); //TODO
      area.setProvinceId((String) city.get("provinceId"));
      area.setProvince((String) city.get("provinceName"));
      area.setCityId((String) city.get("cityId"));
      area.setCity((String) city.get("cityName"));
      areas.add(area);
    }

    List<ActActivityConfig> configs = new ArrayList<>();
    ActActivityConfig config0 = new ActActivityConfig();
    config0.setObjectName(ActGroup.class.getName().toString());
    config0.setObjectId(actGroupId);
    config0.setMetaKey("receiveIneligibleText");
    config0.setMetaValue(receiveIneligibleText);
    ActActivityConfig config1 = new ActActivityConfig();
    config1.setObjectName(ActGroup.class.getName().toString());
    config1.setObjectId(actGroupId);
    config1.setMetaKey("receiveSuccessText");
    config1.setMetaValue(receiveSuccessText);
    configs.add(config0);
    configs.add(config1);
    
    JSONArray subActivitiesJArray = null;
    // 普通活动
    if(ActivityConstants.ACTIVITY_TYPE.GENERAL.getValue().equals(activityTypeVar)){
//    }
//    if ("1".equals((String) param.get("activityType"))) {
      subActivitiesJArray = (JSONArray) param.get("child");
      for (Object obj : subActivitiesJArray) {
        String activityId = null;
        Map tmp = (Map) obj;
        if (opType == OP_TYPE.CREATE) {
          activityId = KeyGenerator.uuid();
        } else if (opType == OP_TYPE.UPDATE) {
          activityId = (String) tmp.get("activityId");
        }
        ActActivity actTmp = new ActActivity();
        actTmp.setActivityId(activityId);
        actTmp.setActGroupId(actGroupId);
        actTmp.setQuaId((String) tmp.get("quaId"));
        actTmp.setActivityName((String) tmp.get("activityName"));
        actTmp.setStatus(ActivityConstants.ACTIVITY_STATUS.INIT.getValue()); //TODO
        actTmp.setActivityCycle(((Number)tmp.get("activityCycle")).intValue());
        actTmp.setCycleUnit(((Number) tmp.get("cycleUnit")).byteValue()); // 週期類型：１－小時，２－天，３－周，４－月
        actTmp.setCycleMax(((Number) tmp.get("cycleMax")).intValue()); // 週期內最大量（庫存）
        actTmp.setUserCycleMax(((Number) tmp.get("userCycleMax")).intValue()); // 週期內用戶最大量
        actTmp.setUserMax(((Number)tmp.get("userMax")).intValue());
        actTmp.setSeqNumber(seqNumber);
        ActActivityConfig config = new ActActivityConfig();
        config.setObjectName(ActActivity.class.getName().toString());
        config.setObjectId(activityId);
        config.setMetaKey("buttonText");
        config.setMetaValue((String) tmp.get("buttonText"));
        configs.add(config);
        ActActivityGift gift = new ActActivityGift();
        gift.setActivityId(activityId);
        if(opType.equals(OP_TYPE.UPDATE)){
          gift.setId((String) tmp.get("productKey"));
        }
        gift.setProductId((String) tmp.get("productId"));
        subActivities.add(actTmp);
        gifts.add(gift);
      }
    }
    // 抽奖
    else if(ActivityConstants.ACTIVITY_TYPE.DRAW.getValue().equals(activityTypeVar)){
//    else if ("2".equals((String) param.get("activityType"))) {
      subActivitiesJArray = (JSONArray) param.get("childDraw");
      for (Object obj : subActivitiesJArray) {
        String activityId = null;
        Map tmp = (Map) obj;
        if (opType == OP_TYPE.CREATE) {
          activityId = KeyGenerator.uuid();
        } else if (opType == OP_TYPE.UPDATE) {
          activityId = (String) tmp.get("activityId");
        }
        ActActivity actTmp = new ActActivity();
        actTmp.setActivityId(activityId);
        actTmp.setActGroupId(actGroupId);
        actTmp.setQuaId((String) tmp.get("quaId"));
        actTmp.setActivityName((String) tmp.get("activityName"));
        actTmp.setStatus(ActivityConstants.ACTIVITY_STATUS.INIT.getValue()); //TODO
        actTmp.setActivityCycle(((Number)tmp.get("activityCycle")).intValue());
        actTmp.setCycleUnit(((Number) tmp.get("cycleUnit")).byteValue()); // 週期類型：１－小時，２－天，３－周，４－月
        actTmp.setCycleMax(((Number) tmp.get("cycleMax")).intValue()); // 週期內最大量（庫存）
        actTmp.setUserCycleMax(((Number) tmp.get("userCycleMax")).intValue()); // 週期內用戶最大量
        actTmp.setUserMax(((Number)tmp.get("userMax")).intValue());
        actTmp.setSeqNumber(seqNumber);
        subActivities.add(actTmp);
        
        ActActivityConfig config = new ActActivityConfig();
        config.setObjectName(ActActivity.class.getName().toString());
        config.setObjectId(activityId);
        config.setMetaKey("buttonText");
        config.setMetaValue((String) tmp.get("buttonText"));
        configs.add(config);
        JSONArray productsJArray = (JSONArray) tmp.get("productList");
        for (Object product : productsJArray) {
          Map prdTmp = (Map) product;

          ActActivityGift gift = new ActActivityGift();
          if(opType.equals(OP_TYPE.UPDATE)){
            gift.setId((String)tmp.get("productKey"));
          }
          gift.setActivityId(activityId);
          gift.setProductId((String) prdTmp.get("productId"));
          gift.setDrawRate(((Number)prdTmp.get("drawRate")).floatValue());
          gifts.add(gift);
        }
      }
    }

    group.setActGroupId(actGroupId);
    group.setQuaGroupId(quaGroupIdStr);
    group.setActGroupName(actGroupNameStr);
    group.setActivityType((byte) activityTypeVar);
    group.setOnlineTime(StringUtils.isEmpty(onlineTimeStr) ? null
        : DateTimeUtils.toDate(onlineTimeStr, DateTimeUtils.Pattern.CMBCHINA_FORMATE_TIME));
    group.setOfflineTime(StringUtils.isEmpty(offlineTimeStr) ? null
        : DateTimeUtils.toDate(offlineTimeStr, DateTimeUtils.Pattern.CMBCHINA_FORMATE_TIME));
    group.setStartTime(StringUtils.isEmpty(startTimeStr) ? null
        : DateTimeUtils.toDate(startTimeStr, DateTimeUtils.Pattern.CMBCHINA_FORMATE_TIME));
    group.setEndTime(StringUtils.isEmpty(endTimeStr) ? null
        : DateTimeUtils.toDate(endTimeStr, DateTimeUtils.Pattern.CMBCHINA_FORMATE_TIME));
    group.setChannel((byte) 1);
    group.setDescription(descriptionStr);
    group.setStatus(ActivityConstants.ACTIVITY_STATUS.INIT.getValue());
    group.setSeqNumber(seqNumber);
    group.setSubActivityRelation((byte) subActivityRelationVar);
    group.setPicUrl(picUrlStr);
    group.setCommitUserId(commitUserIdStr);
    group.setCommitUserName(commitUserNameStr);
    group.setCommitTime(DateTimeUtils.now());


//    ActBusiContext context = this.getTransContext(null, token);
    ActBusiContext context = new  ActBusiContext();//TODO this.getTransContext(null, token);

    try {
      switch (opType) {
        case CREATE: {
          activityService.addActivity(context, group, subActivities, recommends, areas, gifts,
              configs);
          break;
        }
        case UPDATE: {
          activityService.updateActivity(context, group, subActivities, recommends, areas, gifts,
              configs);
          break;
        }
        default: {
          break;
        }
      }
      // log.info(JSONObject.toJSONString(group));
    } catch (BusinessException e) {
      e.printStackTrace();
      log.error(String.format("error:%s", e.getLocalizedMessage()));
      throw e;
    }
    return result;
  }

  // TODO AOP
  private ActBusiContext getTransContext(HttpSession session, String token)
      throws BusinessException {
    ActBusiContext context = new ActBusiContext();

    if (token == null) {
      if (session == null) {
        throw new BusinessException("", new NullPointerException().getMessage()); // TODO
      }
      token = (String) session.getAttribute("token");
    }
    AuthUser user = authorityService.getUserByToken(token);

    context.setRequestSeqNo(KeyGenerator.uuid()); // TODO
    context.setOperatorUserId(user.getUserId());
    context.setOperatorUserName(user.getUserName());
    context.setOperatorDeptId(user.getDeptId());
    context.setOperatorDeptName(user.getDeptName());
    context.setOperatorRoleId(user.getRoleId());

    return context;
  }
}
