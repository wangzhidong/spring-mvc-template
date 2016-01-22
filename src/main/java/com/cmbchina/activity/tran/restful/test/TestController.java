package com.cmbchina.activity.tran.restful.test;

import com.cmbchina.commons.bean.BusinessException;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by wangtingbang on 16/1/12.
 */
@Controller
@RequestMapping(value="test")
public class TestController {

  @RequestMapping(value="greeting", method = RequestMethod.GET)
  @ResponseBody
  public String greeting(String name){
    return "Hello, " + name;
  }

  @RequestMapping(value = "list", method = RequestMethod.GET)
  @ResponseBody
  public List list(){
    List<String> list = Lists.newArrayList("string", "list", "array","map");
    return list;
  }
  @RequestMapping(value = "map", method = RequestMethod.GET)
  @ResponseBody
  public Map map(){
    Map map = new HashMap();
    map.put("key", "key");
    map.put("data", "data");
    return map;
  }

  @RequestMapping(value = "listMap", method = RequestMethod.GET)
  @ResponseBody
  public List<Map> listMap(){
    List<Map> list = new ArrayList<Map>();
    Map map = new HashMap();
    map.put("key", "key");
    map.put("data", "data");
    list.add(map);

    Map code = new HashMap();
    code.put("code", 6000113);
    list.add(code);

    return list;
  }

  @RequestMapping(value = "exception", method = RequestMethod.GET)
  @ResponseBody
  public Object exception() throws Exception{
    throw new Exception("error(test)");
  }

  @RequestMapping(value = "bizException", method = RequestMethod.GET)
  @ResponseBody
  public Object bizException( int flag ) throws BusinessException {
    if(flag == 0){
      throw new BusinessException("100100");
    }
    else if(flag == -1){
      return null;
    }
    return "bizException";
  }
  @RequestMapping(value = "listVo", method = RequestMethod.GET)
  @ResponseBody
  public List<TestVO> listVo(){
    TestVO vo1 = new TestVO("111",1, "name1", true, "comment", new Date());
    TestVO vo2 = new TestVO("222",0, "name2", false, "description", new Date());
    List<TestVO> list = Lists.newArrayList(vo1, vo2);
    return list;
  }
}
