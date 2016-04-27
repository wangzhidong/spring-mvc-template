package com.cmbchina.activity.tran.restful.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by wangtingbang on 16/1/12.
 */
@Controller
@RequestMapping(value = "test")
public class TestController {// extends BasicController{

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "greeting", method = RequestMethod.GET)
    @ResponseBody
    public String greeting(String name) {
        return "Hello, " + name;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List list() {
        List<String> list = Lists.newArrayList("string", "list", "array", "map");
        return list;
    }

    @RequestMapping(value = "map", method = RequestMethod.GET)
    @ResponseBody
    public Map map() {
        Map map = new HashMap();
        map.put("key", "key");
        map.put("data", "data");
        return map;
    }

    @RequestMapping(value = "listMap", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> listMap() {
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
    public Object exception() throws Exception {
        throw new Exception("error(test)");
    }

    /*
      @RequestMapping(value = "bizException", method = RequestMethod.GET)
      @ResponseBody
      public Object bizException( int flag ) throws BusinessException {
        if(flag == 0){
          BusinessException e = new BusinessException("100100", "flag == 0, throw new BusinessException");
          log.error("{}",e);
          throw e;
        }
        else if(flag == -1){
          return null;
        }
        return "bizException";
      }
      //*/
    @RequestMapping(value = "listVo", method = RequestMethod.GET)
    @ResponseBody
    public List<TestVO> listVo() {
        TestVO vo1 = new TestVO("111", 1, "name1", true, "comment", new Date());
        TestVO vo2 = new TestVO("222", 0, "name2", false, "description", new Date());
        List<TestVO> list = Lists.newArrayList(vo1, vo2);
        return list;
    }

    @RequestMapping(value = "redirect", method = RequestMethod.GET)
    @ResponseBody
    public RedirectView testRedirect(@RequestParam("param") String param) {
        RedirectView view = new RedirectView("http://www.baidu.com");
        Map attr = new HashMap();
        attr.put("param", param);
        view.setAttributesMap(attr);
        return view;//"redirect:http://www.baidu.com"+"&"+param;
    }


    public static void main(String[] argv) {
        String url = "http://58.61.30.125:8021/cmb_activity/activity_app/activity/index.html";
        if (!url.startsWith("http")) {
            System.err.println("http--://" + url);
        } else {
            System.err.println(url);
        }
    }

    @RequestMapping(value = "qryQuaInf/cif", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String fakeQryQuaInf(@RequestBody String body, HttpServletRequest request) throws Exception {
        String encoding = request.getHeader("Content-encoding");
        System.out.println(encoding);

        String data = new String(body.getBytes(encoding));

        System.out.println("body -:" + body);
        System.out.println("body 0:" + new String(body.getBytes()));
        System.out.println("body 1:" + new String(body.getBytes(encoding)));
        System.out.println("body 2:" + new String(body.getBytes(encoding), encoding));
        System.out.println("body 3:" + new String(body.getBytes(encoding), "UTF-8"));
        System.out.println("body 4:" + new String(body.getBytes("UTF-8"), encoding));
        System.out.println("body 5:" + new String(body.getBytes("UTF-8")));
        System.out.println("body 6:" + new String(body.getBytes("UTF-8"), "UTF-8"));
        Object obj = JSONObject.parse(body);
        Map map1 = (Map) obj;
        JSONObject obj2 = (JSONObject) map1.get("INFBDY");
        List list = (List) ((Map) map1.get("INFBDY")).get("LN58Q101X1");
//    for(Object tmp:list){

        Object tmp = list.get(0);
        Map map2 = (Map) tmp;
        String CUST_NM = (String) map2.get("CUST_NM");
        System.out.println("CUST_NM -:" + CUST_NM);
        System.out.println("CUST_NM 0:" + new String(CUST_NM.getBytes()));
        System.out.println("CUST_NM 1:" + new String(CUST_NM.getBytes(encoding)));
        System.out.println("CUST_NM 2:" + new String(CUST_NM.getBytes(encoding), encoding));
        System.out.println("CUST_NM 3:" + new String(CUST_NM.getBytes(encoding), "UTF-8"));
        System.out.println("CUST_NM 4:" + new String(CUST_NM.getBytes("UTF-8"), encoding));
        System.out.println("CUST_NM 5:" + new String(CUST_NM.getBytes("UTF-8")));
        System.out.println("CUST_NM 6:" + new String(CUST_NM.getBytes("UTF-8"), "UTF-8"));
//    }
        return JSONObject.toJSONString(obj);
    }

}
