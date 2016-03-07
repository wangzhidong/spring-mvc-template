package com.cmbchina.activity.tran.restful.common.controller;

import com.cmbchina.activity.busi.common.service.ComDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangtingbang on 16/1/11.
 */

@Controller
@RequestMapping(value = "common/department")
public class CommonDepartmentController {

  private static final Logger log = LoggerFactory.getLogger(CommonDepartmentController.class);

  @Autowired
  private ComDepartmentService comDepartmentService;

  @RequestMapping(value = "{role}/listDepartments",
      method = {RequestMethod.POST, RequestMethod.GET})
  @ResponseBody
  public List listDepartments(@PathVariable("role") String role, int page, int limit) {
    log.info("CommonDepartmentController.listDepartments call:{},{},{}", role, page, limit);
    List result = comDepartmentService.listDepartments(page, limit);
    return result;
  }
}
