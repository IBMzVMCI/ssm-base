package com.xxx.web.controllers;

import com.xxx.bean.Hr;
import com.xxx.passport.commons.CookieManager;
import com.xxx.service.ActiveHolder;
import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Interceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * AutoLoginInterceptor
 */
@Interceptor(oncePerRequest = true)
public class AutoLoginInterceptor extends ControllerInterceptorAdapter {

    @Autowired
    private ActiveHolder hostHolder;

    @Override
    public int getPriority() {
        return 10000;
    }

    @Override
    protected Object before(Invocation inv) throws Exception {
        Hr host = hostHolder.getUser();
        if (host == null) {
            String url = checkAutoLogin(inv);
            if (!StringUtils.isEmpty(url)) {
                return "r:" + url;
            }
        }
        return Boolean.TRUE;
    }

    private String checkAutoLogin(Invocation inv) {
        try {
            HttpServletRequest request = inv.getRequest();

            if (StringUtils.isNotEmpty(CookieManager.getInstance().getCookie(request,
                    "COOKIE_PASSPORT"))
                    && StringUtils.isNotEmpty(CookieManager.getInstance().getCookie(request,
                    "AUTOLOGIN"))) {
                String url = getResourceFullLocation(request);
                if (StringUtils.isEmpty(url)) {
                    logger.error("get url failed");
                }
                String redirectUrl = "http://localhost/Login.do?errURL=" + url + "&origURL="
                        + url;
                return redirectUrl;
            }
            return null;
        } catch (Exception ex) {
            logger.error("check auto login failed", ex);
            return null;
        }
    }

    private String getResourceFullLocation(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String url = "127.0.0.1/" + uri;
        String queryString = request.getQueryString();
        if (queryString != null) {
            url = url + "?" + queryString;
        }
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

}
