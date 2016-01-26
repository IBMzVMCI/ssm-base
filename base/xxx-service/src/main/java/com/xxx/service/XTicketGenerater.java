package com.xxx.service;

import com.xxx.bean.Passport;
import com.xxx.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
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
