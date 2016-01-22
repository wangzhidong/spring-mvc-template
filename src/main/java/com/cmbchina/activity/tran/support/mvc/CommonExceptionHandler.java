package com.cmbchina.activity.tran.support.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangtingbang on 16/1/12.
 */
public class CommonExceptionHandler{// implements HandlerExceptionResolver{

  private static  final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
//  @Override 
  public ModelAndView resolveException(HttpServletRequest request,
    HttpServletResponse response, Object handler, Exception ex) {
    ModelAndView modelAndView = null;
    modelAndView = new ModelAndView("/common/index");
    modelAndView.addObject("message", ex.getLocalizedMessage());
    modelAndView.addObject("success", "failed"); //TODO
    modelAndView.addObject("code", "999999"); // TODO
    log.error("modelAndView ---->>> / : {}", ex);
    return modelAndView;
  }
}
