package com.cmbchina.activity.tran.restful;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangtingbang on 16/3/19.
 */
public class BasicController {

  @ExceptionHandler
  public String exception(HttpServletRequest request, Exception exception){
    return exception.getMessage();
  }
}
