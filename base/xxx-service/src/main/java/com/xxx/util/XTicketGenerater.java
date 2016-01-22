package com.xxx.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class XTicketGenerater implements TicketGenerater {
    @Override
    public String generate(int hostId, Passport passport, HttpServletRequest request) {
        if (hostId <= 0)
            throw new IllegalArgumentException("hostId无效");
        if (passport == null || StringUtils.isBlank(passport.getPassword()))
            throw new IllegalArgumentException("passport不能为空");
//        String userAgent = request.getHeader("User-Agent");
//        String userIp = request.getRemoteAddr();  //去掉该项，允许一个账号多电脑同时登陆
//        return MD5.digest(MD5.digest(new StringBuilder().append(hostId).append(passport.getPassword()).append(userAgent).append(userIp).toString()) + passport.getSalt());
        return MD5.digest(MD5.digest(new StringBuilder().append(hostId).append(passport.getPassword()).toString()) + passport.getSalt());
    }
}
