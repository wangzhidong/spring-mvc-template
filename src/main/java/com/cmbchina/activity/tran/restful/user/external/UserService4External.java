package com.cmbchina.activity.tran.restful.user.external;

import com.cmb.commons.bean.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangtingbang on 16/1/13.
 */
@Controller
@RequestMapping(value = "user")
public class UserService4External {

  private static final Logger log = LoggerFactory.getLogger(UserService4External.class);

  /**
   * 用户登录验证，主要面向资格平台
   * @param token
   * @return
   * @throws BusinessException
   */
  @RequestMapping(value = "validUserToken", method = RequestMethod.POST)
  @ResponseBody
  public Object validUserToken(Object token) throws BusinessException{

    return null;
  }
}
