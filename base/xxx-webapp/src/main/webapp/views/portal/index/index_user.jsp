<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="hr-d-box">
    <table class="hr-d-com">
        <tr>
            <td>
                <img src="${companyLogo}"
                     width=50 height=50 border=0>
            </td>
            <td>
                <p><a href="/config/profile">${_xzCurUser.name}</a></p>

                <p><fmt:formatDate value="${baseConf.validDate}" pattern="yyyy年M月d日"/>到期</p>

                <p><a href="/config/profile/pass">修改密码</a></p>
            </td>
            <td class="bd">
                <p class="hr-d-com-t"><b><a href="/job"><c:out value="${baseConf.jobCount- baseConf.jobLeft}"
                                                               default="0"/></a>/<c:out value="${baseConf.jobCount}"
                                                                                        default="0"/></b>个</p>

                <p>已发布/可发布职位</p>
            </td>
            <td class="bd">
                <p class="hr-d-com-t"><b><a href="/resume"><c:out value="${resumeCount}" default="0"/></a></b>份</p>

                <p>已收简历</p>
            </td>
            <td>
                <p class="hr-d-com-t"><b><a href="/schedule"><c:out value="${talkCount}" default="0"/></a></b>条</p>

                <p>已发布日程</p>
            </td>
        </tr>
    </table>
</div>