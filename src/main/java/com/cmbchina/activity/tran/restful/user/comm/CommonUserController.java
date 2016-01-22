package com.cmbchina.activity.tran.restful.user.comm;

import com.cmbchina.commons.bean.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "user")
public class CommonUserController {

  private static final Logger log = LoggerFactory.getLogger(CommonUserController.class);

  public List listUsersByDept(String deptId, @RequestParam(value = "roleList[]") Byte[] roleList, int page, int limit) throws
    BusinessException{
    return null;
  }
}
