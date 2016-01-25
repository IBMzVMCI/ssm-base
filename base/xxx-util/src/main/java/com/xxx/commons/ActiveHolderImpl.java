package com.xxx.commons;

import com.xxx.bean.Hr;
import com.xxx.commons.util.BaseThreadUtil;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.InvocationLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 
 */
@Component("activeHolder")
public class ActiveHolderImpl implements ActiveHolder {

    @Autowired
    InvocationLocal inv;

    @Override
    public Hr getUser() {
        Invocation inv = this.inv.getCurrent(false);
        if (inv != null) {
            return BaseThreadUtil.currentHost(inv.getRequest());
        } else {
            return null;
        }
    }

    public void setUser(Hr user) {
        BaseThreadUtil.setHost(inv.getRequest(), user);
    }

}
