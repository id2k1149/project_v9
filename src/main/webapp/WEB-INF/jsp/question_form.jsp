<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<jsp:include page="fragments/head.jsp"/>
<head>
    <title>Question Form</title>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>

    <div class="container text-center">

        <h1>Create a new Poll</h1>

    </div>

    <jsp:include page="fragments/footer.jsp"/>

</body>
</html>
