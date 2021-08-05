<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<jsp:include page="fragments/head.jsp"/>
<head>
    <title>Create Question</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<%--page content--%>
<div class="container text-center">
    <div><h1>Questions</h1></div>
    <div>
        <h3><a href="/question_form">Create new Question</a></h3>
    </div>
    <form id="question_form"
          method="POST"
          action="${contextPath}/question_form"
          object=""
    >



        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    </div>
</div>
<%--page content--%>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
