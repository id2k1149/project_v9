<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<jsp:include page="fragments/head.jsp"/>
<head>
    <title>Questions for today</title>
</head>
<body>
<script src="resources/js/project_v9.common.js" defer></script>
<script src="resources/js/project_v9.users.js" defer></script>
<jsp:include page="fragments/header.jsp"/>
<%--page content--%>
<div class="container text-center">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>
    <div>
        <h1>Today poll</h1>
    </div>

    <h1> ${question.id} </h1>
    <h1> ${question.questionTitle} </h1>


<c:if test="${error_message != null}">

    <p><strong>${error_message}</strong></p>

</c:if>


</div>
<%--page content--%>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
