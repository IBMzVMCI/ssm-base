<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="table_plus clearfix">
    <div class="btnarea">
        第 <span class="page-now">${pageResult.currentPage}</span>/<span
            class="page-total">${pageResult.totalPage}</span>页 <a href="#" class="j_page" data-page="1">首页</a> | <a
            href="#"
            class="page-prev j_page"
            <c:choose>
        		<c:when test="${pageResult.currentPage == 1}">
            		data-page="1">上页</a>
            	</c:when>
        		<c:when test="${pageResult.currentPage != 1}">
        			data-page="${pageResult.currentPage-1}">上页</a>
        		</c:when>
            </c:choose>
        | <a href="#" class="page-next j_page"
        	<c:choose>
        		<c:when test="${pageResult.currentPage eq pageResult.totalPage}">        
         			data-page="${pageResult.currentPage}" >下页</a>
         		</c:when>
        		<c:when test="${pageResult.currentPage ne pageResult.totalPage}">
        			data-page="${pageResult.currentPage+1}" >下页</a>
        		</c:when>
            </c:choose>
         
          | <a href="#" class="page-end j_page"
                                                                         data-page="${pageResult.endPage}">尾页</a>
        转到第<input type="text" class="input-mini" name="pagenum" maxlength="3">页
        <button type="button" class="btn btn-mini btn-success j_page_change">GO</button>
    </div>
    <div class="info">
        总共 <span class="num-total"><c:out value="${pageResult.totalCount}" default="0"/></span> 条记录
    </div>
</div>