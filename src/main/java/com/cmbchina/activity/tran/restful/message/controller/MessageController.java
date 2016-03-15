package com.cmbchina.activity.tran.restful.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.dto.ComBusiContext;
import com.cmbchina.activity.busi.common.dto.ComMessage;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.common.service.ComMessageService;
import com.cmbchina.commons.bean.BusinessException;
import com.cmbchina.commons.util.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "common/message")
public class MessageController {

  private static final Logger log = LoggerFactory.getLogger(MessageController.class);

  @Autowired
  private ComMessageService comMessageService;

  @Autowired
  private AuthorityService authorityService;

//  @RequestMapping(value = "getUserNotice", method = RequestMethod.POST)
  @RequestMapping(value = "getUserNotice", method = RequestMethod.GET)
  @ResponseBody
  public List<Object> getUserNotice(String token, HttpServletRequest request)throws BusinessException{
    ComBusiContext context = new ComBusiContext();
    context.setOperatorUserId(request.getRemoteUser()); //TODO
    context.setRequestSeqNo("MSG"+ DateTimeUtils.now());

    AuthUser user = authorityService.getUserByToken(token);
    if(user==null){
      throw new BusinessException("","用户未登录");
    }

    String userId = user.getUserId();
    List result = comMessageService.getUserNotice(context, userId);
    return result;
  }

  @RequestMapping(value = "addMessage", method = RequestMethod.POST)
  @ResponseBody
  public int addMessage(@RequestBody HashMap req, HttpServletRequest request) throws BusinessException{

    ComBusiContext context = new ComBusiContext(); //TODO

    log.info(JSONObject.toJSONString(req));

    int result = -1;

    Map param = (Map)req.get("param");
    String commitUserId = (String)param.get("commitUserId");
    Short messageType = Short.parseShort((String) param.get("messageType"));
    Date commitTime = DateTimeUtils.now();//(String)param.get("commitUserId");
    Byte channel = Byte.parseByte((String) param.get("channel"));
    String description = (String)param.get("description");

    ComMessage message = new ComMessage(); //TODO
    message.setUserId(commitUserId); //TODO
    message.setMessageType(messageType);
    message.setCommitUserId(commitUserId);
    message.setCommitUserName(null);//TODO
    message.setChannel(channel);
    message.setCommitTime(commitTime);
    message.setDescription(description);

    result = comMessageService.addMessage(context, message);
    return result;
  }
}
