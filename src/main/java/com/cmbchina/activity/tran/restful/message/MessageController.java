package com.cmbchina.activity.tran.restful.message;

import com.cmbchina.commons.bean.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wangtingbang on 16/1/13.
 */

@Controller
@RequestMapping(value = "common/message")
public class MessageController {

  private static final Logger log = LoggerFactory.getLogger(MessageController.class);

  @RequestMapping(value = "getUserNotice", method = RequestMethod.POST)
  @ResponseBody
  public List<Object> getUserNotice(String userId)throws BusinessException{
    return null;
  }

  @RequestMapping(value = "addMessage", method = RequestMethod.POST)
  @ResponseBody
  public int addMessage(Object message) throws BusinessException{
    return 0;
  }
}
