package com.xxx.commons.util;

import com.xxx.bean.Hr;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XPassportManager implements PassportManager {

    private static String KEY_PREFIX_USER = "xxx:user:";
    private static String KEY_PREFIX_TICKET = "xxx:ticket:";

    private RedisCacheManager manager = RedisCacheManager.getInstance();

    private static final int secondsInDay = 60 * 60 * 24;
    private static final int secondsInWeek = secondsInDay * 7;

    private Logger logger = LoggerFactory.getLogger(XPassportManager.class);

    @Override
    public void clearTicketFromCookie(HttpServletResponse response) {
        CookieManager.getInstance().clearCookie(response, LOGINKEY_TICKET, secondsInWeek, "/");
        CookieManager.getInstance().clearCookie(response, HOST, secondsInWeek, "/");
    }

    @Override
    public void clearTicketFromCache(int uid) {
        logger.debug("====manager.delete={}=={}", KEY_PREFIX_TICKET, uid);
        logger.debug("====manager.delete={}=={}", KEY_PREFIX_USER, uid);
        manager.remove(KEY_PREFIX_TICKET + uid);
        manager.remove(KEY_PREFIX_USER + uid);
    }

    @Override
    public Integer validateTicket(HttpServletRequest request, HttpServletResponse response) {
        String hostId = CookieManager.getInstance().getCookie(request, HOST);
        logger.debug("hostId {}", hostId);
        if (StringUtils.isBlank(hostId))
            return null;
        String ticketFromCookie = getTicketFromCookie(request);
        logger.debug("ticketFromCookie {}", ticketFromCookie);
        if (StringUtils.isBlank(ticketFromCookie))
            return null;

        String ticketFromCache = getTicketFromCache(hostId);
        logger.debug("ticketFromCache {}", ticketFromCache);
        if (ticketFromCookie.equals(ticketFromCache)) {
            logger.debug("ticketFromCookie eq ticketFromCache, {}", hostId);
            return Integer.parseInt(hostId);
        }
        return null;
    }

    @Override
    public int validateTicket(int hostId, String ticketFromCookie) {
        logger.info("tick|{}|{}", hostId, ticketFromCookie);
        if (hostId <= 0)
            return 0;
        if (StringUtils.isBlank(ticketFromCookie))
            return 0;

        String ticketFromCache = getTicketFromCache(String.valueOf(hostId));
        logger.info("valid|{}|{}|{}", new Object[] {hostId, ticketFromCookie, ticketFromCache});
        if (ticketFromCookie.equals(ticketFromCache))
            return hostId;
        return 0;
    }

    private String getTicketFromCookie(HttpServletRequest request) {
        return CookieManager.getInstance().getCookie(request, LOGINKEY_TICKET);
    }

    private String getTicketFromCache(String hostId) {
        return (String) manager.get("xiaozhao:hr2:ticket:" + hostId);
    }

    @Override
    public void saveTicketInCache(int uid, String ticket) {
        manager.set(KEY_PREFIX_TICKET + uid, ticket, secondsInWeek);
    }

    @Override
    public void saveHrInCache(int uid, Hr hr) {
        //for test toRecover
        manager.set(KEY_PREFIX_USER + uid, hr);
    }

    @Override
    public Hr getHrInCache(int uid) {
        logger.debug("====getHrInCache==={}", KEY_PREFIX_USER + uid);
        logger.debug("====getHrInCache==={}", KEY_PREFIX_USER + String.valueOf(uid));
        return (Hr) manager.get(KEY_PREFIX_USER + String.valueOf(uid));
    }

    @Override
    public void saveTicketInCookie(HttpServletResponse response, String ticket, int remember) {
        if (remember == 1) {
            CookieManager.getInstance().saveCookie(response, LOGINKEY_TICKET, ticket, secondsInWeek, "/");
        } else {
            CookieManager.getInstance().saveCookie(response, LOGINKEY_TICKET, ticket, "/");
        }

    }

    @Override
    public void saveHostInCookie(HttpServletResponse response, int xiaozhaoUid, int remember) {
        if (remember == 1) {
            CookieManager.getInstance().saveCookie(response, HOST, String.valueOf(xiaozhaoUid), secondsInWeek, "/");
        } else {
            CookieManager.getInstance().saveCookie(response, HOST, String.valueOf(xiaozhaoUid), "/");
        }
    }

}


