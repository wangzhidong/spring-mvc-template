package com.cmbchina.activity.tran.restful.common.controller;

import com.cmbchina.activity.busi.common.service.ComUserService;
import com.cmbchina.commons.bean.BusinessException;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangtingbang on 16/1/11.
 */

@Controller
@RequestMapping(value="operation/auth")
public class UserController4OP {

  private static final Logger log = LoggerFactory.getLogger(UserController4OP.class);

  @Autowired
  private ComUserService comUserService;

  /**
   * 部门人员列表
   * @param roleList
   * @param roleList
   * @param deptId
   * @param page
   * @param limit
   * @return
   */
  @RequestMapping(value = "listUsersByDept")
  public Object listUsersByDept(@PathVariable("role") String role, @RequestParam(value = "roleList[]") Byte[] roleList, String deptId, int page, int limit){
    List roles = Lists.newArrayList(roleList);
    return comUserService.listUserByDept(null,deptId,roles,page,limit);
  }

  /**
   * 用户添加
   * @param user
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "addUser", method = RequestMethod.POST)
  @ResponseBody
  public int addUser(Object user) throws BusinessException{
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
   * @param param
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "listUsers", method = RequestMethod.POST)
  @ResponseBody
  // TODO
  public List listUsers( Object param) throws BusinessException{
    return null;
  }

  /**
   * 用户详情
   * @param userId
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
  @ResponseBody
  public Object getUserInfo(String userId) throws BusinessException{

    return null;
  }

  @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
  @ResponseBody
  public int deleteUser(String userId) throws BusinessException{
    return 0;
  }

}
