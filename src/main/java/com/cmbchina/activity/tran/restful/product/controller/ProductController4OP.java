package com.cmbchina.activity.tran.restful.product.controller;

import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.external.dto.ExternalProduct;
import com.cmbchina.activity.busi.external.service.ExternalProductService;
import com.cmbchina.commons.bean.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtingbang on 16-2-17.
 */

@Controller
@RequestMapping(value = "operation/product")
public class ProductController4OP {
  private static final Logger log = LoggerFactory.getLogger(ProductController4OP.class);

  @Autowired
  private ExternalProductService externalProductService;

  @Autowired
  private AuthorityService authorityService;

//  public void setExternalProductService(ExternalProductService externalProductService){
//    this.externalProductService = externalProductService;
//  }

//  public void setAuthorityService(AuthorityService authorityService){
//    this.authorityService = authorityService;
//  }

  private List queryProductListByUserId(String userId, int page, int limit){

    AuthUser user;
    try {
      user = authorityService.getUserByUserId(userId);

      if(user == null){
        log.error("auth not login:{}", userId);
        return null;
      }
    }catch (BusinessException e){
      log.error("error:{}, {}", userId, e);
      return null;
    }catch (Exception e){
      log.error("error:{}, {}", userId, e);
      return null;
    }

//    List<ExternalProduct> products = externalProductService.listAvailableProduct(
//            user.getAreaCode(), user.getDeptId(), page,limit);

    return null;//products;
  }

  /**
   *
   * @param userId
   * @param request
   * @return
     */
  @RequestMapping(value = "listProductsIndex", method = {RequestMethod.POST,RequestMethod.GET})
  @ResponseBody
  public Map listProductsIndex(String userId, HttpServletRequest request){

//    List<ExternalProduct> products = this.queryProductListByUserId(userId, -1, -1);
    List<ExternalProduct> products = this.fakeProduct(5);
    if(products == null || products.size() == 0){
      return null;
    }

    Map result = new HashMap();
    for(ExternalProduct product:products){
      Map map = new HashMap();
      map.put("productId",product.getProductId());
      map.put("productName",product.getProductName());
      map.put("productType",product.getProductType());
      map.put("sellPrice", product.getSellPrice());
      map.put("marketPrice", product.getMarketPrice());
      map.put("stockBalance", product.getStockBalance());
      map.put("picUrl", "https://www.baidu.com/img/bd_logo1.png"); //TODO 加字段
      result.put(product.getProductId(), map);
    }
    return result;
  }


  public Map listProducts(String userId, HttpServletRequest request){

    List<ExternalProduct> products = this.queryProductListByUserId(userId, -1, -1);

    if(products == null){
      return null;
    }

    Map result = new HashMap();
    for(ExternalProduct product:products){
      Map map = new HashMap();
      map.put("productId", product.getProductId());
      map.put("productName", product.getProductName());
      map.put("productType", product.getProductType());
      map.put("sellPrice", product.getSellPrice());
      map.put("marketPrice", product.getMarketPrice());
      map.put("stockBalance", product.getStockBalance());
      map.put("commitUserId", product.getCommitUserId());
      map.put("commitUserName", product.getCommitUserName());
      map.put("commitTime", product.getCommitTime());
      map.put("status", product.getStatus());
      map.put("actGroupId", product.getActGroupId());
      map.put("actGroupName", product.getActGroupName());
      map.put("activityId", product.getActivityId());
      map.put("activityName", product.getActivityName());

      result.put(product.getProductId(), product);
    }
    return result;
  }

  private List fakeProduct(int limit){
    List result = new ArrayList();
    for(int idx = 1;idx<limit;idx++){

      ExternalProduct obj = new ExternalProduct();
      obj.setProductId("productId"+idx);
      obj.setProductName("productName"+idx);
      obj.setProductType((byte)((idx%3+1)*10));
      obj.setSellPrice(120.03);
      obj.setMarketPrice(150.00);
      obj.setStockBalance(20000L);

      result.add(obj);
    }
    return result;
  }
}
