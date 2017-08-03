<%@ include file="header.jsp" %>

<div class="container">

    <c:if test="${not empty auth_error}">
        <div class="alert alert-danger" role="alert" style="display:inline-block; float:right; margin-top: 45px;" >${auth_error}</div>
    </c:if>

</div>
<!-- /.container -->
<%@ include file="footer.jsp" %>

