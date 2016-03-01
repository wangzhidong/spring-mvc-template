package com.cmbchina.activity.tran.restful.qualification.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmbchina.activity.busi.external.dto.QualificationReqParam;
import com.cmbchina.activity.busi.external.service.facade.CmbQualificationService;
import com.google.common.collect.Lists;
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
@RequestMapping(value = "h5/qualification")
public class QualificationController4H5 {
  private static final Logger log = LoggerFactory.getLogger(QualificationController4H5.class);

  @Autowired
  private CmbQualificationService cmbQualificationService; // TODO

  @RequestMapping(value = "getQualificationByActIdAndUserId",
      method = {RequestMethod.POST, RequestMethod.GET})
  @ResponseBody
  public List queryQualification() {

    String CUST_ID = ""; //零售客户号/信用卡客户号
    String LOGIN_TYP = "D"; //手机银行登录类型 必输
    String CRD_LST = "6226097550503562,6226096550503563"; //一网通关联卡列表
    String CRD_TYP_LST = "01,01"; //一网通关联卡类型列表
    String APP_ID = "ios1"; //设备号 必输
    String DOC_TYP_CD = "P01"; //证件类型 必输
    String DOC_NBR = "10"; //证件号码 必输
    String CUST_NM = "A"; //客户姓名 必输
    String QRY_TYP = "Q"; //查询类型 必输
    String QUA_GRP_ID = "1001"; //资格组ID  必输
    String QUA_ID = "100101"; //资格ID  QRY_TYP=Q时必输

    List cardList = Lists.newArrayList(CRD_LST.split(","));
    List cardTypeList = Lists.newArrayList(CRD_TYP_LST.split(","));

    QualificationReqParam param = new QualificationReqParam();
    param.setCustomerId(CUST_ID);
    param.setLoginType(LOGIN_TYP);
    param.setCardList(cardList);
    param.setCardTypeList(cardTypeList);
    param.setDeviceId(APP_ID);
    param.setIdentType(DOC_TYP_CD);
    param.setIdentNo(DOC_NBR);
    param.setCustomerName(CUST_NM);
    param.setQueryType(QRY_TYP);
    param.setQauGroupId(QUA_GRP_ID);
    param.setQuaId(QUA_ID);
    log.info("param:{}", param);
    log.info("param:{}", JSONObject.toJSONString(param));
    return cmbQualificationService.getQualificationRules(param);
  }
}
