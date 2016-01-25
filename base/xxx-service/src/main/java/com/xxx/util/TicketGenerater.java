package com.xxx.util;

import javax.servlet.http.HttpServletRequest;

public interface TicketGenerater {

    String generate(int hostId, Passport passport, HttpServletRequest request);
}