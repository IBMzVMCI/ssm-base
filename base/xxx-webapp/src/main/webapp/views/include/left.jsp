<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="leftNav">
    <ul class="mainNav">
        <c:forEach items="${moduleSet}" var="right">
            <c:if test="${right eq 1}">
                <li <c:if test="${rightId == 1}">class="mouseOn"</c:if>><a href="/career">职位发布</a></li>
            </c:if>
            <c:if test="${right eq 2}">
                <li <c:if test="${rightId == 2}">class="mouseOn"</c:if>><a href="/resume">简历筛选</a></li>
            </c:if>
            <!-- <c:if test="${right eq 8}">
            <li <c:if test="${rightId == 8}">class="mouseOn"</c:if>><a href="/hrField">场次管理</a></li>
        </c:if> -->
            <c:if test="${right eq 4}">
                <li <c:if test="${rightId == 4}">class="mouseOn"</c:if>><a href="/scorer">评分规则</a></li>
            </c:if>
            <c:if test="${right eq 5}">
                <li <c:if test="${rightId == 5}">class="mouseOn"</c:if>><a href="/label">标签管理</a></li>
            </c:if>
            <c:if test="${right eq 6}">
                <li <c:if test="${rightId == 6}">class="mouseOn"</c:if>><a href="/depart">部门维护</a></li>
            </c:if>
            <c:if test="${right eq 7}">
                <li <c:if test="${rightId == 7}">class="mouseOn"</c:if>><a href="/purview">人员权限</a></li>
            </c:if>
        </c:forEach>
    </ul>
</div>
