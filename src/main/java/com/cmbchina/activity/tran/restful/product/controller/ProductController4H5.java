package com.cmbchina.activity.tran.restful.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.common.service.CustomerService;
import com.cmbchina.commons.bean.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by wangtingbang on 16-2-17.
 */
@Controller
@RequestMapping(value = "h5/product")
public class ProductController4H5 {
  private static final Logger log = LoggerFactory.getLogger(ProductController4H5.class);

  @Reference
  private CustomerService customerService;

  @Reference
  private AuthorityService authorityService;

  public void setCustomerService(CustomerService customerService){
    this.customerService = customerService;
  }

  public void setAuthorityService(AuthorityService authorityService){
    this.authorityService = authorityService;
  }

  private AuthUser getUserById(String userId){
    AuthUser user = null;
    try {
      user = authorityService.getUserByUserId(userId);
      if(user == null){
        log.error("user not login:{}", userId);
        return null;
      }
    }catch (BusinessException e){
      log.error("error:{}, {}", userId, e);
      return null;
    }catch (Exception e){
      log.error("error:{}, {}", userId, e);
      return null;
    }
    return user;
  }

  /**
   *
   * @param userId
   * @return
   */
  @RequestMapping(value = "getUserProductList", method = RequestMethod.POST)
  @ResponseBody
  public List getCustomerProductList(String userId){
    return null;
  }

}
