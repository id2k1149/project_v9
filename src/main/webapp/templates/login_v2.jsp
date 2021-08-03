<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Sign in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    <style>
        body {
            background-color: #3e3e3e;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="${contextPath}/login">
        <h2 class="form-signin-heading">Please sign in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username"
                   type="text"
                   class="form-control"
                   placeholder="Username"
                   autofocus="true"/>
            <br>
            <input name="password"
                   type="password"
                   class="form-control"
                   placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <div class="checkbox" mb-3>
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block"
                    type="submit">Sign in</button>
            <div class="text-center mt-5">
                <a  href="${contextPath}/registration" >Or create an account</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>