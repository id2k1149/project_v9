<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>HOME</title>
    <link href="${contextPath}/resources/static/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
  <div class="container">
<%--    <c:if test="${pageContext.request.userPrincipal.name == null}">--%>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>INDEX  | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

        <div class="container text-center">
            <div class="p-2">
                <a class="h2" href="/login">Login</a>
            </div>
            <div class="p-2">
                <a class="h2" href="admin/questions/new">Create question</a>
            </div>

        </div>

<%--    </c:if>--%>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/static/js/bootstrap.min.js"></script>
</body>
</html>
