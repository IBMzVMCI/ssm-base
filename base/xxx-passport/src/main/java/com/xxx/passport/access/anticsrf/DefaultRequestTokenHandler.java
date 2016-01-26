package com.xxx.passport.access.anticsrf;

import com.xxx.passport.util.AntiCsrfUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * {@link RequestTokenInterceptor}拦截器处理类，{@link RequestTokenHandler}的实现类。<br/>
 */
public class DefaultRequestTokenHandler implements RequestTokenHandler {

	/**
	 * 取出requestToken的值，检查token是否合法。
	 */
	public boolean validateToken(HttpServletRequest request) {
		return AntiCsrfUtil.validateToken(request, RequestTokenInterceptor.REQ_TOKEN_ATTRIBUTE_NAME);
	}

	@Override
	public String generateToken(HttpServletRequest request) {
		return AntiCsrfUtil.generateToken(request);
	}
}
