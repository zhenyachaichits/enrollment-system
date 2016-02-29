<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

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
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="content">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">

            <div class="panel panel-default clear">
                <div class="panel-heading">
                    <ul class="nav nav-tabs searchtab">
                        <li><a href="#findByRole" data-toggle="tab">
                            <div class="text-muted">By Last Name</div>
                        </a></li>
                        <li><a href="#addUser" data-toggle="tab">
                            <div class="text-muted">By Passport ID</div>
                        </a></li>
                    </ul>
                </div>
                <div class="panel-body">


                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="addUser">

                            <div class="row">
                                <form action="management" method="post">
                                    <fieldset>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="newEmail">Email</label>
                                            <div class="col-md-8">
                                                <input type="email" name="name" id="newEmail" class="form-control"
                                                       placeholder="Email"
                                                       required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="newPassword">Name</label>
                                            <div class="col-md-8">
                                                <input type="password" name="name" id="newPassword" class="form-control"
                                                       placeholder="Password"
                                                       required>
                                            </div>
                                        </div>

                                        <a href="javascript:deleteApplication.submit();"
                                           class="btn btn-raised btn-danger" style="background-color: #bd5050;">Delete</a>
                                    </fieldset>
                                </form>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="findByRole">
                            <div class="row">
                                <div class="col-md-6 col-md-offset-4">
                                    <div class="form-group">
                                        <form action="search" id="searchUsers" name="searchUsers" method="post"
                                              accept-charset="utf-8">
                                            <input id="findCommand" type="hidden" name="command"
                                                   value="search-users-by-role">
                                            <input id="findRole" type="hidden" name="role" value="">
                                            <button id="findStudent" class="btn btn-raised">Student</button>
                                            <button id="findAdmin" class="btn btn-raised">Admin</button>
                                            <button id="findSupport" class="btn btn-raised">Support</button>
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

    <c:if test="${fn:length(users) gt 0}">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-body">

                        <p class="lead text-muted">Records found: ${fn:length(users)}</p>

                        <div class="table-responsive">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>Email</th>
                                    <th>Access level</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${users}">
                                    <form action="apply_form" name="goApplyForm" method="post">
                                        <input type="hidden" name="command" value="go-user-data">
                                        <tr>
                                            <td>
                                                <p class="centred">${user.email}</p>
                                            </td>
                                            <td>
                                                <p class="centred">${user.role}</p>
                                            </td>
                                            <td>
                                                <button name="userID" value="${user.id}"
                                                        class="btn btn-primary">
                                                    More
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
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>


<%@include file="included/js_list.jsp" %>
<script src="content/js/user-management.js"></script>
<script src="content/js/ajax/singup.js"></script>


</body>
</html>
