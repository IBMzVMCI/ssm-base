package com.xxx.commons.admin;

import com.xxx.bean.Module;
import com.xxx.commons.loginrequired.LoginRequired;

import java.lang.annotation.*;

/**
 *
 */
@Inherited
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@LoginRequired(privilege = Module.IGNORE)
public @interface AdminProtection {

    String id();
}
