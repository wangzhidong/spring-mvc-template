package com.cmbchina.activity.tran.restful.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangtingbang on 16/1/11.
 */

@Controller
@RequestMapping(value="department")
public class CommonDepartmentController {

  private static final Logger log = LoggerFactory.getLogger(CommonDepartmentController.class);

  @RequestMapping(value="{role}/listDepartments", method = RequestMethod.POST)
  @ResponseBody
  public Object listDepartments(@PathVariable("role") String role, int page, int limit ){
    //TODO
    return null;
  }
}