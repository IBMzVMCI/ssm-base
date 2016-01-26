package com.xxx.passport.access;

import com.xxx.bean.Hr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * passport 管理类
 **/
public interface PassportManager {

    public static String LOGINKEY_TICKET = "xxxt";
    public static String HOST = "hostid";

    public void clearTicketFromCookie(HttpServletResponse response);

    public void clearTicketFromCache(int uid);

    public Integer validateTicket(HttpServletRequest request, HttpServletResponse response);

    public int validateTicket(int hostId, String ticketFromCookie);

    void saveTicketInCache(int uid, String ticket);

    //user可以接口
    void saveHrInCache(int uid, Hr hr);

    void saveTicketInCookie(HttpServletResponse response, String ticket, int remember);

    void saveHostInCookie(HttpServletResponse response, int uid, int remember);

    Hr getHrInCache(int uid);
    
}
