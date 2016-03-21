package com.cmbchina.activity.tran.support.mvc;

import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.commons.bean.exception.BusinessExceptionDic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionTokenInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  AuthorityService authorityService;
  private static final Logger log = LoggerFactory.getLogger(SessionTokenInterceptor.class);

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public boolean preHandle(HttpServletRequest request,
    HttpServletResponse response, Object handler) throws Exception{

    String uri = request.getRequestURI();
    String token = (String)request.getSession().getAttribute("token");
    log.info("=======================[token:{}]==================", token);
//    if(StringUtils.isEmpty(token)){
//    	if(uri.indexOf("/common/auth")==-1) { //TODO white list
//        log.error("authorized access(token is null) for:{}", uri);
//        throw BusinessExceptionDic.EX_USR_UNAUTHORIZED;
//      }
//    }
    return true;
  }
}
