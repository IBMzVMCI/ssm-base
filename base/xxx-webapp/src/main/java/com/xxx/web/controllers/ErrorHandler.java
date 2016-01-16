package com.xxx.web.controllers;

import net.paoding.rose.web.ControllerErrorHandler;
import net.paoding.rose.web.Invocation;

/**
 * 网站全部异常处理
 */
public class ErrorHandler implements ControllerErrorHandler {

//    private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    public Object onError(Invocation inv, Throwable ex) throws Throwable {

//        //Portal类型的异常，为了避免页面出错，直接返回空串
//        if (ex instanceof PortalException) {
//            logger.error(ex.getMessage(), ex);
//            return "";
//        }

        //业务类型的异常采用一般是通过json请求生成的，所以返回json行的的错误message
//        if (ex instanceof ServiceException) {
//            return JsonResponse.badResult(ex.getMessage());
//        }
//        //其他直接报错500
//        logger.error(ex.getMessage(), ex);
        return "";
    }
}