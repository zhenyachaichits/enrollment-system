<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.error.common.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.error.common.header" var="headMessage"/>
<fmt:message bundle="${loc}" key="locale.page.error.common.message" var="message"/>
<fmt:message bundle="${loc}" key="locale.page.error.common.back" var="back"/>

<html>

<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <%@include file="../included/css_list.jsp" %>

</head>
<body>
<br/>

<div class="container">
    <div class="jumbotron">
        <h1>${headMessage}</h1>

        <p>${message}</p>

        <form action="home" method="post">
            <input type="hidden" name="command" value="go-back">
            <p>
                <button type="submit" class="btn btn-primary btn-lg">${back}</button>
            </p>
        </form>
    </div>
</div>


<%@include file="../included/js_list.jsp" %>
<script src="content/js/locale.js"></script>

</body>
</html>