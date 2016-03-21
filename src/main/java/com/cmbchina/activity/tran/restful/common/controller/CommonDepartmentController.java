package com.cmbchina.activity.tran.restful.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmbchina.activity.busi.common.service.ComDepartmentService;

/**
 * Created by wangtingbang on 16/1/11.
 */

@Controller
@RequestMapping(value = "common/department")
public class CommonDepartmentController {

  private static final Logger log = LoggerFactory.getLogger(CommonDepartmentController.class);

  @Autowired
  private ComDepartmentService comDepartmentService;

  @RequestMapping(value = "listDepartments",
//      method = {RequestMethod.POST, RequestMethod.GET})
    method = RequestMethod.GET)
  @ResponseBody
  public List listDepartments(int page, int limit, HttpServletRequest request) throws Exception{
    log.info("CommonDepartmentController.listDepartments call:{},{}", page, limit);
    List result = comDepartmentService.listDepartments(page, limit);
    return result;
  }
}
