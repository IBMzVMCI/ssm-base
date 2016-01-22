<c:if test="${pageModel.totalPage > 1}">
    <span class="pageBox" style="padding-left: 10px">
        <span style="margin-right: 10px"><a href="#" value="1" class="pageItem">首页</a></span>
        <c:if test="${pageModel.previousPage > 0}">
            <a href="#" value="${pageModel.previousPage}"  class="pageItem radiusL">?</a>
        </c:if>
        <c:forEach begin="${pageModel.beginPage}" end="${pageModel.endPage}" step="1" var="p">
            <a href="#" value="${p}"
               class="pageItem <c:if test="${p == pageModel.currentPage}">pageOn</c:if>">${p}</a>
        </c:forEach>
        <c:if test="${pageModel.nextPage > 0}">
            <a href="#" class="pageItem radiusR"  value="${pageModel.nextPage}">?</a>
        </c:if>
        <span style="margin-left: 10px"><a  href="#" value="${pageModel.totalPage}" class="pageItem">末页</a></span>
        <span style="margin-left: 20px">
            到第<input style="width:20px" id="p" type="text" value="${pageModel.currentPage}">页
            <input id="page_button" type="button" value="Go">
        </span>
    </span>
</c:if>
