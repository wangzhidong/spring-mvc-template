package com.cmbchina.activity.tran.restful.qualification.controller;

import com.cmbchina.activity.busi.external.service.ExternalQualificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangtingbang on 16-2-17.
 */
@Controller
@RequestMapping(value = "operation/qualification")
public class QualificationController4OP {
  private static final Logger log = LoggerFactory.getLogger(QualificationController4OP.class);


  @Autowired
  ExternalQualificationService externalQualificationService;

  @RequestMapping(value = "listAvailableQualifications", method = RequestMethod.GET)
  @ResponseBody
  public List listAvailableQualifications(String areaCode, String deptId, int page, int limit){

    log.info("listAvailableQualifications req: areaCode={}, deptId={}, page={}, limit={}", areaCode, deptId, page, limit);
    List result = externalQualificationService.listAvailableQualifications(areaCode, deptId,page,limit);
    log.info("result:{}", result == null || result.size()==0?"0":result);
    return result;
  }
}
