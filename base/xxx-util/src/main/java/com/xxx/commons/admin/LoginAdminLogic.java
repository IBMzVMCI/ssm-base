package com.xxx.commons.admin;

import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import com.xxx.commons.util.CookieManager;
import com.xxx.commons.util.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class LoginAdminLogic {

    private static Logger logger = LoggerFactory.getLogger(LoginAdminLogic.class);

    private static final String CACHE_ADMIN_COOKIE_TICKET = "admin_cookie_ticket";

    public static final String ADMIN_COOKIE = "admin";

    public static void resetCookie(HttpServletResponse response, int userId) {
        Random rand = new Random(10);
        RedisCacheManager redisCacheManager = RedisCacheManager.getInstance();
        Object obj = redisCacheManager.get(CACHE_ADMIN_COOKIE_TICKET + userId);
        String number;
        if (obj == null) number = String.valueOf(rand.nextInt(1000000));
        else number = (String) obj;
        redisCacheManager.set(CACHE_ADMIN_COOKIE_TICKET + userId, number, 3600);
        CookieManager.getInstance().saveCookie(response, LoginAdminLogic.ADMIN_COOKIE, number);
    }

    public static void removeCookie(int userId) {
        RedisCacheManager redisCacheManager = RedisCacheManager.getInstance();
        redisCacheManager.remove(CACHE_ADMIN_COOKIE_TICKET + userId);
    }

    public static boolean isCookieRight(int userId) {
        try {
            RedisCacheManager redisCacheManager = RedisCacheManager.getInstance();
            Object ticket = redisCacheManager.get(CACHE_ADMIN_COOKIE_TICKET + userId);
            return !(ticket == null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

}
