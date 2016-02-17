package com.cmbchina.activity.tran.restful.user.external;

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
@RequestMapping(value = "user")
public class UserService4External {

  private static final Logger log = LoggerFactory.getLogger(UserService4External.class);

  private AuthorityService authorityService;


  public void setAuthorityService(AuthorityService authorityService){
    this.authorityService = authorityService;
  }


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
