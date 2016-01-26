package com.xxx.passport.annotation;

import com.xxx.bean.Module;
import com.xxx.bean.RightType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在控制器或其父类中标注 {@link LoginRequired}表示http访问该控制器时，必须是已经登录的；<br>
 * 也可以只在控制器的action方法标注，则表示访问该方法需要是已经登录的。
 * <p>
 * 
 */
@Inherited
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {

	int LOGIN_REQUIRED_PRIV = 20000;

	Module privilege();   //权限要求

	RightType rightType() default RightType.READ; //读写权限，默认所有都有

//	public ContentType contentType() default ContentType.HTML; //视图的ContentType
}
