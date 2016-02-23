package com.cmbchina.activity.tran.restful.qualification.controller;

import com.cmbchina.activity.busi.common.dto.ComBusiContext;
import com.cmbchina.activity.busi.external.service.ExternalQualificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtingbang on 16-2-17.
 */
@Controller
@RequestMapping(value = "external/qualification")
public class QualificationController4External {

  private static final Logger log = LoggerFactory.getLogger(QualificationController4External.class);

  @Autowired
  private ExternalQualificationService externalQualificationService;

  @RequestMapping(value = "listAvailableQualifications", method = RequestMethod.POST)
  @ResponseBody
  public List listAvailableQuas(String areaCode, String deptId, String userId, int page, int limit, HttpServletRequest request){

    List result = this.externalQualificationService.listAvailableQualifications(areaCode,deptId, page, limit);

    return result;
  }

  @RequestMapping(value = "listSubQuas", method = RequestMethod.POST)
  @ResponseBody
  public List listSubQuas( String quaGroupId, HttpServletRequest request){

    ComBusiContext context = new ComBusiContext(); //TODO

    List result = this.externalQualificationService.getSubQualifications(quaGroupId);

    return result;
  }

  @RequestMapping(value = "queryQualificationUsages", method = RequestMethod.POST)
  @ResponseBody
  public Map queryQualificationUsages(String quaGroupId, String quaId){
    return null;
  }

}
