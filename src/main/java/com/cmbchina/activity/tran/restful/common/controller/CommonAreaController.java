package com.cmbchina.activity.tran.restful.common.controller;

import com.cmbchina.activity.busi.common.service.ComAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtingbang on 16/1/11.
 */
@Controller
@RequestMapping(value="common/area")
public class CommonAreaController {
  Logger log = LoggerFactory.getLogger(CommonAreaController.class);

  @Autowired
  private ComAreaService comAreaService;

  @RequestMapping(value = "listAreas", method = {RequestMethod.POST,RequestMethod.GET})
  @ResponseBody
  public List listAreas(String role, HttpSession session) {

    log.info("listAreas call:{}", role);
    List result = comAreaService.listAreas(1, 65535);
    return result;
  }
  @RequestMapping(value = "listAreasByGroup", method = {RequestMethod.POST,RequestMethod.GET})
  @ResponseBody
  public Map listAreasByGroup(HttpSession session){
    log.info("listAreasByGroup call");
    Map result = comAreaService.listAreasByGroup();
    return result;
  }
}
