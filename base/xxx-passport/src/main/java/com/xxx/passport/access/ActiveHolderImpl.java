package com.xxx.passport.access;

import com.xxx.bean.Hr;
import com.xxx.service.ActiveHolder;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.InvocationLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 
 */
@Component
public class ActiveHolderImpl implements ActiveHolder {

    @Autowired
    InvocationLocal inv;

    private static final String _host="_curUser" ;

    public Hr getUser() {
        Invocation inv = this.inv.getCurrent(false);
        if (inv != null) {
            return (Hr) inv.getRequest().getAttribute(_host);
        } else {
            return null;
        }
    }

    public void setUser(Hr user) {
        inv.getRequest().setAttribute(_host, user);
    }

}
