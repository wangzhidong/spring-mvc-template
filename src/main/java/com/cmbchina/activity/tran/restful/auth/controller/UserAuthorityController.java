package com.cmbchina.activity.tran.restful.auth.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.dto.ComUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.common.service.ComUserService;
import com.cmbchina.activity.tran.restful.auth.vo.UserAuthRequest;
import com.cmbchina.commons.bean.BusinessException;
import com.cmbchina.commons.util.DateTimeUtils;

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

  @RequestMapping(value = "userLogin", method = {RequestMethod.POST, RequestMethod.GET})
  @ResponseBody
  public Map userLogin(UserAuthRequest user, HttpServletRequest request) {

    String loginName = user.getLoginName();
    String password = user.getPassword();
    String remoteHost = request.getRemoteHost();

    try {
      String existedToken = authorityService.getTokenByLoginName(loginName);
      if(!StringUtils.isEmpty(existedToken)){
        log.debug("existed token for logged in user:{}", loginName);
        AuthUser existedUser = authorityService.getUserByToken(existedToken);
        Map existed = new HashMap();
        existed.put("token",existedToken);
        existed.put("user", existedUser);
        return existed;
      }
      Map loginResult = comUserService.userLogin(loginName, password);
      if (loginResult == null) {
        log.error("无效用户:{}", loginName);
        return null;
      }
      ComUser comUser = (ComUser)(loginResult.get("user"));
      String token = (String)(loginResult.get("token"));

      Date accessTime = DateTimeUtils.now();

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
      authorityService.addUserToken(token, authUser);

      request.getSession().setAttribute("token", token);
      Map<String, String> response = new HashMap<String, String>();

      response.put("token",token);
      response.put("roleId",""+authUser.getRoleId());
      response.put("deptId",""+authUser.getDeptId());
      response.put("deptName",""+authUser.getDeptName());
      return response;
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

  @RequestMapping(value = "userLogout", method = RequestMethod.GET)
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

  @RequestMapping(value = "userLoginTest", method = RequestMethod.GET)
  @ResponseBody
  public String userLoginTest(String userName, String password, HttpServletRequest request, Model model, HttpSession session) {

    log.info("session:{}",session.getAttribute("token"));
    UserAuthRequest user = new UserAuthRequest();
    user.setLoginName(userName);
    user.setPassword(password);
    Map result = this.userLogin(user, request);
    String token = (String) result.get("token");
    session.setAttribute("token",token);
//    return token;
    return JSONObject.toJSONString(result);
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
  //TODO for test
  @RequestMapping(value = "validUserToken", method = RequestMethod.GET)
  @ResponseBody
  public Map validUserTokenGET(String token, HttpServletRequest request) throws BusinessException {
    return this.validUserToken(token, request);
  }
}
