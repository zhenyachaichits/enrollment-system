<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.application.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.application.name" var="nameLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.faculty" var="facultyLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.applied" var="appliedLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.confirmed" var="confirmedLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.status.yes" var="yesLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.status.no" var="noLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.competition" var="compeititonLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.error" var="errorLabel"/>

<html>
<head>
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="container">
    <c:if test="${empty(application)}">
        <div class="col-md-6 col-md-offset-3">
            <div class="well">
                <div class="horizontal-center">
                    <i class="material-icons" style="font-size: 18em">mood_bad</i>
                    <h1>${errorLabel}</h1>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty(application)}">
        <form action="search" name="deleteApplication" method="post">
            <input type="hidden" name="command" value="delete-application">
            <input type="hidden" name="profileID" value="${profile.id}">
            <div class="well col-md-6 col-md-offset-3" id="confirmForm">
                <div class="col-md-10 col-md-offset-1">
                    <div class="list-group">
                        <div class="list-group-item">
                            <div class="row-action-primary">
                                <i style="font-size:36px" class="material-icons">mood</i>
                            </div>
                            <div class="row-content">
                                <h4 class="list-group-item-heading">${nameLabel}</h4>
                                <p class="list-group-item-text">
                                    <tr:transl>${student.profile.firstName} ${student.profile.middleName}
                                        ${student.profile.lastName}</tr:transl>
                                </p>
                            </div>
                        </div>
                        <div class="list-group-separator"></div>
                        <div class="list-group-item">
                            <div class="row-action-primary">
                                <i style="font-size:36px" class="material-icons">school</i>
                            </div>
                            <div class="row-content">
                                <h4 class="list-group-item-heading">${facultyLabel}</h4>
                                <p class="list-group-item-text">
                                    <c:forEach var="faculty" items="${faculties}">
                                        <c:if test="${faculty.id eq student.profile.facultyId}">
                                            <tr:transl>${faculty.name}</tr:transl>
                                        </c:if>
                                    </c:forEach>
                                </p>
                            </div>
                        </div>
                        <div class="list-group-separator"></div>
                        <div class="list-group-item">
                            <div class="row-action-primary">
                                <i style="font-size:36px" class="material-icons">date_range</i>
                            </div>
                            <div class="row-content">
                                <h4 class="list-group-item-heading">${appliedLabel}</h4>
                                <p class="list-group-item-text">
                                    <fmt:formatDate pattern=" dd.MM.yyyy" value="${application.date.time}"/>
                                </p>
                            </div>
                        </div>
                        <div class="list-group-separator"></div>
                        <div class="list-group-item">
                            <div class="row-action-primary">
                                <i style="font-size:36px" class="material-icons">assignment_turned_in</i>
                            </div>
                            <div class="row-content">
                                <h4 class="list-group-item-heading">${confirmedLabel}</h4>
                                <p class="list-group-item-text">
                                    <c:if test="${application.confirmed}">
                                        ${yesLabel}
                                    </c:if>
                                    <c:if test="${not application.confirmed}">
                                        ${noLabel}
                                    </c:if>
                                </p>
                            </div>
                        </div>
                        <div class="list-group-separator"></div>
                        <div class="list-group-item">
                            <div class="row-action-primary checkbox">
                                <label><input type="checkbox" name="outCompetition"
                                <c:if test="${application.outOfCompetition}"> checked </c:if> disabled>
                                </label>
                            </div>
                            <div class="row-content">
                                <h4 class="list-group-item-heading">${compeititonLabel}</h4>

                                <p class="list-group-item-text"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </c:if>
</div>


<span id="errorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
      data-content="One or more records are incorrect" data-timeout="4000"
      data-snackbar-id="snackbar1454251274095"></span>
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="Account with such Email already exists" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>
    <span id="passportErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="Account with such Passport ID already exists" data-timeout="4000"
          data-snackbar-id="snackbar1454251274097"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/profile-update.js"></script>

</body>
</html>
