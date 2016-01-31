package com.xxx.web.controllers;

import com.xxx.bean.Hr;
import com.xxx.bean.Module;
import com.xxx.passport.annotation.LoginRequired;
import com.xxx.service.ActiveHolder;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.portal.Portal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jinchao.xu
 * 2015/5/26.
 */
@Path("")
public class IndexController {

    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ActiveHolder userHolder;

    @Get("")
    @LoginRequired(privilege = Module.IGNORE)
    public String index(Portal portal) {
        LOGGER.info("#IndexController index...");
        portal.addWindow("user_info", "/user_info");

        return "index";
    }

    @Get("user_info")
    @LoginRequired(privilege = Module.IGNORE)
    public String companyInfoPortal(Invocation inv) {
        Hr hr = userHolder.getUser();


//        inv.addModel("resumeCount", resumeCount);
        return "/views/portal/index/index_user";
    }
}
