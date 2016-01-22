<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/1/16
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>XXX管理系统</title>

    <link href="../include/styles/ncommon.css" rel="stylesheet" type="text/css"/>
    <link href="../include/styles/hbootstrap.css" rel="stylesheet" type="text/css"/>
<body>
<c:set var="_curModule" value="6"/>
<%@include file="/views/include/header.jsp" %>
<div class="hr-mainwrap">
    <div class="hr-mainwrap">
        <%--<div class="alert">--%>
        <%--<!--  <button type="button" class="close" data-dismiss="alert">×</button> -->--%>
        <%--&lt;%&ndash;<p>公告：啥空间商的哈空间商的阿卡还是得空间,<a href="x">点击查看详情</a></p>&ndash;%&gt;--%>
        <%--</div>--%>
        <div class="hr-d-main">
            ${user_info}
            ${job_info}
            ${resume_info}
        </div>
        <div class="hr-d-sidebar">
            <div class="hr-d-box hr-d-btns">
                <h3 class="hr-d-title"><span>招聘快速操作</span></h3>
                <a type="button" class="btn btn-large btn-success" href="/job/add">新增职位</a>
                <a type="button" class="btn btn-large" href="/config/department">组织结构管理</a>
                <a type="button" class="btn btn-large" href="/config/delivery">申请截止设置</a>
                <a type="button" class="btn btn-large" href="/resume">简历概况</a>
                <a type="button" class="btn btn-large" href="/resume/prescreening/status/1">简历筛选</a>
                <a type="button" class="btn btn-large" href="/config/template/resume">简历模板预览</a>
                <a type="button" class="btn btn-large" href="/config/space">场地管理</a>
                <a type="button" class="btn btn-large" href="/config/tag">简历标签</a>
                <a type="button" class="btn btn-large" href="/config/user">用户管理</a>
            </div>
            <%--<div class="hr-d-box hr-d-tel">--%>
            <%--<h3 class="hr-d-title"><span>售后支持热线</span></h3>--%>

            <%--<p>84481818-3388</p>--%>

            <%--<p><a href="mailto:career@xxx-inc.com">career@xxx-inc.com</a></p>--%>
            <%--</div>--%>
        </div>
    </div>
    <div class="hr-footer"></div>
    <script type="text/javascript" src="../include/scripts/hrbase.js"></script>
</div>
</body>
</html>

