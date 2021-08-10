<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<jsp:include page="../fragments/head.jsp"/>
<head>
    <title>Create Question</title>
</head>
<body>
<jsp:include page="../fragments/header.jsp"/>
<%--page content--%>
<div class="container text-center">
    <div><h1>Questions</h1></div>
    <hr>

    <h2>${param.action == 'create' ? 'Create question' : 'Edit question'}</h2>

<%--    <jsp:useBean id="question" type="org.id2k1149.project_v9.model.Question" scope="request"/>--%>

    <form method="post" action="save" style="max-width: 600px; margin: 0 auto">
        <input type="hidden" name="id" value="${question.id}">
        <div class="m-3">
            <div class="form-group row">
                <label class="col-form-label col-sm-4">Question</label>
                <div class="col-sm-8">
                    <input type="text" value="${question.questionTitle}" size=40 name="questionTitle" required>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-form-label col-sm-4">Answers:</label>
                <div class="col-sm-8 text-left">

                    <c:forEach items="${answersList}" var="diner" >
                        <div>
                            <input type="checkbox"
                                   value="${diner.id}"
                                   id="diner"
                                   name="diners"
                            />
                            <label for="diner">${diner.dinerName}</label>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>


        <button type="submit" class="btn btn-primary">Save</button>
        <button onclick="window.history.back()" type="button" class="btn btn-primary">Cancel</button>

    </form>

    </div>
</div>
<%--page content--%>
<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>
