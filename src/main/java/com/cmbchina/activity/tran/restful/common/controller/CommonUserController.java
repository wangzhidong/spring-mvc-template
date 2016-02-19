package com.cmbchina.activity.tran.restful.common.controller;

import com.cmbchina.activity.busi.common.dto.ComBusiContext;
import com.cmbchina.activity.busi.common.dto.ComUser;
import com.cmbchina.activity.busi.common.service.ComUserService;
import com.cmbchina.commons.util.DateTimeUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

//  public void setComUserService(ComUserService comUserService) {
//    this.comUserService = comUserService;
//  }

  // public List listUsersByDept(String deptId, @RequestParam(value = "roleList[]") Byte[] roleList,
  // int page, int limit) throws BusinessException {
  // return null;
  // }


  /**
   * 部门人员列表，主要面向资格平台
   * 
   * @param page
   * @param limit
   * @param deptId
   * @param roleList
   * @param request
   * @return
   */
  @RequestMapping(value = "listUsersByDept", method = RequestMethod.POST)
  @ResponseBody
  public List<Map> listUsersByDept(int page, int limit, String deptId, Byte[] roleList,
      HttpServletRequest request) {

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

    List<Byte> role_s = Lists.newArrayList(roleList);

    List<ComUser> users = comUserService.listUserByDept(commonContext, deptId, role_s, page, limit);

    if (users == null || users.size() == 0) {
      return null;
    }

    List<Map> result = new ArrayList<Map>();
    for (ComUser user : users) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("userId", user.getUserId());
      map.put("userName", user.getUserName());
      map.put("roleId", user.getRoleId());

      result.add(map);
    }

    return result;
  }

}
