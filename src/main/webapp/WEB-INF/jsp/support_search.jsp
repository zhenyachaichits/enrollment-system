<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<c:set var="req" value="${pageContext.request}"/>
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.page.search.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.search.tab.name" var="nameTab"/>
<fmt:message bundle="${loc}" key="locale.page.search.tab.passport" var="passportTab"/>
<fmt:message bundle="${loc}" key="locale.page.search.tab.all" var="allTab"/>
<fmt:message bundle="${loc}" key="locale.page.search.button.find" var="findBtn"/>
<fmt:message bundle="${loc}" key="locale.page.search.button.find.applications" var="findApplicationsBtn"/>
<fmt:message bundle="${loc}" key="locale.page.search.button.find.profiles" var="findProfilesBtn"/>
<fmt:message bundle="${loc}" key="locale.page.search.button.view" var="viewBtn"/>
<fmt:message bundle="${loc}" key="locale.page.search.records" var="recordsLabel"/>

<fmt:message bundle="${loc}" key="locale.data.personal" var="personalDataLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.first" var="firstNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.middle" var="middleNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.last" var="lastNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.passport" var="passportLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.points" var="pointsLabel"/>

<html>
<head>
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>


<div class="content">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default clear">
                <div class="panel-heading">
                    <ul class="nav nav-tabs searchtab">
                        <li class="active"><a href="#byPassport" data-toggle="tab">
                            <div class="text-muted">${passportTab}</div>
                        </a></li>
                        <li><a href="#byLastName" data-toggle="tab">
                            <div class="text-muted">${nameTab}</div>
                        </a></li>
                        <li><a href="#all" data-toggle="tab">
                            <div class="text-muted">${allTab}</div>
                        </a></li>
                    </ul>
                </div>
                <div class="panel-body">


                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="byPassport">

                            <div class="row">
                                <div class="col-md-7 col-md-offset-2">
                                    <div class="form-group">
                                        <form action="search" id="searchByPassport" name="searchByPassport"
                                              method="post"
                                              accept-charset="utf-8">
                                            <input id="findByPassportCommand" type="hidden" name="command" value="">
                                            <input type="text" id="passportID" name="passportID" class="form-control"
                                                   placeholder="${passportLabel}">
                                        </form>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="btn-group">
                                            <a href="bootstrap-elements.html" data-target="#"
                                               class="btn btn-raised dropdown-toggle" data-toggle="dropdown">
                                                ${findBtn}
                                                <span class="caret"></span>
                                            </a>
                                            <ul class="dropdown-menu">
                                                <li><a id="findByPassportId" href="javascript:void(0)">${findProfilesBtn}</a></li>
                                                <li><a id="findAppByPassportId"
                                                       href="javascript:void(0)">${findApplicationsBtn}</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="byLastName">
                            <div class="row">
                                <div class="col-md-7 col-md-offset-2">
                                    <div class="form-group">
                                        <form action="search" id="searchByLastName" name="searchByLastName"
                                              method="post"
                                              accept-charset="utf-8">
                                            <input id="findByNameCommand" type="hidden" name="command" value="">
                                            <input type="text" id="lastName" name="lastName" class="form-control"
                                                   placeholder="${lastNameLabel}">
                                        </form>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="form-group">
                                        <div class="btn-group">
                                            <a href="bootstrap-elements.html" data-target="#"
                                               class="btn btn-raised dropdown-toggle" data-toggle="dropdown">
                                                ${findBtn}
                                                <span class="caret"></span>
                                            </a>
                                            <ul class="dropdown-menu">
                                                <li><a id="findByName" href="javascript:void(0)">${findProfilesBtn}</a></li>
                                                <li><a id="findAppByName" href="javascript:void(0)">${findApplicationsBtn}</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="all">
                            <div class="row">
                                <div class="col-md-6 col-md-offset-4">
                                    <div class="form-group">
                                        <form action="search" id="searchAll" name="searchAll" method="post"
                                              accept-charset="utf-8">
                                            <input id="findAllCommand" type="hidden" name="command" value="">
                                            <button id="findAllProfiles" class="btn btn-raised">${findProfilesBtn}</button>
                                            <button id="findAllApplied" class="btn btn-raised">${findApplicationsBtn}</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${not empty(profiles)}">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <p class="lead text-muted">${recordsLabel} ${fn:length(profiles)}</p>
                        <c:if test="${fn:length(profiles) gt 0}">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover ">
                                    <thead>
                                    <tr>
                                        <th>${passportLabel}</th>
                                        <th>${firstNameLabel}</th>
                                        <th>${middleNameLabel}</th>
                                        <th>${lastNameLabel}</th>
                                        <th>${pointsLabel}</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="profileLabel" items="${profiles}">
                                        <form action="apply_form" name="goApplyForm" method="post">
                                            <c:if test="${not profileLabel.applied}">
                                                <input type="hidden" name="command" value="go-apply-form">
                                            </c:if>
                                            <c:if test="${profileLabel.applied}">
                                                <input type="hidden" name="command" value="go-application">
                                            </c:if>
                                            <tr>
                                                <td>
                                                    <p class="centred">${profileLabel.passportId}</p>
                                                </td>
                                                <td><tr:transl>
                                                    <p class="centred">${profileLabel.firstName}</p>
                                                </tr:transl></td>
                                                <td><tr:transl>
                                                    <p class="centred">${profileLabel.middleName}</p>
                                                </tr:transl></td>
                                                <td><tr:transl>
                                                    <p class="centred">${profileLabel.lastName}</p>
                                                </tr:transl></td>
                                                <td><tr:transl>
                                                    <p class="centred">${profileLabel.points}</p>
                                                </tr:transl></td>
                                                <td>
                                                    <button name="profileID" value="${profileLabel.id}"
                                                            class="btn btn-primary">
                                                        ${viewBtn}
                                                    </button>
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <c:if test="${pagesNumber gt 1}">

                                <div class="col-md-12 horizontal-center">
                                    <ul class="pagination">
                                        <c:if test="${currentPage ne 1}">
                                            <form action="${command}" method="post" name="previousPage" hidden>
                                                <input type="hidden" name="currentPage" value="${currentPage - 1}">
                                            </form>
                                            <li><a href="javascript:previousPage.submit()">«</a></li>
                                        </c:if>

                                        <c:forEach begin="1" end="${pagesNumber}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    <li class="active"><a
                                                            style=" background-color: #1DA195;
                                                            border-color: #1DA195;"
                                                            href="javascript:void(0)">${i}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <form action="${command}" method="post" name="changePage" hidden>
                                                        <input type="hidden" name="currentPage" value="${i}">
                                                    </form>
                                                    <li><a href="javascript:changePage.submit()">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                        <c:if test="${currentPage lt pagesNumber}">
                                            <form action="${command}" method="post" name="nextPage" hidden>
                                                <input type="hidden" name="currentPage" value="${currentPage + 1}">
                                            </form>
                                            <li><a href="javascript:nextPage.submit()">»</a></li>
                                        </c:if>

                                    </ul>
                                </div>
                            </c:if>

                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>

</div>


<%@include file="included/js_list.jsp" %>
<script src="content/js/support-search.js"></script>
<script src="content/js/ajax/singup.js"></script>


</body>
</html>
