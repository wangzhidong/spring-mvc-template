package com.cmbchina.activity.tran.restful.user.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.tran.restful.user.auth.vo.UserAuthRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cmbchina.commons.bean.BusinessException;

/**
 * Created by wangtingbang on 15/12/10.
 */

@Controller
@RequestMapping(value="user/auth")
public class UserAuthorityController {
  @RequestMapping(value ="{key}/login", method= RequestMethod.GET)
  public ModelAndView userLogin( @PathVariable("key") String key,
    String userName, String password ){
    ModelAndView mav = new ModelAndView();
    return mav;
  }


  @RequestMapping(value = "{key}/login", method = RequestMethod.POST)
  @ResponseBody
  public Map userLogin(UserAuthRequest user, HttpServletRequest request) throws BusinessException{

    Subject currentUser = SecurityUtils.getSubject();

    if(currentUser.isAuthenticated()){
      // TODO
      // return
    }

    String loginName = user.getLoginName();
    String password = user.getPassword();

    try{
      UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
      currentUser.login(token);
      String tokenString = JSONObject.toJSONString(token);
      Map result = new HashMap<String, String>();
      result.put(tokenString, loginName + password);
      return result;
      //TODO
    }catch(LockedAccountException e){
      e.printStackTrace();
    }catch(UnknownAccountException e){

      e.printStackTrace();
    }catch (AuthenticationException e){

      e.printStackTrace();
    }catch(Exception e){

      e.printStackTrace();
    }
    return  new HashMap();
  }

  @RequestMapping(value = "{key}/userLoginTest", method = RequestMethod.GET)
  @ResponseBody
  public String userLoginTest(String userName, String password){
    UserAuthRequest user = new UserAuthRequest();
    user.setLoginName(userName);
    user.setPassword(password);
    String result = null;
    try {
      result = JSONObject.toJSONString(this.userLogin(user, null));
    }catch(BusinessException e){
      e.printStackTrace();
    }catch (Exception e){
      e.printStackTrace();
    }
    return result;
  }
}
