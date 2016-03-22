package com.cmbchina.activity.tran.restful;

import com.cmbchina.activity.tran.pojo.AuthUser;
import com.cmbchina.activity.tran.pojo.CommonContext;

/**
 * Created by wangtingbang on 16/3/19.
 */
public class BasicController {
  protected CommonContext context;

  public void initContext(AuthUser user){
    if(user == null){
      return;
    }

    if(context == null){
      context = new CommonContext();
    }
    this.context.setSeqNo(user.getSeqNo());
    this.context.setUserId(user.getUserId());
    this.context.setUserName(user.getUserName());
  }

}
