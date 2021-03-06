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
<fmt:message bundle="${loc}" key="locale.page.application.terms" var="termsLabel"/>

<fmt:message bundle="${loc}" key="locale.message.wrongfield" var="errorMessage"/>
<fmt:message bundle="${loc}" key="locale.message.emailexist" var="emailMessage"/>
<fmt:message bundle="${loc}" key="locale.message.passportexist" var="passportMessage"/>

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
            <div class="horizontal-center centred">
                <a href="javascript:void(0)" class="btn btn-default btn-fab" style="background-color: #f4f4ee;
                height: 250px; width: 250px;">
                    <i class="material-icons" style=" color: #7e7e79; top: 50%;left: 15%; font-size: 200px">content_paste</i>
                </a>
                <h1 style="color: #7e7e79 ">${errorLabel}</h1>
                <h3 style="color: #7e7e79 ">${termsLabel}</h3>
                <h3 style="color: #7e7e79 "><fmt:formatDate pattern=" dd.MM.yyyy" value="${terms.startDate.time}"/>
                - <fmt:formatDate pattern=" dd.MM.yyyy" value="${terms.endDate.time}"/></h3>
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
                                    <tr:transl>${faculty.name}</tr:transl>
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
      data-content="${errorMessage}" data-timeout="4000"
      data-snackbar-id="snackbar1454251274095"></span>
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${emailMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>
    <span id="passportErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${passportMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274097"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/profile-update.js"></script>
<script src="content/js/ajax/signup.js"></script>

</body>
</html>
