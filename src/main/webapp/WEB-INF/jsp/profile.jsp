<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>

<fmt:message bundle="${loc}" key="locale.modal.sure.title" var="sureTitle"/>
<fmt:message bundle="${loc}" key="locale.modal.sure.description" var="sureDescription"/>
<fmt:message bundle="${loc}" key="locale.modal.sure.confirm" var="sureConfirm"/>
<fmt:message bundle="${loc}" key="locale.modal.sure.cancel" var="sureCancel"/>

<fmt:message bundle="${loc}" key="locale.page.profile.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.profile.button.save" var="saveBtn"/>
<fmt:message bundle="${loc}" key="locale.page.profile.button.cancel" var="cancelBtn"/>
<fmt:message bundle="${loc}" key="locale.page.profile.button.update" var="updateBtn"/>
<fmt:message bundle="${loc}" key="locale.page.profile.button.delete" var="deleteBtn"/>
<fmt:message bundle="${loc}" key="locale.page.profile.warning" var="warning"/>
<fmt:message bundle="${loc}" key="locale.page.profile.warning.message" var="warningMessage"/>

<fmt:message bundle="${loc}" key="locale.data.personal" var="personalDataLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.first" var="firstNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.middle" var="middleNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.name.last" var="lastNameLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.passport" var="passportLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.birth" var="birthLabel"/>

<fmt:message bundle="${loc}" key="locale.data.personal.account" var="accountDataLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.email" var="emailLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.password" var="passwordLabel"/>
<fmt:message bundle="${loc}" key="locale.data.personal.password.help" var="passwordHelpLabel"/>

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

    <c:if test="${student.profile.applied}">
        <div class="alert alert-dismissible alert-danger" style="background-color: #bd5050;">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>${warning}</strong>
            <p>${warningMessage}</p>
        </div>
    </c:if>

    <form class="form-horizontal" name="update" accept-charset="utf-8" action="profile" method="post">

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

        <input type="hidden" name="command" value="update-profile">

        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-default clear">
                    <div class="panel-heading">${accountDataLabel}</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="newEmail">${emailLabel}</label>
                            <div class="col-md-8">
                                <input type="email" name="email" id="newEmail" class="form-control"
                                       placeholder="${emailLabel}" value="${student.user.email}" required>
                            </div>
                        </div>
                        <div class="form-group" id="passwordGroup">
                            <label for="newPassword" class="col-md-3 control-label">${passwordLabel}</label>

                            <div class="col-md-8">
                                <input type="password" name="password" class="form-control" id="newPassword"
                                       placeholder="${passwordLabel}"
                                       pattern=".{5,20}" required>
                                <p class="help-block">${passwordHelpLabel}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-8">
                                <a id="updAccount" class="btn btn-primary">${updateBtn}</a>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="panel panel-default  clear">
                    <div class="panel-heading">${privilegesLabel}</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">${medalLabel}</label>

                            <div class="col-md-8">
                                <div class="radio radio-primary" id="medalType">
                                    <label>
                                        <input type="radio" name="medal" id="none" value="none"
                                        <c:if test="${student.profile.medalType eq 'NONE'}">
                                               checked="checked" </c:if> readonly>
                                        ${noneLabel}
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="medal" id="silver" value="silver"
                                        <c:if test="${student.profile.medalType eq 'SILVER'}">
                                               checked="checked" </c:if> readonly>
                                        ${silverLabel}
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="medal" id="golden" value="golden"
                                        <c:if test="${student.profile.medalType eq 'GOLDEN'}">
                                               checked="checked" </c:if> readonly>
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
                                        ${student.profile.privileges}
                                    </textarea>
                                <span class="help-block">${privilegesHelpLabel}</span>
                            </div>
                        </div>

                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updPrivileges" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>
                        </c:if>

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
                                       value="${student.profile.phone}" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address" class="col-md-3 control-label">${addressLabel}</label>

                            <div class="col-md-8">
                                    <textarea name="address" class="form-control" rows="3" id="address"
                                              required>
                                        ${student.profile.address}
                                    </textarea>
                            </div>
                        </div>

                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updContact" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>
                        </c:if>

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
                                       value="${student.profile.passportId}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="col-md-3 control-label">${firstNameLabel}</label>

                            <div class="col-md-8">
                                <input name="firstName" type="text" class="form-control" id="firstName"
                                       placeholder="${firstNameLabel}"
                                       value="${student.profile.firstName}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="middleName" class="col-md-3 control-label">${middleNameLabel}</label>

                            <div class="col-md-8">
                                <input name="middleName" type="text" class="form-control" id="middleName"
                                       placeholder="${middleNameLabel}"
                                       value="${student.profile.middleName}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-md-3 control-label">${lastNameLabel}</label>

                            <div class="col-md-8">
                                <input name="lastName" type="text" class="form-control" id="lastName"
                                       placeholder="${lastNameLabel}"
                                       value="${student.profile.lastName}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dateBirth" class="col-md-3 control-label">${birthLabel}</label>

                            <div class="col-md-8">
                                <input name="dateBirth" type="text" class="form-control date" id="dateBirth"
                                       placeholder="${birthLabel}"
                                       value="<fmt:formatDate pattern="dd.MM.yyyy"
                                       value="${student.profile.birthDate.time}" />" required>
                            </div>
                        </div>
                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updPersonal" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>
                        </c:if>
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
                                        <select name="facultyID" id="faculty" class="form-control select-dropdown"
                                                readonly="">
                                            <option disabled selected>${chooseFacultyLabel}</option>
                                            <c:forEach var="faculty" items="${faculties}">
                                                <option value="${faculty.id}"
                                                        <c:if test="${faculty.id eq student.profile.facultyId}">selected</c:if> >
                                                    <tr:transl>${faculty.name}</tr:transl>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="togglebutton">
                                        <label>
                                            <input name="freeForm" type="checkbox"
                                            <c:if test="${student.profile.freeForm}"> checked</c:if>> ${formLabel}
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <p id="subjectMessage" hidden> ${subjectMessageLabel} </p>
                        <div id="subjects">
                            <div class="form-group" id="subject1Group" style="display: none">
                                <label for="subject1" id="label1" class="col-md-5 control-label">

                                </label>

                                <div class="col-md-4">
                                    <input type="number" min="0" max="100" class="form-control point"
                                           id="subject1"
                                           placeholder="Points" onkeyup="refreshTotal();" onchange="refreshTotal();"
                                           pattern="\d{1,3}">
                                    <p class="help-block" id="help1"></p>
                                </div>
                            </div>
                            <div class="form-group" id="subject2Group" style="display: none">
                                <label for="subject2" id="label2" class="col-md-5 control-label">

                                </label>

                                <div class="col-md-4">
                                    <input type="number" min="0" max="100" class="form-control point"
                                           id="subject2"
                                           placeholder="Points" onkeyup="refreshTotal();" onchange="refreshTotal();"
                                           pattern="\d{1,3}">
                                    <p class="help-block" id="help2"></p>
                                </div>
                            </div>
                            <div class="form-group" id="subject3Group" style="display: none">
                                <label for="subject3" id="label3" class="col-md-5 control-label">
                                </label>

                                <div class="col-md-4">
                                    <input type="number" min="0" max="100" class="form-control point"
                                           id="subject3"
                                           placeholder="Points" onkeyup="refreshTotal();" onchange="refreshTotal();"
                                           pattern="\d{1,3}">
                                    <p class="help-block" id="help3"></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gpa" class="col-md-5 control-label">${gpaLabel}</label>

                                <div class="col-md-4">
                                    <input type="number" min="30" max="100" class="form-control point" id="gpa"
                                           placeholder="${gpaLabel}" onkeyup="refreshTotal();"
                                           onchange="refreshTotal();"
                                           pattern="\d{1,3}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <label for="totalPoints" class="col-md-5 control-label">${totalLabel}</label>

                            <div class="col-md-4">
                                <input type="number" name="points" class="form-control" id="totalPoints"
                                       placeholder="0"
                                       pattern="\d{1,3}"
                                       value="${student.profile.points}" required readonly>
                            </div>
                        </div>

                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-8">
                                    <a id="updEducation" class="btn btn-primary">${updateBtn}</a>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </form>
    <c:if test="${not student.profile.applied}">

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
    </c:if>

    <div id="sureDelete" class="modal fade in" tabindex="-1" style="display: none;">
        <div class="modal-dialog modal-md">
            <div class="modal-content" style=" margin-top: 140px">
                <div class="modal-header">
                    <button type="button" class="close btn btn-primary btn-fab small"
                            data-dismiss="modal" aria-hidden="true"><i class="material-icons">close</i></button>
                    <h4>${sureTitle}</h4>
                </div>
                <div class="modal-body">
                    <p>${sureDescription}</p>
                    <div class="row">
                        <div class="content">
                            <div class="col-md-12">
                                <div class="col-md-3 col-md-offset-7">
                                    <form action="home" name="deleteForm" method="post">
                                        <input type="hidden" name="command" value="delete-profile">
                                        <button type="submit"
                                       class="btn btn-raised btn-primary">${sureConfirm}</button>
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
