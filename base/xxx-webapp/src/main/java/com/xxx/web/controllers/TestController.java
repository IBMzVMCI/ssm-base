package com.xxx.web.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@Path("test")
public class TestController {

    @Get("")
    public String index(){
        return "@hello";
    }  
}  