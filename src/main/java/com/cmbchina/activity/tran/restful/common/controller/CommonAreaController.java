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
import java.util.ArrayList;
import java.util.HashMap;
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
//    Map result = comAreaService.listAreasByGroup();
    Map result = this.fakeArea(5,3);
    return result;
  }
  private Map fakeArea(int limit, int subLimit){
    Map result = new HashMap(limit);
    for(int i=1;i<limit;i++){
      Map group = new HashMap();
//      List cityList = new ArrayList();
      Map city = new HashMap();
      for(int j=1;j<subLimit;j++){
        Map sub = new HashMap();
        sub.put("provinceId", "provinceId"+i);
        sub.put("provinceName", "provinceName"+i);
        sub.put("cityId",String.format("cityId%d%d", i, j));
        sub.put("cityName",String.format("cityName%d%d", i, j));
        city.put(sub.get("cityId"), sub);
      }

//      cityList.add(city);
      group.put("provinceId", "provinceId"+i);
      group.put("provinceName", "provinceName"+i);
      group.put("cityList",city);

      result.put("provinceId"+i, group);
    }
    return result;
  }
}
