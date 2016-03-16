package com.cmbchina.activity.tran.restful.common.controller;

import com.alibaba.fastjson.JSONArray;
import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.dto.ComBusiContext;
import com.cmbchina.activity.busi.common.dto.ComUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.common.service.ComUserService;
import com.cmbchina.commons.bean.BusinessException;
import com.cmbchina.commons.util.DateTimeUtils;
import com.google.common.collect.Lists;
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

  /**
   * 部门人员列表，主要面向资格平台
   * 
   * @param req
   * @param request
   * @return
   */
  @RequestMapping(value = "listUsersByDept", method = RequestMethod.POST)
  @ResponseBody
  public List<Map> listUsersByDept(@RequestBody HashMap req,
      HttpServletRequest request) throws Exception{

    if(req==null){
      log.error("req is null");
      throw new IllegalArgumentException();
    }
    Map param = (Map)req.get("param");
    if(param==null){
      log.error("param is null");
      throw new IllegalArgumentException();
    }

    int page = 1;
    int limit = 10;
    String deptId = (String)param.get("deptId");
    JSONArray roleListJArr = (JSONArray) param.get("roleList");
//    Byte[] roleList = new Byte[roleListStr.size()];
//    roleListStr.toArray(roleList);
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

    List<Byte> role_s = new ArrayList();//Lists.newArrayList(roleList);
    for(int idx =0;idx<roleListJArr.size();idx++){
      byte var = ((Number)roleListJArr.get(idx)).byteValue();
//      role_s.add((Number)roleListJArr.get(idx));
      role_s.add(var);
    }

    List<Map> result = comUserService.listUserByDept(commonContext, deptId, role_s);

//    if (users == null || users.size() == 0) {
//      return null;
//    }
//
//    List<Map> result = new ArrayList<Map>();
//    for (Map user : users) {
//      Map<String, Object> map = new HashMap<String, Object>();
//      map.put("userId", user.get("userId"));
//      map.put("userName", user.get("userName"));
//      map.put("roleId", user.get("roleId"));
//
//      result.add(map);
//    }

    return result;
  }

  /**
   * 部门人员列表
   * @param deptId
   * @param page
   * @param limit
   * @return
   */
//  @RequestMapping(value = "listUsersByDept", method = RequestMethod.GET)
//  @ResponseBody
//  public Object listUsersByDept(String deptId, int page, int limit){
//    List roles = Lists.newArrayList(1,2,3);
//    return comUserService.listUserByDept(null,deptId,roles,page,limit);
//  }

  /**
   * 用户添加
   * @param user
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "addUser", method = RequestMethod.POST)
  @ResponseBody
  public int addUser(Object user) throws BusinessException {
    return 0;
  }

  /**
   * 用户修改
   * @param role
   * @param user
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "updateUser", method = RequestMethod.POST)
  @ResponseBody
  public int updateUser(@PathVariable("role") String role, Object user) throws BusinessException{
    return 0;
  }

  /**
   * 用户列表
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "listUsers", method = RequestMethod.GET) //TODO
  @ResponseBody
  public List listUsers( int page, int limit, HttpServletRequest request) throws BusinessException{
    //TODO
    return comUserService.listUsers(null, page, limit);
  }

  /**
   * 用户详情
   * @param token
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "getUserInfo", method = RequestMethod.GET) //TODO
  @ResponseBody
  public Object getUserInfo(String token, HttpServletRequest request) throws BusinessException{

    if(token == null){
      return null;
    }
    AuthUser user = authorityService.getUserByToken(token);
    if(user == null){
      return null;// TODO
    }
    String userId = user.getUserId();
    ComUser result = comUserService.getUserInfo(null, userId); //TODO
    if(result == null){
      log.error("user is null for userId:", userId);
      return result;
    }
    return result;
  }

  @RequestMapping(value = "deleteUser", method = RequestMethod.GET) //TODO
  @ResponseBody
  public int deleteUser(String userId, HttpServletRequest request) throws BusinessException{
    log.info("deleteUser:{}, {}", userId, request); //TODO
    return comUserService.deleteUser(null, userId);
  }
}
