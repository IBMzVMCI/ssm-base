package com.xxx.service;

import com.xxx.bean.Passport;

import javax.servlet.http.HttpServletRequest;

public interface TicketGenerater {

    String generate(int hostId, Passport passport, HttpServletRequest request);
}