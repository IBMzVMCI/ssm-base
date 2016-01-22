package com.xxx.commons.admin;

import com.xxx.commons.loginrequired.LoginRequired;

import java.lang.annotation.*;

/**
 *
 */
@Inherited
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@LoginRequired
public @interface AdminProtection {

    String id();
}
