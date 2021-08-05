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
        <h1>Questions</h1>
    </div>
    <div>
        <h3><a href="${contextPath}/new">Create new Question</a></h3>
    </div>
<%--    <h3 class="text-center"><spring:message code="question.questionTitle"/></h3>--%>
<%--    <button class="btn btn-primary" onclick="add()">--%>
<%--        <span class="fa fa-plus"></span>--%>
<%--        <spring:message code="common.add"/>--%>
<%--    </button>--%>
    <div>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Question</th>
                <th>Answers</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${questionsList}" var="question">
                <jsp:useBean id="question" type="org.id2k1149.project_v9.model.Question"/>
                <tr id="${question.id}">
                    <td><c:out value="${question.id}"/></td>
                    <td>${question.datePublished}</td>
                    <td>${question.questionTitle}</td>
                    <td>${question.answers}</td>
                    <td><a><span class="fa fa-pencil"></span></a></td>
                    <td><a class="delete"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
<%--page content--%>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
