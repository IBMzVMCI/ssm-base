<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>hr</title>
    <link href="http://s.xnimg.cn/xiaozhao/styles/base.css?ver=$revxxx" rel="stylesheet" type="text/css"/>
    <link href="http://s.xnimg.cn/xiaozhao/styles/hr/hcommon.css?ver=$revxxx" rel="stylesheet" type="text/css"/>
    <link href="http://s.xnimg.cn/xiaozhao/styles/hr/hbootstrap.css?ver=$revxxx" rel="stylesheet" type="text/css"/>
</head>
<body>
<%@include file="views/include/header.jsp" %>
<div id="mainWrapper" class="clearfix">
    <div class="mainContent errorPage">

        <div class="errorChild500">
            <div class="errorInfo">
                <p class="con500">您目前没有权限访问该页面，请联系管理员</p>

                <a href="#" class="goBack">返回上一页</a>
            </div>
        </div>


    </div>
</div>
<script type="text/javascript" src="http://s.xnimg.cn/xiaozhao/scripts/base.js?ver=$revxxx"></script>
<script type="text/javascript" src="http://s.xnimg.cn/xiaozhao/scripts/hrn/hrbase.js?ver=$revxxx"></script>
<script type="text/javascript">
    $(function () {
        var $btnhref = $(".tabNav .btn");
        $btnhref.click(function () {
            var url = window.location.href.split("#")[0];
            var hrefName = $(this).attr("href");
            window.location.href = url + hrefName;
            return false
        });
    })

</script>

</body>
</html>
