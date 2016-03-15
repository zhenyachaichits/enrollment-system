<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>


<fmt:message bundle="${loc}" key="locale.data.personal" var="personalDataLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.first" var="firstNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.middle" var="middleNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.last" var="lastNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.passport" var="passportLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.form" var="formLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.free" var="freeLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.paid" var="paidLabel"/>

<fmt:message bundle="${loc}" key="locale.page.search.records" var="recordsLabel"/>
<fmt:message bundle="${loc}" key="locale.page.search.button.view" var="viewBtn"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${recordsLabel} ${fn:length(profiles)}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="container">
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
                                        <th>${firstNameLabel}</th>
                                        <th>${middleNameLabel}</th>
                                        <th>${lastNameLabel}</th>
                                        <th>${formLabel}</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="profile" items="${profiles}">
                                        <form action="apply_form" name="goApplyForm" method="post">
                                            <c:if test="${profile.applied}">
                                                <input type="hidden" name="command" value="go-application">
                                                <input type="hidden" name="profileID" value="${profile.id}">
                                            </c:if>
                                            <tr>
                                                <td><tr:transl>
                                                    <p class="centred">${profile.firstName}</p>
                                                </tr:transl></td>
                                                <td><tr:transl>
                                                    <p class="centred">${profile.middleName}</p>
                                                </tr:transl></td>
                                                <td><tr:transl>
                                                    <p class="centred">${profile.lastName}</p>
                                                </tr:transl></td>
                                                <c:if test="${profile.freeForm}">
                                                    <td><p class="centred">${freeLabel}</p></td>
                                                </c:if>
                                                <c:if test="${not profile.freeForm}">
                                                    <td><p class="centred">${freeLabel}</p></td>
                                                </c:if>
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

<%@include file="included/js_list.jsp" %>

</body>
</html>