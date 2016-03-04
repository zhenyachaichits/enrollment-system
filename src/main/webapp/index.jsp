<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.index.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.index.header" var="divHeader"/>
<fmt:message bundle="${loc}" key="locale.page.index.description" var="description"/>
<fmt:message bundle="${loc}" key="locale.page.index.button.statistics" var="statisticsBtn"/>

<fmt:message bundle="${loc}" key="locale.modal.signin.message.error" var="errorMessage"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <%@include file="WEB-INF/jsp/included/css_list.jsp" %>

</head>
<body>

<jsp:include page="WEB-INF/jsp/included/navbar.jsp"/>

<div class="container">
    <div class="jumbotron">
        <h1>${divHeader}</h1>
        <br/>
        <p>${description}</p>

        <p><a href="javascript:toStatistics.submit();" class="btn btn-primary btn-lg">${statisticsBtn}</a></p>

    </div>
    <span id="errorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${errorMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274095"></span>
</div>

<%@include file="WEB-INF/jsp/included/js_list.jsp" %>

</body>
</html>