package com.cmbchina.activity.tran.aop;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.cmbchina.activity.tran.pojo.AuthUser;

/**
 * Created by wangtingbang on 16/3/22.
 */
@Aspect
public class ContextAspectJAdvice {

  /**
   * Pointcut 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数 该方法就是一个标识，不进行调用
   */
  // @Pointcut("execution(* initContext*(..))")
  @Pointcut("execution(* com.cmbchina.activity.tran.restful..*.*(..))")
  private void aspectjMethod() {};

  /**
   * Before 在核心业务执行前执行，不能阻止核心业务的调用。
   * 
   * @param joinPoint
   */
  @Before("aspectjMethod()")
  public void beforeAdvice(JoinPoint joinPoint) {
    try {
      Object[] args = joinPoint.getArgs();
      Method[] method_s = joinPoint.getTarget().getClass().getDeclaredMethods();
      System.err.println(joinPoint.getClass().getName());
      for (Method method : method_s) {
        System.err.println(method.getName());
      }

//      joinPoint.getTarget().getClass().getSuperclass()
      Method method = joinPoint.getTarget().getClass().getSuperclass().getDeclaredMethod("initContext", AuthUser.class);
      AuthUser user = new AuthUser();
      user.setSeqNo("SEQ"+(new Date()).getTime());
      user.setUserId("UID"+(new Date()).getTime());
      user.setUserName("UName"+(new Date()).getTime());
      
      method.invoke(joinPoint.getTarget(), user);
      for (Object arg : args) {
        if (arg instanceof HttpServletRequest) {
          System.out.println("arg instanceof httpservletrequest");
          HttpServletRequest request = (HttpServletRequest) arg;
          String token = (String) request.getSession().getAttribute("token");
          System.err.printf("token[%s] from httpservletRequest by aspectj\n", token);
          if (StringUtils.isEmpty(token)) {
            request.setAttribute("token", new Date());
          }
        } else {
          System.out.println("arg not instanceof httpservletrequest");
        }
      }
      System.out.println("-----beforeAdvice().invoke-----");
      System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");
      System.out.println(" 可通过joinPoint来获取所需要的内容");
      System.out.println("-----End of beforeAdvice()------");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
