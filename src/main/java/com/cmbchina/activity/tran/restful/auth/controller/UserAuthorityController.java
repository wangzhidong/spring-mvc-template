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

  @Autowired
  private AuthorityService authorityService;

  @Autowired
  private ComUserService comUserService;

  @RequestMapping(value = "{key}/login", method = RequestMethod.GET)
  public ModelAndView userLogin(@PathVariable("key") String key, String userName, String password) {
    ModelAndView mav = new ModelAndView();
    return mav;
  }


  @RequestMapping(value = "{key}/login", method = RequestMethod.POST)
  @ResponseBody
  public Map userLogin(UserAuthRequest user, HttpServletRequest request) throws BusinessException {

    String loginName = user.getLoginName();
    String password = user.getPassword();

    ComUser comUser = comUserService.userLogin(loginName, password);

    Date accessTime = DateTimeUtils.now();

    try {
      UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
      // currentUser.login(token);
      String tokenString = JSONObject.toJSONString(token);
      String aaa =
          String.format("%s-%s-%d", tokenString, request.getRemoteHost(), request.getRemotePort());
      AuthUser authUser = new AuthUser();
      authUser.setLoginName(loginName);
      authUser.setPassword(password);
      authUser.setUserId(comUser.getUserId());
      authUser.setUserName(comUser.getUserName());
      authUser.setDeptId(comUser.getDeptId());
      authUser.setDeptName(comUser.getDeptName());
      authUser.setRoleId(""+comUser.getRoleId());//TODO
      authUser.setAccessTime(accessTime);
      // authorityService.addUserToken(tokenString, authUser);
      authorityService.addUserToken(aaa, authUser);

      Map result = new HashMap<String, String>();
      result.put(tokenString, loginName + password);
      return result;
      // TODO
    } catch (LockedAccountException e) {
      e.printStackTrace();
    } catch (UnknownAccountException e) {

      e.printStackTrace();
    } catch (AuthenticationException e) {

      e.printStackTrace();
    } catch (Exception e) {

      e.printStackTrace();
    }
    return new HashMap();
  }

  @RequestMapping(value = "{key}/userLogout", method = RequestMethod.GET)
  @ResponseBody
  public String userLogout(String token) {
    int result = -1;
    try {
      result = authorityService.removeUserToken(token);
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result > 0 ? "success" : "error";
  }

  @RequestMapping(value = "{key}/userLoginTest", method = RequestMethod.GET)
  @ResponseBody
  public String userLoginTest(String userName, String password, HttpServletRequest request) {

    System.out.println("object========>>>>:\n\t" + (request));
    String method = request.getMethod();
    String remoteHost = request.getRemoteHost();
    int remotePort = request.getRemotePort();
    System.out.printf("request method:%s, remoteHost:%s, remotePort:%d\n", method, remoteHost,
        remotePort);

    Object object = RequestContextHolder.getRequestAttributes();
    System.out.println("object========>>>>:\n\t" + (object));

    // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
    // .getRequestAttributes()).getRequest();
    //
    // System.out.println("http_request========>>>>:\n\t"+JSONObject.toJSONString(request));
    UserAuthRequest user = new UserAuthRequest();
    user.setLoginName(userName);
    user.setPassword(password);
    String result = null;
    try {
      result = JSONObject.toJSONString(this.userLogin(user, request));
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
