package com.cmbchina.activity.tran.restful.product.controller;

import com.cmbchina.activity.busi.act.dto.ActBusiContext;
import com.cmbchina.activity.busi.act.service.ActivityService;
import com.cmbchina.commons.bean.BusinessException;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "external/product")
public class ProductController4External {

  private static final Logger log = LoggerFactory.getLogger(ProductController4External.class);

  @Autowired
  private ActivityService activityService;

//  public void setActivityService(ActivityService activityService){
//    this.activityService = activityService;
//  }


  /**
   * 面向商品/权益系统
   * 用于查询商品关联的活动
   * @param productId
   * @param request
   * @return
   */
  @RequestMapping(value = "getActivitiesByProductIds", method = RequestMethod.POST)
  @ResponseBody
  public List getActivitiesByProductIds(String[] productId, HttpServletRequest request){

    List productIds = Lists.newArrayList(productId);

    ActBusiContext context = new ActBusiContext();//TODO: set session info, gen reqSeqNo

    List products = null;

    try{
      products = activityService.getActivitiesByProduct(context, productIds);
    } catch (BusinessException e){
      log.error("error:{}", e);
      return null;
    }catch (Exception e){
      log.error("error:{}", e);
      return null;
    }
    return products;
  }

  /**
   * queryProductUsages
   */
  @RequestMapping(value = "queryProductUsages",method = RequestMethod.POST)
  @ResponseBody
  public Map queryProductUsages(@RequestBody HashMap req){

    Map param =(Map) req.get("param");
    // for obj:param
    // productId = obj;

    return null; // {$productId:{$activityId,$activityName, $activityStatus}}

  }

  /**
   * queryProductUsages
   */
//  @RequestMapping(value = "queryProductUsages",method = RequestMethod.POST)
//  @ResponseBody
  public Map queryProductUsages(@RequestBody ArrayList req){

    // for obj:req
    // productId = obj;

    return null; // {$productId:{$activityId,$activityName, $activityStatus}}

  }
}
