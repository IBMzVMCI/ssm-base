package com.xxx.passport.access.anticsrf;

import javax.servlet.http.HttpServletRequest;

/**
 * Token拦截器的处理接口。
 *
 */
public interface RequestTokenHandler {

	/**
	 * 验证token是否合法，合法返回true，不合法返回相关的提示。
	 * 
	 */
	boolean validateToken(HttpServletRequest request);
	
	/**
	 * 生成token
	 * 
	 * @param request
	 * @return
	 */
	String generateToken(HttpServletRequest request);
}
