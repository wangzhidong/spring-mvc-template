package com.cmbchina.activity.tran.restful.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmbchina.activity.busi.common.dto.AuthUser;
import com.cmbchina.activity.busi.common.service.AuthorityService;
import com.cmbchina.activity.busi.external.dto.ExternalProduct;
import com.cmbchina.activity.busi.external.service.ExternalProductService;
import com.cmbchina.commons.bean.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @Reference
  private ExternalProductService externalProductService;

  @Reference
  private AuthorityService authorityService;

  public void setExternalProductService(ExternalProductService externalProductService){
    this.externalProductService = externalProductService;
  }

  public void setAuthorityService(AuthorityService authorityService){
    this.authorityService = authorityService;
  }

  private List queryProductListByUserId(String userId, int page, int limit){

    AuthUser user;
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

    List<ExternalProduct> products = externalProductService.listAvailableProduct(
            user.getAreaCode(), user.getDeptId(), page,limit);

    return products;
  }

  /**
   *
   * @param userId
   * @param request
   * @return
     */
  public List<Map> listProductsIndex(String userId, HttpServletRequest request){

    List<ExternalProduct> products = this.queryProductListByUserId(userId, -1, -1);
    if(products == null || products.size() == 0){
      return null;
    }

    List<Map> result = new ArrayList<Map>();
    for(ExternalProduct product:products){
      Map map = new HashMap();
      map.put("productId",product.getProductId());
      map.put("productName",product.getProductName());
      map.put("productType",product.getProductType());
      result.add(map);
    }
    return result;
  }


  public List listProducts(String userId, HttpServletRequest request){

    List<ExternalProduct> products = this.queryProductListByUserId(userId, -1, -1);

    if(products == null){
      return null;
    }

    List<Map> result = new ArrayList<Map>();
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

      result.add(map);
    }
    return result;
  }
}
