package com.cmbchina.activity.tran.restful.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.tran.exception.BusinessException;
import com.cmbchina.activity.tran.restful.BasicController;
import com.google.common.collect.Lists;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "common/user")
public class CommonUserController extends BasicController {

  private static final Logger log = LoggerFactory.getLogger(CommonUserController.class);

  


  public enum OP_TYPE {
    CREATE((byte) 1), UPDATE((byte) 2);

    byte type;

    OP_TYPE(byte type) {
      this.type = type;
    }
  }

  /**
   * 部门人员列表，主要面向资格平台
   * 
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "listUsersByDept", method = RequestMethod.POST)
  @ResponseBody
  public List<Map> listUsersByDept(@RequestBody HashMap req, HttpServletRequest request)
      throws Exception {

    if (req == null) {
      log.error("req is null");
      throw new IllegalArgumentException();
    }
    Map param = (Map) req.get("param");
    if (param == null) {
      log.error("param is null");
      throw new IllegalArgumentException();
    }

    int page = 1;
    int limit = 10;
    String deptId = (String) param.get("deptId");
    JSONArray roleListJArr = (JSONArray) param.get("roleList");

    String remoteAddr = request.getRemoteAddr();
    String remoteHost = request.getRemoteHost();
    String remoteUser = request.getRemoteUser();
    String requestedSessionId = request.getRequestedSessionId();
    Integer remotePort = request.getRemotePort();

    List<Byte> role_s = new ArrayList();// Lists.newArrayList(roleList);
    for (int idx = 0; idx < roleListJArr.size(); idx++) {
      byte var = ((Number) roleListJArr.get(idx)).byteValue();
      role_s.add(var);
    }

    List<Map> result = null;
    return result;
  }

  /**
   * 用户添加
   * 
   * @param req
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "addUser", method = RequestMethod.POST)
  @ResponseBody
  public int addUser(@RequestBody HashMap req, HttpServletRequest request) throws BusinessException {

    String token = (String) request.getSession().getAttribute("token");
    return this.proccessWrite(req, OP_TYPE.CREATE, token);
  }

  /**
   * 用户修改
   * 
   * @param req
   * @param request
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "updateUser", method = RequestMethod.POST)
  @ResponseBody
  public int updateUser(@RequestBody HashMap req, HttpServletRequest request)
      throws BusinessException {
    // TODO update redis

    String token = (String) request.getSession().getAttribute("token");

    return this.proccessWrite(req, OP_TYPE.UPDATE, token);
  }

  private int proccessWrite(Map req, OP_TYPE opType, String token) throws BusinessException {
    log.info("request body:{}", req);
    Map param = (Map) req.get("param");

    int result = 0;

    switch (opType) {
      case CREATE:
        result = -1;// comUserService.addUser(null, user); // TODO
        break;
      case UPDATE:
        // user.setUserId((String) param.get("userId"));
        // String oldPass = (String) param.get("oldPassword");
        result = -1;// comUserService.updateUser(context, user, oldPass);
        break;
    }
    return result;
  }

  /**
   * 用户列表
   * 
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "listUsers", method = RequestMethod.GET)
  // TODO
  @ResponseBody
  @Around("aspectjMethod()")
  public List listUsers(int page, int limit, HttpServletRequest request) throws BusinessException {
    // TODO
    String token = (String) request.getSession().getAttribute("token");
    log.info("page:{}, limit:{}, token:{}", page, limit, token);
    List result = null;// comUserService.listUsers(null, page, limit);
    
    log.info("context value:{}", JSONObject.toJSONString(this.context));

//    return result;
    return Lists.newArrayList(JSONObject.toJSONString(this.context));
  }

//  public void initContext(AuthUser user) {
//
//    if(user == null){
//      log.error("init context error, user is null");
//    }
//    if(this.context==null){
//      this.context = new CommonContext();
//    }
//    this.context.setSeqNo(user.getSeqNo());
//    this.context.setUserId(user.getUserId());
//    this.context.setUserName(user.getUserName());
//    
//    log.info("init context:{}", JSONObject.toJSONString(this.context));
//  }
  
  @RequestMapping(value = "test", method = RequestMethod.GET)
  // TODO
  @ResponseBody
  @Around("aspectjMethod()")
  public List test(HttpServletRequest request) throws BusinessException {
    // TODO
    String token = (String) request.getSession().getAttribute("token");
//    log.info("page:{}, limit:{}, token:{}", page, limit, token);
    List result = null;// comUserService.listUsers(null, page, limit);
    
    log.info("context value:{}", JSONObject.toJSONString(this.context));

//    return result;
    return Lists.newArrayList(JSONObject.toJSONString(this.context));
  }
}
