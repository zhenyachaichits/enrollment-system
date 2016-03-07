
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.statistics.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.statistics.header" var="headerLabel"/>
<fmt:message bundle="${loc}" key="locale.page.statistics.table.name" var="nameLabel"/>
<fmt:message bundle="${loc}" key="locale.page.statistics.table.quota.free" var="freeQuotaLabel"/>
<fmt:message bundle="${loc}" key="locale.page.statistics.table.quota.paid" var="paidQuotaLabel"/>
<fmt:message bundle="${loc}" key="locale.page.statistics.table.points.free" var="freePointsLabel"/>
<fmt:message bundle="${loc}" key="locale.page.statistics.table.points.paid" var="paidPointsLabel"/>



<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="container">

    <div class="panel panel-default">
        <div class="panel-heading">${headerLabel}</div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover ">
                    <thead>
                    <tr>
                        <th>${nameLabel}</th>
                        <th>${freeQuotaLabel}</th>
                        <th>${paidQuotaLabel}</th>
                        <th>${freePointsLabel}</th>
                        <th>${paidPointsLabel}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="faculty" items="${faculties}">
                        <tr>
                            <td><tr:transl>${faculty.name}</tr:transl></td>
                            <td>${faculty.freeQuota}</td>
                            <td>${faculty.paidQuota}</td>
                            <td>${faculty.freePoint}</td>
                            <td>${faculty.paidPoint}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="included/js_list.jsp" %>

</body>
</html>