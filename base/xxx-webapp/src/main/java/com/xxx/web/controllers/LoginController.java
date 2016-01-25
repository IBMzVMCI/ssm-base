package com.xxx.web.controllers;

import com.xxx.bean.Hr;
import com.xxx.bean.Module;
import com.xxx.commons.ActiveHolder;
import com.xxx.commons.access.ValidateRequestToken;
import com.xxx.commons.loginrequired.LoginRequired;
import com.xxx.commons.originurl.OrigURLInterceptor;
import com.xxx.commons.util.CookieManager;
import com.xxx.commons.util.PassportManager;
import com.xxx.service.HrPassportService;
import com.xxx.service.HrService;
import com.xxx.util.Passport;
import com.xxx.util.TicketGenerater;
import com.xxx.web.util.Commons;
import com.xxx.web.util.JsonResponse;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Path("")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    private Logger businessLogger = LoggerFactory.getLogger("business");

    @Autowired
    private PassportManager passportManager;

    @Autowired
    private TicketGenerater ticketGenerater;

    @Autowired
    private ActiveHolder activeHolder;

    @Autowired
    private HrPassportService hrPassportService;

    @Autowired
    private HrService hrService;

    /**
     * 登录页面
     * @param inv
     * @return
     * @throws UnsupportedEncodingException
     */
    @Get("login")
    public String login(Invocation inv) throws UnsupportedEncodingException {
        Integer xiaozhaoUid = passportManager.validateTicket(inv.getRequest(), inv.getResponse());
        if (xiaozhaoUid != null && xiaozhaoUid > 0) {
            //added by agx 登陆后直接访问页面，就直接跳转到访问页
            Object origURL = inv.getParameter(OrigURLInterceptor.REQ_ATTR_ORIG_URL);
            if (origURL != null) {
                String url = (String) origURL;
                url = URLDecoder.decode(url, "utf-8");
                return "r:" + url;
            }
            return "r:/";
        }

        Hr rrHost = activeHolder.getUser();
        if (rrHost != null) {
            inv.addModel("rruid", "&uid=" + rrHost.getUid());
        }
        return "login";
    }

    @Post("login")
    @ValidateRequestToken
    public String login(Invocation inv,
                        @Param("customer_id") int customerId,
                        @Param("uname") String username,
                        @Param("password") String password,
                        @Param("inputCode") String inputCode,
                        @Param("remember") int isRemember) {


        businessLogger.info("login|{}|{}|click", customerId, username);
        String type = "hr_login";

        if (customerId == 0 || StringUtils.isBlank(username)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(inputCode))
            return JsonResponse.badResult("登录信息不能为空");

        if (StringUtils.isBlank(username))
            return JsonResponse.badResult("登录信息不能为空");

        //密码
        if (StringUtils.isEmpty(password)) {
            return JsonResponse.badResult(Commons.EMPEY_PASSWORD);
        }

        //验证码
        if (StringUtils.isEmpty(inputCode)) {
            return JsonResponse.badResult(Commons.EMPEY_VALIDATE_IMAGE);
        }

        Hr user = activeHolder.getUser();

        String id;
        //如果有XX的t票， 则使用rr2uid去验证
        if (user != null) {
            id = String.valueOf(user.getUid());
        } else {
            //否则使用ick
            id = CookieManager.getInstance().getCookie(inv.getRequest(), "ick");
        }
//        CodeValidateInfo codeValidateInfo = CodeUtil.getInstance()
//                .validCodeFull(inputCode.toUpperCase(), type, id);
//        if (!codeValidateInfo.isValidate()) {
//            return JsonResponse.badResult(Commons.ERROR_VALIDATE_IMAGE);
//        }

        //密码验证
        try {
            username = username.toLowerCase();
            Passport passport = hrPassportService.findPassportByUname(customerId, username);
            if (passport == null) {
                return JsonResponse.badResult(Commons.BAND_ACCOUNT);
            } else {
                //采用md5(md5(password) + salt)
                String encryptPassword = hrPassportService.generatePassword(password, passport.getSalt());
                if (!passport.getPassword().equals(encryptPassword))
                    return JsonResponse.badResult(Commons.BAD_PASSWORD);
                Hr hr = hrService.findByUid(passport.getId());
                if (hr == null) {
                    return JsonResponse.badResult("用户初始化错误");
                }
                if (hr.getStatus() == Hr.STATUS_PAUSE)
                    return JsonResponse.badResult("账号已被暂停");

                if (hr.getStatus() == Hr.STATUS_DELETE)
                    return JsonResponse.badResult("账号不存在");

//                ProductOrder productOrder = hrProductService.getCurrentProductOrder(hr.getCustomerId());
//                if (productOrder == null) {
//                    return JsonResponse.badResult("客户不存在或已到达产品使用期限");
//                }
//                if (productOrder.getStatus() == ProductOrderStatus.FINISHED.val()) {
//                    return JsonResponse.badResult("账号已到期");
//                }
//                Date accountValidData = customConfigService.getValidDate(hr.getCustomerId());
//                if (accountValidData != null) {
//                    if (new LocalDate().isAfter(new LocalDate(accountValidData))) {
//                        return JsonResponse.badResult("账号已到期");
//                    }
//                }
//                Company company = customerManager.getCompanyByCustomer(hr.getCustomerId());
//                if (company == null)
//                    throw new ServiceException("系统错误，登陆失败");
//                hr.setCompanyId(company.getCompanyId());
//                hr.setCompanyName(company.getName());
//                hr.setUname(username);

                //设置t票，Hr加入缓存
                final String ticket = ticketGenerater.generate(passport.getId(), passport, inv.getRequest());
                businessLogger.info("passport.getId：{},user-agent：{}", passport.getId(), inv.getRequest().getHeader("User-Agent"));
                businessLogger.info("passport.getId：{},remoteAddr：{}", passport.getId(), inv.getRequest().getRemoteAddr());
                businessLogger.info("login|{}|{}|ticket", passport.getId(), ticket);
                //登录成功，设置
                passportManager.saveTicketInCache(passport.getId(), ticket);
                passportManager.saveHrInCache(passport.getId(), hr);

                passportManager.saveTicketInCookie(inv.getResponse(), ticket, isRemember);
                passportManager.saveHostInCookie(inv.getResponse(), passport.getId(), isRemember);
                businessLogger.info("login|{}|{}|success", passport.getId(), ticket);
                return JsonResponse.ok();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResponse.badResult(Commons.INTERNAL_ERROR);
        }
    }

    @Get("logout")
    @LoginRequired(privilege = Module.IGNORE)
    public String logout(Invocation inv, @Param("origURL") String origURL) {
        Hr hr = activeHolder.getUser();
        passportManager.clearTicketFromCache(hr.getUid());
        passportManager.clearTicketFromCookie(inv.getResponse());
        return "r:/login";
    }
}
