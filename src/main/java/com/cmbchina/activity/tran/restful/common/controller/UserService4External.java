package com.cmbchina.activity.tran.restful.common.controller;

import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.commons.bean.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtingbang on 16/1/13.
 */
@Controller
@RequestMapping(value = "external/user")
public class UserService4External {

  private static final Logger log = LoggerFactory.getLogger(UserService4External.class);

  @Autowired
  private AuthorityService authorityService;


//  public void setAuthorityService(AuthorityService authorityService){
//    this.authorityService = authorityService;
//  }


  /**
   * 用户登录验证，主要面向资格平台
   * @param token
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "validUserToken", method = RequestMethod.POST)
  @ResponseBody
  public Map validUserToken(String token,HttpServletRequest request) throws BusinessException{

    AuthUser user = authorityService.getUserByToken(token);

    if(user == null){
      return null;
    }

    Map<String, String> result = new HashMap<String, String>();
    result.put("userId", user.getUserId());
    result.put("userName",user.getUserName());
    result.put("deptId",user.getDeptId());
    result.put("deptName",user.getDeptName());
    result.put("roleId",user.getRoleId());

    return result;
  }

}
