package com.cmbchina.activity.tran.restful.qualification.controller;

import com.cmbchina.activity.busi.external.service.ExternalQualificationService;
import com.cmbchina.activity.tran.restful.qualification.vo.Qualification;
import com.cmbchina.activity.tran.restful.qualification.vo.QualificationGroup;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtingbang on 16-2-17.
 */
@Controller
@RequestMapping(value = "operation/qualification")
public class QualificationController4OP {
  private static final Logger log = LoggerFactory.getLogger(QualificationController4OP.class);


  @Autowired
  ExternalQualificationService externalQualificationService;

  @RequestMapping(value = "listQualificationsIndex", method = {RequestMethod.GET, RequestMethod.POST})
  @ResponseBody
  public Map listQualificationsIndex(@RequestBody  String userId){

    log.info(String.format("listQualificationsIndex req: userId=%s", userId)); //TODO
//    List result = externalQualificationService.listAvailableQualifications(areaCode, deptId,page,limit);
//    log.info("result:{}", result == null || result.size()==0?"0":result);
//    log.info("result:{}", JSONObject.toJSONString(result));
    List<QualificationGroup> result = new ArrayList<>();

    QualificationGroup quaGroup1 = new QualificationGroup();
    QualificationGroup quaGroup2 = new QualificationGroup();
    QualificationGroup quaGroup3 = new QualificationGroup();

    quaGroup1.setQuaGroupId("quaGroup1");
    quaGroup1.setQuaGroupName("quaGroupName1");
    quaGroup1.setDescription("description1");

    quaGroup2.setQuaGroupId("quaGroup2");
    quaGroup2.setQuaGroupName("quaGroupName2");
    quaGroup2.setDescription("description2");

    quaGroup3.setQuaGroupId("quaGroup3");
    quaGroup3.setQuaGroupName("quaGroupName3");
    quaGroup3.setDescription("description3");


    Qualification qua11 = new Qualification();
    qua11.setQuaId("quaId11");
    qua11.setQuaName("quaName11");
    qua11.setDescription("description11");

    Qualification qua12 = new Qualification();
    qua12.setQuaId("quaId12");
    qua12.setQuaName("quaName12");
    qua12.setDescription("description12");

    Qualification qua13 = new Qualification();
    qua13.setQuaId("quaId13");
    qua13.setQuaName("quaName13");
    qua13.setDescription("description13");

    quaGroup1.setQualifications(Lists.newArrayList(qua11, qua12, qua13));


    Qualification qua21 = new Qualification();
    qua21.setQuaId("quaId21");
    qua21.setQuaName("quaName21");
    qua21.setDescription("description21");

    Qualification qua22 = new Qualification();
    qua22.setQuaId("quaId22");
    qua22.setQuaName("quaName22");
    qua22.setDescription("description22");

    Qualification qua23 = new Qualification();
    qua23.setQuaId("quaId23");
    qua23.setQuaName("quaName23");
    qua23.setDescription("description23");

    quaGroup2.setQualifications(Lists.newArrayList(qua21, qua22, qua23));


    Qualification qua31 = new Qualification();
    qua31.setQuaId("quaId31");
    qua31.setQuaName("quaName31");
    qua31.setDescription("description31");
    Qualification qua32 = new Qualification();
    qua32.setQuaId("quaId32");
    qua32.setQuaName("quaName32");
    qua32.setDescription("description32");
    Qualification qua33 = new Qualification();
    qua33.setQuaId("quaId33");
    qua33.setQuaName("quaName33");
    qua33.setDescription("description33");
    quaGroup3.setQualifications(Lists.newArrayList(qua31, qua32, qua33));

    result.add(quaGroup1);
    result.add(quaGroup2);
    result.add(quaGroup3);
    return this.fakeQualification(2,3);
  }

  private Map fakeQualification(int limit, int subLimit){
    Map result = new HashMap(limit);
    for(int i=0;i<limit;i++){
      Map group = new HashMap();
      List quaList = new ArrayList();
      for(int j=0;j<subLimit;j++){
        Map sub = new HashMap();
        sub.put("quaId",String.format("quaId%d%d", i, j));
        sub.put("quaName",String.format("quaName%d%d", i, j));
        sub.put("description",String.format("description%d%d", i, j));
        quaList.add(sub);
      }

      group.put("quaGroupId", "quaGroupId"+i);
      group.put("quaGroupName", "quaGroupName"+i);
      group.put("description", "description"+i);
      group.put("childQuaList",quaList);

      result.put("quaGroupId"+i, group);
    }
    return result;
  }

  public static void main(String[] argv){
    QualificationController4OP op = new QualificationController4OP();
    System.out.println(op.fakeQualification(2,3));
  }
}
