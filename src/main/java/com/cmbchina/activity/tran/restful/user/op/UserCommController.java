package com.cmbchina.activity.tran.restful.user.op;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangtingbang on 16/1/11.
 */

@Controller
@RequestMapping(value="user")
public class UserCommController {

  private static final Logger log = LoggerFactory.getLogger(UserCommController.class);

  @RequestMapping(value = "listUsersByDept")
  public Object listUsersByDept(@PathVariable("role") String role, String deptId, int page, int limit){
    return null;
  }
}
