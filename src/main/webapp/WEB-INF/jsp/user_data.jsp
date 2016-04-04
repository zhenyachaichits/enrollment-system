<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.users.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.users.role" var="roleLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.role.student" var="studentLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.role.admin" var="adminLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.role.support" var="supportLabel"/>
<fmt:message bundle="${loc}" key="locale.page.users.button.delete" var="deleteBtn"/>
<fmt:message bundle="${loc}" key="locale.page.users.button.save" var="saveBtn"/>

<fmt:message bundle="${loc}" key="locale.data.personal.email" var="emailLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.password" var="passwordLabel"/>

<fmt:message bundle="${loc}" key="locale.modal.sure.title" var="sureTitle"/>
<fmt:message bundle="${loc}" key="locale.modal.sure.description" var="sureDescription"/>
<fmt:message bundle="${loc}" key="locale.modal.sure.confirm" var="sureConfirm"/>
<fmt:message bundle="${loc}" key="locale.modal.sure.cancel" var="sureCancel"/>

<fmt:message bundle="${loc}" key="locale.message.emailexist" var="emailMessage"/>
<fmt:message bundle="${loc}" key="locale.message.wrongfield" var="fieldMessage"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${user.email}</title>

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
                            <h4 class="list-group-item-heading">${emailLabel}</h4>
                            <p class="list-group-item-text">
                            <div class="form-group" style="margin: 7px 0 0 0">
                                <div class="col-md-10">
                                    <input type="email" name="email" class="form-control" id="newEmail"
                                           value="${user.email}" placeholder="${emailLabel}">
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
                            <h4 class="list-group-item-heading">${passwordLabel}</h4>
                            <p class="list-group-item-text">
                            <div class="form-group" style="margin: 7px 0 0 0">
                                <div class="col-md-10">
                                    <input type="password" name="password" class="form-control" id="password"
                                           placeholder="${passwordLabel}">
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
                            <h4 class="list-group-item-heading">${roleLabel}</h4>
                            <p class="list-group-item-text">
                                <c:choose>
                                <c:when test="${user.role eq 'STUDENT'}">
                                ${studentLabel}
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
                                            ${supportLabel}
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="role" id="adminRole"
                                                   value="ADMIN"
                                            <c:if test="${user.role eq 'ADMIN'}"> checked="" </c:if>>
                                            ${adminLabel}
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
                    <div class="col-md-12 col-md-offset-9">
                        <form name="deleteUser" action="management" method="post" hidden>
                            <input type="hidden" name="command" value="delete-user">
                            <input type="hidden" name="userID" value="${user.id}">
                            <input type="hidden" name="role" value="${user.role}">
                        </form>
                        <a href="javascript:updateUser.submit()" class="btn btn-raised btn-primary">${saveBtn}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <a href="javascript:void(0)"
       style="position: fixed;
                        box-shadow: 0 0 4px rgba(0,0,0,.14),0 4px 8px rgba(0,0,0,.28);
                        z-index: 25;
                        bottom: 25px;
                        right: 25px;
                        background-color: #d23f31;
                        height: 56px;
                        width: 56px;
                        outline: none;"
       class="btn btn-danger btn-fab rounded-btn"
       data-toggle="modal" data-target="#sureDelete"><i class="material-icons">delete</i></a>

    <div id="sureDelete" class="modal fade in" tabindex="-1" style="display: none;">
        <div class="modal-dialog modal-md">
            <div class="modal-content" style=" margin-top: 140px">
                <div class="modal-header">
                    <button type="button" class="close btn btn-primary btn-fab small"
                            data-dismiss="modal" aria-hidden="true"><i class="material-icons">close</i></button>
                    <h4>${sureTitle}</h4>
                </div>
                <div class="modal-body">
                    <form name="deleteFaculty" id="deleteFaculty" action="management" method="post" hidden>
                        <input type="hidden" name="command" value="delete-faculty">
                        <input type="hidden" name="facultyID" value="${faculty.id}">
                    </form>
                    <p>${sureDescription}</p>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="col-md-3 col-md-offset-7">
                                <a href="javascript:deleteUser.submit();"
                                   class="btn btn-raised btn-primary">${sureConfirm}</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

    <span id="errorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${fieldMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274095"></span>
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${emailMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/ajax/singup.js"></script>

</body>
</html>