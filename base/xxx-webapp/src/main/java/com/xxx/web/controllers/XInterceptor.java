package com.xxx.web.controllers;

import com.xxx.bean.Hr;
import com.xxx.passport.commons.CookieManager;
import com.xxx.service.ActiveHolder;
import com.xxx.service.UserService;
import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Interceptor;
//import org.perf4j.StopWatch;
//import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 */
@Interceptor(oncePerRequest = true)
public class XInterceptor extends ControllerInterceptorAdapter {

    protected static final int APPID = 193060;

    public static final String UNIQ_COOKIE_NAME = "anonymid";

    public static final String USREAGENT = "User-Agent";

    public static final String REFERER = "Referer";

    private static final String START_TIME = "PERF_START";

    private static final String COOKIE_PASSPORT = "p";

    private static final String COOKIE_PROFILE_GUIDE = "profile_guide";

    private static final String PASSPORT_DOMAIN = "rr.com";

    private static final String URL_PARAM_SOURCEFROM = "sourcefrom";

    private static final String URL_PARAM_GUIDE_OUT = "guideOut";

    private Logger logger = LoggerFactory.getLogger(XInterceptor.class);

    private Logger access = LoggerFactory.getLogger("access");

    @Autowired
    private ActiveHolder hostHolder;

    @Autowired
    UserService userService;

//    protected StopWatch stopWatch = new Slf4JStopWatch(logger);

    @Override
    public int getPriority() {
        return 10000;
    }

    @Override
    protected Object before(Invocation inv) throws Exception {
//        stopWatch.start();
        String uuid = (String) inv.getRequest().getAttribute(UNIQ_COOKIE_NAME);
        try {
            boolean exist = true; // 用户未登录的情况下默认存在此用户，不进行跳转简历引导的操作
            Hr loginUser = hostHolder.getUser();
            int xzUid = 0;
            inv.getRequest().setAttribute(START_TIME, System.currentTimeMillis());
            if (loginUser != null) {
                exist = userService.isExistUser(loginUser.getUid());
                if (!exist) {
//                    RenRenUserInfo info = new RenRenUserInfo();
//                    info.setUserid(loginUser.getId());
//                    info.setName(loginUser.getUserNameInfo().getName());
//                    info.setGender(loginUser.getGender());
//                    info.setHeadurl(loginUser.getHeadFullUrl());
//                    int newUid = renRenUserService.trandferRenRenInfo2XZ(info);
//                    inv.addModel("firstComing", true);
//                    UserBasic userBasic = new UserBasic();
//                    userBasic.setId(newUid);
//                    userBasic.setName(info.getName());
//                    inv.addModel("currentOwnerUser", userBasic);
//                    inv.addModel("is_login", true);
//                    xzUid = newUid;
                } else {
//                    xzUid = userBindingDAO.getUidByRenRenUid(loginUser.getId());
//                    UserBasic basic = userInfoDAO.getUserBasic(xzUid);
//                    inv.addModel("currentOwnerUser", basic);
//                    inv.addModel("is_login", true);
                }
                if(!hasPticket(inv)){
                    savePassport2Cookie(inv.getResponse());
                }
                try {
                    //                    /**
                    //                     *添加到左侧菜单(这里的是老版的，已经不用了)
                    //                     */
                    //                    RecentMenuLogicAdapter.getInstance().track(loginUser.getId(), APPID);
                    
                } catch (Exception e) {
                    logger.error("左侧菜单导航修改 error " + e.getMessage(), e);
                }
            }
            if ("GET".equalsIgnoreCase(inv.getRequest().getMethod())) {
                StringBuilder builder = new StringBuilder();
                builder.append(uuid);
                builder.append("|");
                builder.append(inv.getRequest().getRequestURL());
                String queryString = inv.getRequest().getQueryString();
                if (queryString != null) {
                    builder.append("?").append(queryString);
                }
                builder.append("|");
                builder.append(inv.getRequest().getRemoteAddr());
                builder.append("|");
                builder.append(inv.getRequest().getHeader(USREAGENT));
                builder.append("|");
                if (loginUser != null) {
                    builder.append(loginUser.getUid());
                } else {
                    builder.append(0);
                }
                builder.append("|");
                builder.append(inv.getRequest().getHeader(REFERER));
                builder.append("|");
                builder.append(inv.getRequest().getMethod());
                access.debug(builder.toString());


                /*
                 * 简历引导页面检查
                 * 只过滤不带_rtk变量的get方法
                 */
                String uriString = inv.getRequest().getRequestURI();
                if (uriString == null ) {
                    uriString = "";
                }
                // 当访问页面不是简历引导页面时开始检查
                if (xzUid > 0 && !uriString.startsWith("/guide/resume") && inv.getParameter("_rtk") == null) {
                    queryString = inv.getRequest().getQueryString();
                    if (queryString == null) {
                        queryString = "";
                    } else {
                        queryString = "?" + queryString;
                    }
                    String sourceFrom = inv.getParameter(URL_PARAM_SOURCEFROM);
                    String localUrl = URLEncoder.encode(uriString + queryString, "UTF-8");
                    if (inv.getParameter(URL_PARAM_GUIDE_OUT) != null
                            || CookieManager.getInstance().getCookie(inv.getRequest(), COOKIE_PROFILE_GUIDE) != null){ //guideOut, add coockie and do not redirect in 7 days
                        CookieManager.getInstance().saveCookie(inv.getResponse(), COOKIE_PROFILE_GUIDE, String.valueOf(xzUid),
                                60 * 60 * 24 * 7, "/");
                    } else if (!uriString.startsWith("/act/employer")      // 雇主调查
//                            && !uriString.startsWith("/go")
//                            && !uriString.startsWith("/lecture")           // 职场必修课
                            ){  // doesn't has coockie and profile hasn't been completed
                        if (sourceFrom == null && exist){
                            inv.addModel("profile_guide_layer", true);
                        }
//                        else if ((sourceFrom == null && !exist) || profileGuideToPage(sourceFrom)){
//                            return "r:/guide/resume?step=1&url=" + localUrl;
//                        } else if (profileGuideToLayer(sourceFrom)) {
//                            inv.addModel("profile_guide_layer", true);
//                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
//            stopWatch.stop("start:" + inv.getResourceId());
        }
        return Boolean.TRUE;
    }

    protected Object after(Invocation inv, Object instruction) throws Exception {
        //        try {
        //            long startTime = (Long) inv.getRequest().getAttribute(START_TIME);
        //            StringBuilder builder = new StringBuilder();
        //            builder.append(inv.getRequest().getRequestURL());
        //            String queryString = inv.getRequest().getQueryString();
        //            if (queryString != null) {
        //                builder.append("?").append(queryString);
        //            }
        //            builder.append("|");
        //            builder.append(inv.getRequest().getRemoteAddr());
        //            builder.append("|");
        //            builder.append(inv.getRequest().getHeader(USREAGENT));
        //            builder.append("|");
        //            builder.append(inv.getRequest().getHeader(REFERER));
        //            builder.append("|");
        //            builder.append(inv.getRequest().getMethod());
        //            builder.append("|");
        //            builder.append((System.currentTimeMillis() - startTime) / 1000);
        //            timeStatistics.debug(builder.toString());
        //        } catch (Exception e) {
        //            logger.error(e.getMessage(), e);
        //        } finally {
//        stopWatch.stop("start:" + inv.getResourceId());
        //        }
        return null;
    }

    /*private boolean profileGuideToPage(String from){
        if (from == null || from.equals("")){
            return true;
        }
        return profileGuideService.hasSourceFrom(from, ProfileGuideSourcefromEnum.GUIDE_PAGE);
    }


    private boolean profileGuideToLayer(String from){
        if (from == null || from.equals("")){
            return false;
        }
        return profileGuideService.hasSourceFrom(from, ProfileGuideSourcefromEnum.GUIDE_LAYER);
    }*/


    /**
     * 是否有P票
     * @param inv
     * @return
     */
    private boolean hasPticket(Invocation inv){
        return CookieManager.getInstance().getCookie(inv.getRequest(),COOKIE_PASSPORT) != null;
    }

    private void savePassport2Cookie(final HttpServletResponse response) {
        CookieManager.getInstance().saveCookie(response, COOKIE_PASSPORT,
                "xiaozhao", -1, "/");
    }
}
