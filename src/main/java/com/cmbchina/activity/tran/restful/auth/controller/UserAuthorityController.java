package com.cmbchina.activity.tran.restful.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.dto.ComUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.common.service.ComUserService;
import com.cmbchina.activity.tran.restful.auth.vo.UserAuthRequest;
import com.cmbchina.commons.bean.BusinessException;
import com.cmbchina.commons.util.DateTimeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtingbang on 15/12/10.
 */

@Controller
@RequestMapping(value = "common/auth")
public class UserAuthorityController {

  private static final Logger log = LoggerFactory.getLogger(UserAuthorityController.class);

  @Autowired
  private AuthorityService authorityService;

  @Autowired
  private ComUserService comUserService;

  // @RequestMapping(value = "{key}/login", method = RequestMethod.GET)
  // public ModelAndView userLogin(@PathVariable("key") String key, String userName, String
  // password) {
  // ModelAndView mav = new ModelAndView();
  // return mav;
  // }


  @RequestMapping(value = "{key}/login", method = RequestMethod.POST)
  @ResponseBody
  public Map userLogin(UserAuthRequest user, HttpServletRequest request) {

    String loginName = user.getLoginName();
    String password = user.getPassword();
    String remoteHost = request.getRemoteHost();


    try {
      ComUser comUser = comUserService.userLogin(loginName, password);
      if (comUser == null) {

      }

      Date accessTime = DateTimeUtils.now();

      UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
      String tokenString = JSONObject.toJSONString(token);
      String aaa =
          String.format("%s-%s-%d", tokenString, request.getRemoteHost(), request.getRemotePort()); // TODO

      AuthUser authUser = new AuthUser();
      authUser.setLoginName(loginName);
      authUser.setPassword(password);
      authUser.setUserId(comUser.getUserId());
      authUser.setUserName(comUser.getUserName());
      authUser.setDeptId(comUser.getDeptId());
      authUser.setDeptName(comUser.getDeptName());
      authUser.setRoleId("" + comUser.getRoleId());// TODO
      authUser.setAccessTime(accessTime);
      authUser.setRemoteHost(remoteHost);
      authorityService.addUserToken(aaa, authUser);

      Map result = new HashMap<String, String>();
      result.put(tokenString, loginName + password);
      return result;
      // TODO
    } catch (BusinessException e) {
      log.error("error:{}", e);
    } catch (LockedAccountException e) {
      log.error("error:{}", e);
      e.printStackTrace();
    } catch (UnknownAccountException e) {
      log.error("error:{}", e);
    } catch (AuthenticationException e) {
      log.error("error:{}", e);

    } catch (Exception e) {
      log.error("error:{}", e);
    }
    return null;
  }

  @RequestMapping(value = "{key}/userLogout", method = RequestMethod.GET)
  @ResponseBody
  public String userLogout(String token) {
    int result = -1;
    try {
      result = authorityService.removeUserToken(token);
    } catch (BusinessException e) {
      log.error("error:{}", e);
    } catch (Exception e) {
      log.error("error:{}", e);
    }
    return result > 0 ? "success" : "error";
  }

  @RequestMapping(value = "{key}/userLoginTest", method = RequestMethod.GET)
  @ResponseBody
  public String userLoginTest(String userName, String password, HttpServletRequest request) {

    log.info("object========>>>>:{}", request);
    String method = request.getMethod();
    String remoteHost = request.getRemoteHost();
    int remotePort = request.getRemotePort();
    log.info("request method:{}, remoteHost:{}, remotePort:{}", method, remoteHost, remotePort);

    Object object = RequestContextHolder.getRequestAttributes();
    log.info("object========>>>>:{}", object);

    UserAuthRequest user = new UserAuthRequest();
    user.setLoginName(userName);
    user.setPassword(password);
    String result = null;
    result = JSONObject.toJSONString(this.userLogin(user, request));
    return result;
  }


  /**
   * 用户登录验证，主要面向资格平台
   * 
   * @param token
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "validUserToken", method = RequestMethod.POST)
  @ResponseBody
  public Map validUserToken(String token, HttpServletRequest request) throws BusinessException {

    AuthUser user = authorityService.getUserByToken(token);

    if (user == null) {
      return null;
    }

    Map<String, String> result = new HashMap<String, String>();
    result.put("userId", user.getUserId());
    result.put("userName", user.getUserName());
    result.put("deptId", user.getDeptId());
    result.put("deptName", user.getDeptName());
    result.put("roleId", user.getRoleId());

    log.info("validUserToken, token:{}, userId:{}", token, user.getUserId());

    return result;
  }
}
