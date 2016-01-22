<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:choose>
    <c:when test="${!expired}">
        <div class="hr-fix-header">
            <div class="hr-fix-header-wrap">
                <a class="hr-fix-header-logo"></a>

                <p class="hr-fix-header-txt">
                    <span class="title">${_xzCurUser.companyName}-${_xzCurUser.uname}</span>
                    <a href="/logout">退出</a>
                    <em class="ico-gang">|</em>
                    <a href="/">帮助</a>
                </p>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <style type="text/css">
            .hr-fix-wrap {
                height: 40px;
            }

            .hr-fix-wrap .hr-fix-tip {
                position: fixed;
                top: 0px;
                left: 0px;
                background-color: #000000;
                opacity: 0.7;
                font-size: 14px;
                font-weight: bold;
                text-align: center;
                color: #FFFFFF;
                width: 100%;
                height: 40px;
                line-height: 40px;
                box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.6);
                z-index: 10001;
            }
        </style>
        <div class="hr-fix-wrap">
            <div class="hr-fix-tip">您的产品已经到期，将限制使用功能；如需正常使用，欢迎进行续订。</div>
        </div>
        <div class="hr-fix-header">
            <div class="hr-fix-header-wrap">
                <a class="hr-fix-header-logo"></a>
                <p class="hr-fix-header-txt">
                    <span class="title">${_xzCurUser.companyName}-${_xzCurUser.uname}</span>
                    <a href="/logout">退出</a>
                    <em class="ico-gang">|</em>
                    <a href="/">帮助</a>
                </p>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<div class="hr-header">
    <div class="hr-headerwrap">
        <!--<a class="hr-logo"></a>-->
        <ul class="hr-header-menu">
            <li <c:if test="${_curModule == 6}">class="active"</c:if>><a href="/">首页</a></li>
            <c:if test="${moduleMap[1]}">
                <li <c:if test="${_curModule == 1}">class="active"</c:if>><a href="/job">职位管理</a></li>
            </c:if>
            <c:if test="${moduleMap[8]}">
                <li <c:if test="${_curModule == 2}">class="active"</c:if>><a href="/resume">简历管理</a></li>
            </c:if>
            <c:if test="${moduleMap[24]}">
                <!-- <li style="display:none;" <c:if test="${_curModule == 3}">class="active"</c:if>><a href="/schedule">招聘日程管理</a></li> -->
            </c:if>
            <c:if test="${moduleMap[25]}">
                <li><a href="http://xiaozhao.renren.com/hr/blog/list">公共主页管理</a></li>
            </c:if>
            <c:if test="${moduleMap[28]}">
                <!-- <li <c:if test="${_curModule == 8}">class="active"</c:if>><a href="/bolt">精准推送管理</a></li> -->
            </c:if>
            <c:if test="${moduleMap[23]}">
                <!-- <li <c:if test="${_curModule == 4}">class="active"</c:if>><a href="#">数据统计</a></li> -->
            </c:if>
            <li <c:if test="${_curModule == 5}">class="active"</c:if>><a href="/config/profile">系统设置</a></li>
            <c:if test="${moduleMap[27]}">
                <!-- <li <c:if test="${_curModule == 7}">class="active"</c:if>><a href="/posterwall">海报墙管理</a></li> -->
            </c:if>
            <c:if test="${moduleMap[32]}">
                <li <c:if test="${_curModule == 9}">class="active"</c:if>><a href="/propagate">宣讲会管理</a></li>
            </c:if>
        </ul>
    </div>
</div>
<script type="text/javascript">
    var _rtk = '${requestScope._rtk}';
</script>
