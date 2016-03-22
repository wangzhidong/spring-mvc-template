package com.cmbchina.activity.tran.support.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionTokenInterceptor extends HandlerInterceptorAdapter {

  
  private static final Logger log = LoggerFactory.getLogger(SessionTokenInterceptor.class);

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public boolean preHandle(HttpServletRequest request,
    HttpServletResponse response, Object handler) throws Exception{

    String uri = request.getRequestURI();
    String token = (String)request.getSession().getAttribute("token");
    log.info("=======================[token:{}]==================", token);
    return true;
  }
}
