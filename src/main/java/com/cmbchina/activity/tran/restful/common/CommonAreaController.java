package com.cmbchina.activity.tran.restful.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmbchina.activity.busi.common.service.ComAreaService;
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
@RequestMapping(value="area")
public class CommonAreaController {
  Logger log = LoggerFactory.getLogger(CommonAreaController.class);

  @Reference
  private ComAreaService comAreaService;

  public void setAreaService(ComAreaService comAreaService){
    this.comAreaService = comAreaService;
  }

  @RequestMapping(value = "listAreas", method = RequestMethod.POST)
  @ResponseBody
  public Object listAreas(@PathVariable(value="role") String role, int page, int limit){
    return null;
  }
}
