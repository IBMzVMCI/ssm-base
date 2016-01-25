package com.xxx.commons;

import com.xxx.bean.Hr;

public interface ActiveHolder {
    /**
     * 返回当前访问者，如果没有登录的话返回null
     *
     * @return
     */
    Hr getUser();

    /**
     *
     * @param user
     */
    void setUser(Hr user);
}
