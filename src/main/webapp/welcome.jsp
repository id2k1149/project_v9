<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Welcome</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${contextPath}/resources/static/assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${contextPath}/resources/static/css/styles.css" rel="stylesheet" />
        <style>
            body {
                background-color: #3e3e3e;
                color: white;
            }
        </style>
    </head>
    <body>
        <!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Company polls</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                        class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="${contextPath}/welcome">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="${contextPath}/vote">Vote</a></li>
                        <li class="nav-item"><a class="nav-link" href="${contextPath}/result">Results</a></li>
                        <li class="nav-item">
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                                <a class="nav-link" onclick="document.forms['logoutForm'].submit()">${pageContext.request.userPrincipal.name}|Logout</a>
                            </c:if>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page content-->
        <div class="container">
            <br>
            <br>

            <div class="row mb-4" data-aos="fade-up" data-aos-delay="200">
                <div class="col-lg-6 mr-auto">
                    <h1>Survey for your company</h1>
                    <p class="mb-5">Lorem ipsum dolor sit amet consectetur adipisicing elit,
                        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
                        dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                        sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                    <div>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <a href="${contextPath}/vote" class="btn btn-primary mr-2 mb-2">
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
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${contextPath}/resources/static/js/scripts.js"></script>
    </body>
</html>
