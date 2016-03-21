package com.cmbchina.activity.tran.support.mvc;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtingbang on 16/1/12.
 */
// public class CommonExceptionHandler {// implements HandlerExceptionResolver{

// private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
//// @Override
// public ModelAndView resolveException(HttpServletRequest request,
// HttpServletResponse response, Object handler, Exception ex) {
// ModelAndView modelAndView = null;
// modelAndView = new ModelAndView("/common/index");
// modelAndView.addObject("message", ex.getLocalizedMessage());
// modelAndView.addObject("success", "failed"); //TODO
// modelAndView.addObject("code", "999999"); // TODO
// log.error("modelAndView ---->>> / : {}", ex);
// return new ModelAndView();
// }
// }
//public class CommonExceptionHandler extends SimpleMappingExceptionResolver{//extends ExceptionHandlerExceptionResolver {
//
//  private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
//
//  @Override
//  protected ModelAndView doResolveException(HttpServletRequest request,
//      HttpServletResponse response, Object handler, Exception ex) {
//    // TODO Auto-generated method stub
//	  
//	log.error("error------>>>>>:{}", ex);
//
//    HandlerMethod mathod = (HandlerMethod) handler;
//    ResponseBody body = mathod.getMethodAnnotation(ResponseBody.class);
//    // [email protected]�
//    if (body == null) {
//      return super.doResolveException(request, response, handler, ex);
//    }
//    ModelAndView mv = new ModelAndView("/exception/ajaxexception");
//    // 设置状态码
//    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    // 设置ContentType
//    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//    // 避免乱码
//    response.setCharacterEncoding("UTF-8");
//    response.setHeader("Cache-Control", "no-cache, must-revalidate");
//
//    try {
////      response.getWriter().write("{\"success\":false,\"msg\":\"" + ex.getMessage() + "\"}");
//      Method method = mathod.getMethod();
//      if (method == null) {
//        return null;
//      }
//    ModelAndView returnValue = super.doResolveException(request, response, handler, ex);
//      ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
//      if (responseBodyAnn != null) {
//        try {
//          ResponseStatus responseStatusAnn =
//              AnnotationUtils.findAnnotation(method, ResponseStatus.class);
//          if (responseStatusAnn != null) {
//            HttpStatus responseStatus = responseStatusAnn.value();
//            String reason = responseStatusAnn.reason();
//            if (!StringUtils.hasText(reason)) {
//              response.setStatus(responseStatus.value());
//            } else {
//              try {
//                response.sendError(responseStatus.value(), reason);
//              } catch (IOException e) {
//              }
//            }
//          }
//
//          return handleResponseBody(returnValue, request, response);
//        } catch (Exception e) {
//          e.printStackTrace();
//          return null;
//        }
//      }
//
//      if (returnValue.getViewName() == null) {
//        returnValue.setViewName(defaultErrorView);
//      }
//
//      return returnValue;
//      
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//
//    return mv;
//  }
//  
//  private String defaultErrorView;
//
//  public String getDefaultErrorView() {
//    return defaultErrorView;
//  }
//
//  public void setDefaultErrorView(String defaultErrorView) {
//    this.defaultErrorView = defaultErrorView;
//  }
//
////  protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request,
////      HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
////
////    if (handlerMethod == null) {
////      return null;
////    }
////    Method method = handlerMethod.getMethod();
////    if (method == null) {
////      return null;
////    }
////    ModelAndView returnValue =
////        super.doResolveHandlerMethodException(request, response, handlerMethod, exception);
////    ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
////    if (responseBodyAnn != null) {
////      try {
////        ResponseStatus responseStatusAnn =
////            AnnotationUtils.findAnnotation(method, ResponseStatus.class);
////        if (responseStatusAnn != null) {
////          HttpStatus responseStatus = responseStatusAnn.value();
////          String reason = responseStatusAnn.reason();
////          if (!StringUtils.hasText(reason)) {
////            response.setStatus(responseStatus.value());
////          } else {
////            try {
////              response.sendError(responseStatus.value(), reason);
////            } catch (IOException e) {
////            }
////          }
////        }
////
////        return handleResponseBody(returnValue, request, response);
////      } catch (Exception e) {
////        return null;
////      }
////    }
////
////    if (returnValue.getViewName() == null) {
////      returnValue.setViewName(defaultErrorView);
////    }
////
////    return returnValue;
////
////  }
//
//
//  @SuppressWarnings({"unchecked", "rawtypes"})
//  private ModelAndView handleResponseBody(ModelAndView returnValue, HttpServletRequest request,
//      HttpServletResponse response) throws ServletException, IOException {
//
//    Map value = returnValue.getModelMap();
//    HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
//    List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
//    if (acceptedMediaTypes.isEmpty()) {
//      acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
//    }
//    MediaType.sortByQualityValue(acceptedMediaTypes);
//    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
//    Class<?> returnValueType = value.getClass();
//    
//    return new ModelAndView();
//
////    List<HttpMessageConverter<?>> messageConverters = super.get.getMessageConverters();
////    if (messageConverters != null) {
////      for (MediaType acceptedMediaType : acceptedMediaTypes) {
////        for (HttpMessageConverter messageConverter : messageConverters) {
////          if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
////            messageConverter.write(value, acceptedMediaType, outputMessage);
////            return new ModelAndView();
////          }
////        }
////      }
////    }
////    if (logger.isWarnEnabled()) {
////      logger.warn("Could not find HttpMessageConverter that supports return type ["
////          + returnValueType + "] and " + acceptedMediaTypes);
////    }
////    return null;
//  }
//}

public class CommonExceptionHandler implements HandlerExceptionResolver {
	  private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

	  @Override
	  public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp,
	      Object handler, Exception ex) {
	    ModelAndView mav = null;
	    String xRequestedWith = req.getHeader("X-Requested-With");
	    if (!StringUtils.isEmpty(xRequestedWith)) {
	      mav = new ModelAndView("common/ajaxexception");
	    } else {
	      mav = new ModelAndView("common/exception");
	    }
	    logger.error(ex.getLocalizedMessage());
	    mav.addObject("ex", ex);
	    return mav;
	  }

}
