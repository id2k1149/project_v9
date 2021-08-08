<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>welcome</title>
    <link href="${contextPath}/resources/static/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">

    <img src="${contextPath}/resources/static/images/undraw_investing_7u74.svg%}"
         alt="Image"
         class="img-fluid img-absolute">

    <div class="row mb-4" data-aos="fade-up" data-aos-delay="200">
        <div class="col-lg-6 mr-auto">
            <h1>Survey for your company</h1>
            <p class="mb-5">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
            <div>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <a href="${contextPath}/questions" class="btn btn-primary mr-2 mb-2">
                        Vote
                    </a>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <a href="${contextPath}/login" class="btn btn-primary mr-2 mb-2">
                        Sign in
                    </a>
                </c:if>
            </div>
        </div>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/static/js/bootstrap.min.js"></script>
</body>
</html>
