<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.application.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.application.button.apply" var="applyBtn"/>
<fmt:message bundle="${loc}" key="locale.page.application.button.delete" var="deleteAppButton"/>

<fmt:message bundle="${loc}" key="locale.page.profile.button.save" var="saveBtn"/>
<fmt:message bundle="${loc}" key="locale.page.profile.button.cancel" var="cancelBtn"/>
<fmt:message bundle="${loc}" key="locale.page.profile.button.update" var="updateBtn"/>
<fmt:message bundle="${loc}" key="locale.page.profile.button.delete" var="deleteBtn"/>

<fmt:message bundle="${loc}" key="locale.page.application.name" var="nameLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.faculty" var="facultyLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.applied" var="appliedLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.confirmed" var="confirmedLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.status.yes" var="yesLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.status.no" var="noLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.competition" var="compeititonLabel"/>
<fmt:message bundle="${loc}" key="locale.page.application.error" var="errorLabel"/>

<fmt:message bundle="${loc}" key="locale.data.personal" var="personalDataLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.first" var="firstNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.middle" var="middleNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.last" var="lastNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.passport" var="passportLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.birth" var="birthLabel"/>

<fmt:message bundle="${loc}" key="locale.data.personal.privileges" var="privilegesLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.medal" var="medalLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.medal.golden" var="goldenLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.medal.silver" var="silverLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.medal.none" var="noneLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.privileges.help" var="privilegesHelpLabel"/>

<fmt:message bundle="${loc}" key="locale.data.personal.contact" var="contactInfoLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.phone" var="phoneLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.address" var="addressLabel"/>

<fmt:message bundle="${loc}" key="locale.data.personal.education" var="educationLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.faculty" var="facultyLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.faculty.choose" var="chooseFacultyLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.gpa" var="gpaLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.total" var="totalLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.free" var="formLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.subject.message" var="subjectMessageLabel"/>

<html>
<head>
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="container">

    <form action="search" name="confirmApplication" method="post">
        <input type="hidden" name="command" value="create-application">
        <div class="well col-md-6 col-md-offset-3" id="confirmForm">
            <div class="col-md-10 col-md-offset-1">
                <div class="list-group">
                    <div class="list-group-item">
                        <div class="row-action-primary">
                            <i style="font-size:46px" class="material-icons">mood</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">${nameLabel}</h4>
                            <input type="hidden" name="profileID" value="${profile.id}">
                            <p class="list-group-item-text">
                                <tr:transl>${profile.firstName} ${profile.middleName} ${profile.lastName}</tr:transl>
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
                            <input type="hidden" name="facultyID" value="${profile.facultyId}">
                            <p class="list-group-item-text">
                                <c:forEach var="faculty" items="${faculties}">
                                    <c:if test="${faculty.id eq profile.facultyId}">
                                        <tr:transl>${faculty.name}</tr:transl>
                                    </c:if>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                    <div class="list-group-separator"></div>
                    <div class="list-group-item">
                        <div class="row-action-primary checkbox">
                            <label><input type="checkbox" name="outCompetition">
                            </label>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">${compeititonLabel}</h4>

                            <p class="list-group-item-text"></p>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${applicable}">
                <div class="col-md-3 col-md-offset-8">
                    <a href="javascript:confirmApplication.submit();"
                       class="btn btn-raised btn-primary">${applyBtn}</a>
                </div>
            </c:if>
        </div>

    </form>

    <form class="form-horizontal" name="update" accept-charset="utf-8" action="profile" method="post">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <a href="javascript:update.submit()" id="saveBtn"
                       class="btn btn-primary btn-lg btn-block btn-raised">${saveBtn}</a>
                </div>
                <div class="col-md-6">
                    <a href="javascript:void(0)" id="cancelBtn"
                       class="btn btn-default btn-lg btn-block btn-raised">${cancelBtn}</a>
                </div>
            </div>

            <input type="hidden" name="command" value="update-student-data">
            <input type="hidden" name="userID" value="${profile.userId}">

            <div class="row">
                <div class="col-md-6">

                    <div class="panel panel-default  clear">
                        <div class="panel-heading">${privilegesLabel}</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">${medalLabel}</label>

                                <div class="col-md-8">
                                    <div class="radio radio-primary" id="medalType">
                                        <label>
                                            <input type="radio" name="medal" id="none" value="none"
                                            <c:if test="${profile.medalType eq 'NONE'}">
                                                   checked="checked"
                                            </c:if>
                                                   readonly>
                                            ${noneLabel}
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="silver" value="silver"
                                            <c:if test="${profile.medalType eq 'SILVER'}">
                                                   checked="checked"
                                            </c:if>
                                                   readonly>
                                            ${silverLabel}
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="golden" value="golden"
                                            <c:if test="${profile.medalType eq 'GOLDEN'}">
                                                   checked="checked"
                                            </c:if>
                                                   readonly>
                                            ${goldenLabel}
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="privileges" class="col-md-3 control-label">${privilegesLabel}</label>

                                <div class="col-md-8">
                                    <textarea name="privileges" class="form-control" rows="3"
                                              id="privileges">
                                        ${profile.privileges}
                                    </textarea>
                                    <span class="help-block">${privilegesHelpLabel}</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updPrivileges" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="panel panel-default clear">
                        <div class="panel-heading">${contactInfoLabel}</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label for="phone" class="col-md-3 control-label">${phoneLabel}</label>

                                <div class="col-md-8">
                                    <input name="phone" type="text" class="form-control phone" id="phone"
                                           placeholder="${phoneLabel}"
                                           value="${profile.phone}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="address" class="col-md-3 control-label">${addressLabel}</label>

                                <div class="col-md-8">
                                    <textarea name="address" class="form-control" rows="3" id="address"
                                              required>
                                        ${profile.address}
                                    </textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updContact" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>

                <div class="col-md-6">
                    <div class="panel panel-default clear">
                        <div class="panel-heading">${personalDataLabel}</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="passportID">${passportLabel}</label>
                                <div class="col-md-8">
                                    <input name="passportID" type="text" id="passportID" class="form-control"
                                           placeholder="${passportLabel}"
                                           value="${profile.passportId}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstName" class="col-md-3 control-label">${firstNameLabel}</label>

                                <div class="col-md-8">
                                    <input name="firstName" type="text" class="form-control" id="firstName"
                                           placeholder="${firstNameLabel}"
                                           value="${profile.firstName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="middleName" class="col-md-3 control-label">${middleNameLabel}</label>

                                <div class="col-md-8">
                                    <input name="middleName" type="text" class="form-control" id="middleName"
                                           placeholder="${middleNameLabel}"
                                           value="${profile.middleName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="col-md-3 control-label">${lastNameLabel}</label>

                                <div class="col-md-8">
                                    <input name="lastName" type="text" class="form-control" id="lastName"
                                           placeholder="Last Name"
                                           value="${profile.lastName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="dateBirth" class="col-md-3 control-label">${birthLabel}</label>

                                <div class="col-md-8">
                                    <input name="dateBirth" type="text" class="form-control date" id="dateBirth"
                                           placeholder="${birthLabel}"
                                           value="<fmt:formatDate pattern=" dd.MM.yyyy"
                                value="${profile.birthDate.time}" />" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updPersonal" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default clear">
                        <div class="panel-heading">${educationLabel}</div>
                        <div class="panel-body">

                            <div class="form-group">
                                <div class="row">
                                    <div class="col-md-8">
                                        <label for="faculty" class="col-md-3 control-label">${facultyLabel}</label>
                                        <div class="col-md-8">
                                            <select name="facultyID" id="faculty" class="form-control select-dropdown" readonly="">
                                                <option disabled selected>${chooseFacultyLabel}</option>
                                                <c:forEach var="faculty" items="${faculties}">
                                                    <option value="${faculty.id}"
                                                            <c:if test="${faculty.id eq profile.facultyId}">selected</c:if>
                                                    >
                                                        <tr:transl>${faculty.name}</tr:transl>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="togglebutton">
                                            <label>
                                                <input name="freeForm" type="checkbox" checked>
                                                ${formLabel}
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div id="subjects"></div>

                            <div class="form-group">
                                <label for="totalPoints" class="col-md-5 control-label">${totalLabel}</label>

                                <div class="col-md-4">
                                    <input type="number" name="points" class="form-control" id="totalPoints"
                                           placeholder="0"
                                           pattern="\d{1,3}"
                                           value="${profile.points}" required readonly>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updEducation" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
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
<script src="content/js/ajax/singup.js"></script>

</body>
</html>
