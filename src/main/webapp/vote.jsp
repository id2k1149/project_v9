<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<jsp:include page="fragments/head.jsp"/>
<head>
    <title>Vote</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<!-- Page content-->
<div class="container">
    <div class="container text-center">
        <div>
            <h1> Where to have a lunch? </h1>
        </div>

        <c:if test="${error1 != null}">
            <p class="alert-danger"><strong>${error1}</strong></p>
        </c:if>
        <c:if test="${error2 != null}">
            <p class="alert-danger"><strong>${error2}</strong></p>
        </c:if>

        <form action="vote" method="post">

            <c:forEach items="${answersList}" var="answer">

                <input type="radio"
                       id="answer"
                       name="answer"
                       value="${answer.id}"
                />
                <label for="answer">${answer.title}</label>

                <br>
                ${answer.infoTo}
                <%--            <c:forEach items="${answer.title}" var="entry">--%>
                <%--                ${entry.key} ${entry.value}--%>
                <%--                <br>--%>
                <%--            </c:forEach>--%>

                <br>
                <br>

            </c:forEach>
            <br>
            <br>
            <c:if test="${error2 == null}">
                <input type="submit" class="btn btn-primary" value="Vote"/>
            </c:if>
            <c:if test="${error2 != null}">
                <a href="${contextPath}/result" class="btn btn-primary mr-2 mb-2">
                    Result
                </a>
            </c:if>

        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
