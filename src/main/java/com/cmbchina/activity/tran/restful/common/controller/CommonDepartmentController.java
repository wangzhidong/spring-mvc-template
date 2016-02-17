package com.cmbchina.activity.tran.restful.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmbchina.activity.busi.common.service.ComDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value="common/department")
public class CommonDepartmentController {

  private static final Logger log = LoggerFactory.getLogger(CommonDepartmentController.class);

  @Reference
  private ComDepartmentService comDepartmentService;

  public void setComDepartmentService(ComDepartmentService comDepartmentService){
    if(log.isDebugEnabled()){
      log.debug("CommonDepartmentController.comDepartmentService init-->{}",comDepartmentService);
    }
    this.comDepartmentService = comDepartmentService;
  }

  @RequestMapping(value="{role}/listDepartments", method = RequestMethod.POST)
  @ResponseBody
  public List listDepartments(@PathVariable("role") String role, int page, int limit ){
    log.info("CommonDepartmentController.listDepartments call:{},{},{}", role, page, limit);
    List result = comDepartmentService.listDepartments(page,limit);
    return result;
  }
}
