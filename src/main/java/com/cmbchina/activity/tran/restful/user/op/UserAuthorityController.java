package com.cmbchina.activity.tran.restful.user.op;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangtingbang on 15/12/10.
 */

@Controller
@RequestMapping(value="user/op")
public class UserAuthorityController {
  @RequestMapping(value ="{key}/login", method= RequestMethod.POST)
  public ModelAndView userLogin( @PathVariable("key") String key,
    String userName, String password ){
    ModelAndView mav = new ModelAndView();
    return mav;
  }
}
