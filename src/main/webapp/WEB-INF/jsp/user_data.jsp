<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<div class="container">
    <div class="well col-md-6 col-md-offset-3" id="confirmForm">
        <div class="col-md-10 col-md-offset-1">
            <div class="list-group">
                <form action="management" name="updateUser" method="post">
                    <input type="hidden" name="command" value="update-user">
                    <input type="hidden" name="userID" value="${user.id}">
                    <div class="list-group-item">
                        <div class="row-action-primary">
                            <i style="font-size:36px" class="material-icons">contact_mail</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">Email</h4>
                            <p class="list-group-item-text">
                            <div class="form-group" style="margin: 7px 0 0 0">
                                <div class="col-md-10">
                                    <input type="email" name="email" class="form-control" id="newEmail"
                                           value="${user.email}" placeholder="Email">
                                </div>
                            </div>
                            </p>
                        </div>
                    </div>
                    <div class="list-group-separator"></div>
                    <div class="list-group-item">
                        <div class="row-action-primary">
                            <i style="font-size:36px" class="material-icons">more_horiz</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">Password</h4>
                            <p class="list-group-item-text">
                            <div class="form-group" style="margin: 7px 0 0 0">
                                <div class="col-md-10">
                                    <input type="password" name="password" class="form-control" id="password"
                                           placeholder="Password">
                                </div>
                            </div>
                            </p>
                        </div>
                    </div>
                    <div class="list-group-separator"></div>
                    <div class="list-group-item">
                        <div class="row-action-primary">
                            <i style="font-size:36px" class="material-icons">group</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">Role</h4>
                            <p class="list-group-item-text">
                                <c:choose>
                                <c:when test="${user.role eq 'STUDENT'}">
                                Student
                                    <input type="hidden" name="role" value="STUDENT">
                                </c:when>
                                <c:otherwise>
                            <div class="form-group" style="margin: 7px 0 0 0">
                                <div class="col-md-10">
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="role" id="supportRole"
                                                   value="SUPPORT"
                                            <c:if test="${user.role eq 'SUPPORT'}"> checked="" </c:if>>
                                            Support
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="role" id="adminRole"
                                                   value="ADMIN"
                                            <c:if test="${user.role eq 'ADMIN'}"> checked="" </c:if>>
                                            Admin
                                        </label>
                                    </div>
                                </div>
                            </div>
                            </c:otherwise>
                            </c:choose>

                            </p>
                        </div>
                    </div>
                </form>
                <div class="list-group-separator"></div>
                <div class="form-group">
                    <div class="col-md-12 col-md-offset-6">
                        <form name="deleteUser" action="management" method="post" hidden>
                            <input type="hidden" name="command" value="delete-user">
                            <input type="hidden" name="userID" value="${user.id}">
                            <input type="hidden" name="role" value="${user.role}">
                        </form>
                        <a href="javascript:deleteUser.submit()" class="btn btn-raised btn-primary"
                           style="background-color: #bd5050;">Delete</a>
                        <a href="javascript:updateUser.submit()" class="btn btn-raised btn-primary">Save</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

    <span id="errorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="One or more records are incorrect" data-timeout="4000"
          data-snackbar-id="snackbar1454251274095"></span>
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="Account with such Email already exists" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/ajax/singup.js"></script>

</body>
</html>