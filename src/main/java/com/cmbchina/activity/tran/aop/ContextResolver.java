package com.cmbchina.activity.tran.aop;

import java.lang.annotation.*;

/**
 * Created by wangtingbang on 16/3/22.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ContextResolver {
}

