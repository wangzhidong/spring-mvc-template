package com.cmbchina.activity.tran.restful;

import org.springframework.context.annotation.Scope;

import com.cmbchina.activity.tran.pojo.AuthUser;
import com.cmbchina.activity.tran.pojo.CommonContext;

/**
 * Created by wangtingbang on 16/3/19.
 */
//@Scope("prototype") //无用，子类还是singleton 
public class BasicController {
  protected CommonContext context;

  public void initContext(AuthUser user){
    if(user == null){
      return;
    }

    if(context != null){
      this.context.setSeqNo(null);
      this.context.setUserId(null);
      this.context.setUserName(null);
      this.context = null;
    }
    context = new CommonContext();
    this.context.setSeqNo(user.getSeqNo());
    this.context.setUserId(user.getUserId());
    this.context.setUserName(user.getUserName());
  }

  public void destroyContext(){
    this.context.setSeqNo(null);
    this.context.setUserId(null);
    this.context.setUserName(null);
    this.context = null;
  }
}
