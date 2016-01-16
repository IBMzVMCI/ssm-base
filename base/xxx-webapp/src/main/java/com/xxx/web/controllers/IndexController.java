package com.xxx.web.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jinchao.xu
 * 2015/5/26.
 */
@Path("")
public class IndexController {

    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Get("")
    public String index(Invocation invocation){
        LOGGER.info("#IndexController index...");
        return "index";
    }
}
