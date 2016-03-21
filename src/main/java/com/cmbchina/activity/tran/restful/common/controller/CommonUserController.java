package com.cmbchina.activity.tran.restful.common.controller;

import com.alibaba.fastjson.JSONArray;
import com.cmbchina.activity.busi.common.constant.UserConstants;
import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.dto.ComArea;
import com.cmbchina.activity.busi.common.dto.ComBusiContext;
import com.cmbchina.activity.busi.common.dto.ComUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.common.service.ComUserService;
import com.cmbchina.commons.bean.BusinessException;
import com.cmbchina.commons.bean.exception.BusinessExceptionDic;
import com.cmbchina.commons.util.DateTimeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "common/user")
public class CommonUserController {

  private static final Logger log = LoggerFactory.getLogger(CommonUserController.class);

  @Autowired
  private ComUserService comUserService;

  @Autowired
  private AuthorityService authorityService;


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
    // Byte[] roleList = new Byte[roleListStr.size()];
    // roleListStr.toArray(roleList);
    ComBusiContext commonContext = new ComBusiContext();
    commonContext.setRequestSeqNo("EXTR" + DateTimeUtils.now()); // TODO seqNo-gen

    /**
     * TODO
     */
    String remoteAddr = request.getRemoteAddr();
    String remoteHost = request.getRemoteHost();
    String remoteUser = request.getRemoteUser();
    String requestedSessionId = request.getRequestedSessionId();
    Integer remotePort = request.getRemotePort();

    List<Byte> role_s = new ArrayList();// Lists.newArrayList(roleList);
    for (int idx = 0; idx < roleListJArr.size(); idx++) {
      byte var = ((Number) roleListJArr.get(idx)).byteValue();
      // role_s.add((Number)roleListJArr.get(idx));
      role_s.add(var);
    }

    List<Map> result = comUserService.listUserByDept(commonContext, deptId, role_s);

    // if (users == null || users.size() == 0) {
    // return null;
    // }
    //
    // List<Map> result = new ArrayList<Map>();
    // for (Map user : users) {
    // Map<String, Object> map = new HashMap<String, Object>();
    // map.put("userId", user.get("userId"));
    // map.put("userName", user.get("userName"));
    // map.put("roleId", user.get("roleId"));
    //
    // result.add(map);
    // }

    return result;
  }

  /**
   * 部门人员列表
   * 
   * @param deptId
   * @param page
   * @param limit
   * @return
   */
  // @RequestMapping(value = "listUsersByDept", method = RequestMethod.GET)
  // @ResponseBody
  // public Object listUsersByDept(String deptId, int page, int limit){
  // List roles = Lists.newArrayList(1,2,3);
  // return comUserService.listUserByDept(null,deptId,roles,page,limit);
  // }

  /**
   * 用户添加
   * 
   * @param req
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "addUser", method = RequestMethod.POST)
  @ResponseBody
  public int addUser(@RequestBody HashMap req, HttpServletRequest request)
      throws BusinessException {

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
    Map param = (Map) req.get("param");

    ComUser user = new ComUser();


    AuthUser opUser = authorityService.getUserByToken(token);

    // TODO
    // if(opUser.getRoleId().equals(UserConstants.USER_ROLE_ID.ADMIN.getValue())) {
    // user.setDeptId((String) param.get("deptId"));
    // user.setDeptName((String) param.get("deptName"));
    // user.setRoleId(((Number) param.get("roleId")).shortValue()); //TOOD
    // user.setApproverId((String) param.get("approverId"));
    // user.setArea((String) param.get("area"));
    // }
    user.setLoginName((String) param.get("loginName"));
    user.setPassword((String) param.get("password"));
    user.setUserName((String) param.get("userName"));
    user.setPhone((String) param.get("phone"));
    user.setEmail((String) param.get("email"));


    int result = -1;
    switch (opType) {
      case CREATE:
        result = comUserService.addUser(null, user); // TODO
        break;
      case UPDATE:
        user.setUserId((String) param.get("userId"));
        result = comUserService.updateUser(null, user);
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
  @RequestMapping(value = "listUsers", method = RequestMethod.GET) // TODO
  @ResponseBody
  public List listUsers(int page, int limit, HttpServletRequest request) throws BusinessException {
    // TODO
    log.info("page:{}, limit:{}", page, limit);
    List result = comUserService.listUsers(null, page, limit);
    
    //TODO hide password

    return result;
  }

  /**
   * 用户详情
   * 
   * @param userId
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "getUserInfo", method = RequestMethod.GET) // TODO to be remove
  @ResponseBody
  public Object getUserInfo(String userId, HttpServletRequest request) throws BusinessException {

    if (StringUtils.isEmpty(userId)) {
      throw BusinessExceptionDic.EX_GNR_NULL_PARAM;
    }

    ComUser user = comUserService.getUserInfo(null, userId); // TODO

    if (user == null) {
      throw BusinessExceptionDic.EX_GNR_DATA_NULL;
    }

    Map result = new HashMap();

    result.put("approverName", user.getApproverName());
    result.put("approverId", user.getApproverId());
    result.put("userName", user.getUserName());
    result.put("deptId", user.getDeptId());
    result.put("deptName", user.getDeptName());
    result.put("loginName", user.getLoginName());
    result.put("roleId", user.getRoleId());
    result.put("area", user.getArea());
    result.put("phone", user.getPhone());
    result.put("email", user.getEmail());

    if (result == null) {
      log.error("user is null for userId:", userId);
      throw BusinessExceptionDic.EX_GNR_DATA_NULL;
    }
    return result;
  }

  @RequestMapping(value = "deleteUser", method = RequestMethod.GET) // TODO
  @ResponseBody
  public int deleteUser(String userId, HttpServletRequest request) throws BusinessException {
    log.info("deleteUser:{}, {}", userId, request); // TODO
    int result = comUserService.deleteUser(null, userId);
    return result;
  }
}
