package com.xxx.util;

import javax.servlet.http.HttpServletRequest;

public interface TicketGenerater {

    public String generate(int hostId, Passport passport, HttpServletRequest request);
}