<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 08.02.2016
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tr" uri="http://epam.com/project/university/transliterate"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.index.title" var="title"/>

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
        <div class="panel-heading">Faculties statistics</div>
        <div class="panel-body">
            <table class="table table-striped table-hover ">
                <thead>
                <tr>
                    <th>Faculty</th>
                    <th>Free Form Quota</th>
                    <th>Paid Form Quota</th>
                    <th>Points for Free Form </th>
                    <th>Points for Paid Form </th>
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


<%@include file="included/js_list.jsp" %>
<script src="content/js/ajax/authenticate.js"></script>
<script src="content/js/locale.js"></script>

</body>
</html>