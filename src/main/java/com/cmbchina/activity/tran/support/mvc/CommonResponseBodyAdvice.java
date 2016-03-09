package com.cmbchina.activity.tran.support.mvc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtingbang on 15/12/10.
 */
@ControllerAdvice(basePackages = "com.cmbchina.activity.tran")
public class CommonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

//  @Override
  public boolean supports(MethodParameter returnType,
    Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

//  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
    MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
    ServerHttpRequest request, ServerHttpResponse response) {
    Map map = new HashMap();
    map.put("code", "000000"); //TODO
    map.put("message", "success");
    map.put("data", body);

    if(String.class == returnType.getMethod().getReturnType()){
      return JSONObject.toJSONString(map);
    }
    return map;
  }
}
