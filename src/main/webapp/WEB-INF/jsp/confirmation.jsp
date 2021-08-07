<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<jsp:include page="fragments/head.jsp"/>
<head>
    <title>Confirmation</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<%--page content--%>
<div class="container text-center">
    <div><h1>You have poll for today already </h1></div>
    <hr>

        <button onclick="location.href='${contextPath}/new'" type="button" class="btn btn-primary">Add</button>
        <button onclick="window.history.back()" type="button" class="btn btn-primary">Cancel</button>

    </div>
</div>
<%--page content--%>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
