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
@RequestMapping(value="area")
public class CommAreaController {
  Logger log = LoggerFactory.getLogger(CommAreaController.class);

  @RequestMapping(value = "listAreas", method = RequestMethod.POST)
  @ResponseBody
  public Object listAreas(@PathVariable(value="role") String role, int page, int limit){
    return null;
  }

  @RequestMapping(value="greeting", method = RequestMethod.GET)
  @ResponseBody
  public String greeting(String name){
    return "Hello, " + name;
  }
}
