<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 22.02.2016
  Time: 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.index.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.index.header" var="divHeader"/>
<fmt:message bundle="${loc}" key="locale.page.index.description" var="description"/>
<fmt:message bundle="${loc}" key="locale.page.index.button.statistics" var="statisticsBtn"/>

<fmt:message bundle="${loc}" key="locale.modal.signin.message.error" var="errorMessage"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>


<div class="content">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default clear">
                <div class="panel-heading">Searching parameters</div>
                <div class="panel-body">
                    <form action="search" name="search" method="post">
                        <input type="hidden" name="command" value="search-profile-by-passport">
                        <div class="row">
                            <div class="col-md-7 col-md-offset-2">
                                <div class="form-group">
                                    <input type="search" name="passportID" class="form-control"
                                           placeholder="Passport ID">
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="form-group">
                                    <div class="btn-group">
                                        <a href="bootstrap-elements.html" data-target="#"
                                           class="btn btn-raised dropdown-toggle" data-toggle="dropdown">
                                            Find In
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li><a href="javascript:search.submit()">Profiles</a></li>
                                            <li><a href="javascript:void(0)">Applications</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-body">
                    <p class="lead text-muted">Records found: ${fn:length(profiles)}</p>
                    <c:if test="${fn:length(profiles) gt 0}">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>Passport ID</th>
                                    <th>First Name</th>
                                    <th>Middle Name</th>
                                    <th>Last Name</th>
                                    <th>Points</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="profile" items="${profiles}">
                                    <tr>
                                        <td>${profile.passportId}</td>
                                        <td><tr:transl><p>${profile.firstName}</p></tr:transl></td>
                                        <td><tr:transl><p>${profile.middleName}</p></tr:transl></td>
                                        <td><tr:transl><p>${profile.lastName}</p></tr:transl></td>
                                        <td><tr:transl><p>${profile.points}</p></tr:transl></td>
                                        <td><a href="javascript:void(0)" class="btn btn-primary">Find</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

</div>
</div>


<%@include file="included/js_list.jsp" %>

</body>
</html>
