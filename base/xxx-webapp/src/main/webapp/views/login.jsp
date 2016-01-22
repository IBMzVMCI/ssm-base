<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Test</title>
    <link rel="shortcut icon" type="image/x-icon" href="#"/>
    <meta name="keywords" content="TEst"/>
    <meta name="description" content="assdfsdTEST"/>
    <meta name="googlebot" content="all"/>
    <meta name="baiduspider" content="all"/>
    <meta name="copyright" content="本页版权 www.xxx.com 。All Rights Reserved"/>
    <meta name="msvalidate.01" content="EE42CB9994DE71F2B8A47FD68E64B9E6"/>
    <meta name="google-site-verification" content="8OZjZRjZXjvvV5S2jG3SqYbIYQWUGIp6wNQSD8WVldY"/>
    <meta name="baidu-site-verification" content="lbYa9RV1A9ZCXet0"/>
    <link href="../include/styles/ncommon.css" rel="stylesheet" type="text/css"/>
    <link href="../include/styles/hbootstrap.css" rel="stylesheet" type="text/css"/>
<body>
<div class="hr-login-header">
    <div class="hr-login-header-wrap">
        <a class="hr-w-logo"></a>
    </div>
</div>
<div class="hr-login-banner">

    <div class="hr-login-aside">
        <p class="title"></p>

        <form class=" form-horizontal">
            <div class="control-group">
                <label class="control-label " for="">企业ID：</label>

                <div class="controls">
                    <input type="text" class="input-medium" placeholder="" name="customer_id">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for=" ">用户名：</label>

                <div class="controls">
                    <input type="text" class="input-medium" placeholder="" name="uname">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for=" ">密码：</label>

                <div class="controls">
                    <input type="password" class="input-medium" placeholder="" name="password">
                </div>
            </div>
            <%--<div class="control-group">
                <label class="control-label" for=" ">验证码：</label>

                <div class="controls">
                    <input type="text" placeholder="" class="input-medium" name="inputCode">

                    <p class="code">
                        <img id="verifyPic_login"
                             src="http://icode.xxx.com/getcode.do?t=hr_login&rnd=0.08297105063684285${xxuid}"
                             width=90>
                        <a href="#">换一张</a>
                    </p>
                </div>

            </div>--%>

            <div class="control-group loginbtn">
                <div class="controls">
                    <button type="button" class="btn btn-success">登录</button>
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1">下次自动登录
                    </label>

                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for=" "></label>

                <div class="controls">
                    <p class="login-error">
                        ${errorMsg}
                    </p>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="hr-login-footer">
    <div class="hr-login-footer-wrap">XX公司旗下网站©2016</div>
</div>
<script type="text/javascript">
    var _rtk = '${requestScope._rtk}';
</script>
<script type="text/javascript" src="../include/scripts/hrbase.js"></script>
<script type="text/javascript" src="../include/scripts/login.js"></script>

</body>
</html>
