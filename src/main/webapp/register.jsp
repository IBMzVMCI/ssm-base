<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/10/24
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<div class="register-info">
    <h3><span class="icon"></span></h3>

    <div class="bind-mobile clearfix">
        <form id="form" class="clearfix" method="post" action="/account/register?action=reg">
            <ul class="info">
                <li><span class="err" style="font-size:14px;color:red;"></span></li>
                <li><span class="tit">用户名：</span><span class="input">
	                  <input type="text" name="userName" placeholder="请输入用户名" value=""></span></li>
                <li class="vcode" style="display:none"><span class="tit">密码：</span><span class="input">
                    <input type="password" name="password" placeholder="请输入密码" value=""/> </span></li>
                <li>
                    <span class="tit"></span>
                	<span class="provision" style="font-size:10px;">
                    	<input id="agree" class="agree" name="agree" type="checkbox" checked="checked"
                               style="width:20px;height:12px"/>我已仔细阅读并接受<a href="/html/registration-policy.html"
                                                                           target="_blank">CSDN注册条款</a>
                    </span>
                </li>
            </ul>
        </form>
    </div>
    <div id="tooltip" class="tooltip-info"><span class="icon-border"></span><span class="icon-bg"></span><span
            class="strength"><em class="level">低</em><span></span><span></span><span></span></span><span
            class="state"></span><span class="mess"></span></div>
</div>

</body>
</html>
