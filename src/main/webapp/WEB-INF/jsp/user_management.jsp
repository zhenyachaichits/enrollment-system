<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.users.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.users.role" var="roleLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.tab.search" var="searchTab"/>
<fmt:message bundle="${loc}" key="locale.page.users.tab.create" var="createTab"/>
<fmt:message bundle="${loc}" key="locale.page.users.role.student" var="studentLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.role.admin" var="adminLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.role.support" var="supportLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.button.create" var="createBtn"/>
<fmt:message bundle="${loc}" key="locale.page.users.button.more" var="moreBtn"/>
<fmt:message bundle="${loc}" key="locale.page.users.records" var="foundLabel"/>

<fmt:message bundle="${loc}" key="locale.data.personal.email" var="emailLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.password" var="passwordLabel"/>


<html>
<head>
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="content">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <select id="selRole" class="form-control select-dropdown">
                                <option selected>${roleLabel}</option>
                                <option id="findStudent">${studentLabel}</option>
                                <option id="findAdmin">${adminLabel}</option>
                                <option id="findSupport">${supportLabel}</option>
                            </select>
                            <form action="search" id="searchUsers" name="searchUsers" method="post"
                                  accept-charset="utf-8" hidden>
                                <input id="findCommand" type="hidden" name="command"
                                       value="search-users-by-role">
                                <input id="findRole" type="hidden" name="role" value="">
                            </form>
                        </div>
                    </div>
                    <p class="lead text-muted horizontal-center">${foundLabel} ${fn:length(users)}</p>
                    <c:if test="${fn:length(users) gt 0}">

                        <div class="table-responsive">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>${emailLabel}</th>
                                    <th>${roleLabel}</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${users}">
                                    <form action="management" name="goApplyForm" method="post">
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
                                                        ${moreBtn}
                                                </button>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
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
                                            <form action="${command}" method="post" id="change${i}" name="change"
                                                  hidden>
                                                <input type="hidden" name="currentPage" value="${i}">
                                            </form>
                                            <li><a href="javascript:void(0)"
                                                   onclick="('form:#change${i}').submit()">${i}</a></li>
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

    <div id="addFaculty" class="modal fade" tabindex="-1" style="display: none;">
        <div class="modal-dialog modal-md" style=" margin-top: 10%">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h2 class="modal-title">${createTab}</h2>
                </div>
                <div class="modal-body">
                    <form action="management" name="addUser" method="post">
                        <input type="hidden" name="command" value="create-user">
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="newEmail">${emailLabel}</label>
                                <div class="col-md-8">
                                    <input type="email" name="email" id="newEmail" class="form-control"
                                           placeholder="${emailLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label"
                                       for="newPassword">${passwordLabel}</label>
                                <div class="col-md-8">
                                    <input type="password" name="password" id="newPassword"
                                           class="form-control"
                                           placeholder="${passwordLabel}"
                                           required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">${roleLabel}</label>

                                <div class="col-md-8">
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="role" id="supportRole" value="SUPPORT"
                                                   checked="">
                                            ${supportLabel}
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="role" id="adminRole" value="ADMIN">
                                            ${adminLabel}
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-7">
                                    <button class="btn btn-primary">${createBtn}</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <button data-toggle="modal" data-target="#addFaculty" href="javascript:void(0)"
            style="position: fixed;
                        box-shadow: 0 0 4px rgba(0,0,0,.14),0 4px 8px rgba(0,0,0,.28);
                        z-index: 25;
                        bottom: 25px;
                        right: 25px;
                        background-color: #d23f31;
                        height: 56px;
                        width: 56px;"
            class="btn btn-danger btn-fab rounded-btn"><i class="material-icons">add</i></button>


</div>

    <span id="errorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="One or more records are incorrect" data-timeout="4000"
          data-snackbar-id="snackbar1454251274095"></span>
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="Account with such Email already exists" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/user-management.js"></script>
<script src="content/js/ajax/singup.js"></script>


</body>
</html>
