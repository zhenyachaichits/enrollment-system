<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.signup.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.signup.button.next" var="nextBtn"/>
<fmt:message bundle="${loc}" key="locale.page.signup.button.previous" var="previousBtn"/>
<fmt:message bundle="${loc}" key="locale.page.signup.button.confirm" var="confirmBtn"/>

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

<fmt:message bundle="${loc}" key="locale.message.wrongfield" var="errorMessage"/>
<fmt:message bundle="${loc}" key="locale.message.emailexist" var="emailMessage"/>
<fmt:message bundle="${loc}" key="locale.message.passportexist" var="passportMessage"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <link href="../../content/css/onepage-scroll.css" rel="stylesheet">
    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="scroll">
    <form class="form-horizontal" action="home" method="post">
        <input type="hidden" name="command" value="sign-up">
        <section id="account">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well bs-component">
                            <legend>${accountDataLabel}</legend>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="newEmail">${emailLabel}</label>
                                <div class="col-md-8">
                                    <input type="email" name="email" id="newEmail" class="form-control"
                                           placeholder="${emailLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newPassword" class="col-md-3 control-label">${passwordLabel}</label>

                                <div class="col-md-8">
                                    <input type="password" name="password" class="form-control" id="newPassword"
                                           placeholder="${passwordLabel}"
                                           pattern=".{5,20}" required>
                                    <p class="help-block">${passwordHelpLabel}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="toPersonal" class="btn btn-primary scroll-button">${nextBtn}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="personal">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well bs-component">
                            <legend>${personalDataLabel}</legend>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="passportID">${passportLabel}</label>
                                <div class="col-md-8">
                                    <input name="passportID" type="text" id="passportID" class="form-control"
                                           placeholder="${passportLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstName" class="col-md-3 control-label">${firstNameLabel}</label>

                                <div class="col-md-8">
                                    <input name="firstName" type="text" class="form-control" id="firstName"
                                           placeholder="${firstNameLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="middleName" class="col-md-3 control-label">${middleNameLabel}</label>

                                <div class="col-md-8">
                                    <input name="middleName" type="text" class="form-control" id="middleName"
                                           placeholder="${middleNameLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="col-md-3 control-label">${lastNameLabel}</label>

                                <div class="col-md-8">
                                    <input name="lastName" type="text" class="form-control" id="lastName"
                                           placeholder="${lastNameLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="dateBirth" class="col-md-3 control-label">${birthLabel}</label>

                                <div class="col-md-8">
                                    <input name="dateBirth" type="text" class="form-control date" id="dateBirth"
                                           placeholder="${birthLabel}"
                                           required>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-6">
                                    <a onclick="$('.scroll').moveUp(); $('.progress-bar').width('1%')"
                                       class="btn btn-primary scroll-button">${previousBtn}</a>
                                    <a id="toEducation" class="btn btn-primary scroll-button">${nextBtn}</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="education">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well bs-component">
                            <legend>${educationLabel}</legend>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-md-8">
                                        <label for="faculty" class="col-md-3 control-label">${facultyLabel}</label>
                                        <div class="col-md-8">
                                            <select name="facultyID" id="faculty" class="form-control select-dropdown">
                                                <option disabled selected>${chooseFacultyLabel}</option>
                                                <c:forEach var="faculty" items="${faculties}">
                                                    <option value="${faculty.id}">
                                                        <tr:transl>${faculty.name}</tr:transl>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <div class="togglebutton">
                                            <label>
                                                <input name="freeForm" type="checkbox" checked="checked"> ${formLabel}
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
                                               onkeyup="refreshTotal();" onchange="refreshTotal();"
                                               pattern="\d{1,3}">
                                        <p class="help-block" id="help1"> </p>
                                    </div>
                                </div>
                                <div class="form-group" id="subject2Group" style="display: none">
                                    <label for="subject2" id="label2" class="col-md-5 control-label">

                                    </label>

                                    <div class="col-md-4">
                                        <input type="number" min="0" max="100" class="form-control point"
                                               id="subject2"
                                                onkeyup="refreshTotal();" onchange="refreshTotal();"
                                               pattern="\d{1,3}">
                                        <p class="help-block" id="help2"> </p>
                                    </div>
                                </div>
                                <div class="form-group" id="subject3Group" style="display: none">
                                    <label for="subject3" id="label3" class="col-md-5 control-label">
                                    </label>

                                    <div class="col-md-4">
                                        <input type="number" min="0" max="100" class="form-control point"
                                               id="subject3"
                                                onkeyup="refreshTotal();" onchange="refreshTotal();"
                                               pattern="\d{1,3}">
                                        <p class="help-block" id="help3"> </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="gpa" class="col-md-5 control-label">${gpaLabel}</label>

                                    <div class="col-md-4">
                                        <input type="number" min="30" max="100" class="form-control point" id="gpa"
                                               placeholder="${gpaLabel}" onkeyup="refreshTotal();" onchange="refreshTotal();"
                                               pattern="\d{1,3}">
                                    </div>
                                </div>

                            </div>

                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="col-md-3">
                                        <label for="totalPoints" class=" control-label">${totalLabel}</label>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="number" name="points" class="form-control" id="totalPoints"
                                               placeholder="0"
                                               pattern="\d{1,3}" value="0" required readonly>
                                    </div>
                                    <a onclick="$('.scroll').moveUp(); $('.progress-bar').width('20%')"
                                       class="btn btn-primary scroll-button">${previousBtn}</a>
                                    <a id="toPrivileges" class="btn btn-primary scroll-button">${nextBtn}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="privilegesSection">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well bs-component">
                            <legend>${privilegesLabel}</legend>
                            <div class="form-group">
                                <label class="col-md-3 control-label">${medalLabel}</label>

                                <div class="col-md-8">
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="none" value="none"
                                                   checked="checked">
                                            ${noneLabel}
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="silver" value="silver">
                                            ${silverLabel}
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="golden" value="golden">
                                            ${goldenLabel}
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="privileges" class="col-md-3 control-label">${privilegesLabel}</label>

                                <div class="col-md-8">
                                    <textarea name="privileges" class="form-control" rows="3"
                                              id="privileges"></textarea>
                                    <span class="help-block">${privilegesHelpLabel}</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-6">
                                    <a onclick="$('.scroll').moveUp(); $('.progress-bar').width('40%')"
                                       class="btn btn-primary scroll-button">${previousBtn}</a>
                                    <a id="toContact" class="btn btn-primary scroll-button">${nextBtn}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well bs-component">
                            <legend>${contactInfoLabel}</legend>
                            <div class="form-group">
                                <label for="phone" class="col-md-3 control-label">${phoneLabel}</label>

                                <div class="col-md-8">
                                    <input name="phone" type="text" class="form-control phone" id="phone"
                                           placeholder="${phoneLabel}"
                                           required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="address" class="col-md-3 control-label">${addressLabel}</label>

                                <div class="col-md-8">
                                    <textarea name="address" class="form-control" rows="3" id="address"
                                              required></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-6">
                                    <a onclick="$('.scroll').moveUp(); $('.progress-bar').width('60%')"
                                       class="btn btn-primary scroll-button">${previousBtn}</a>
                                    <button id="confirm" type="submit" class="btn btn-raised btn-primary">${confirmBtn}
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </form>

    <span id="errorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${errorMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274095"></span>
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${emailMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>
    <span id="passportErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${passportMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274097"></span>
</div>

<div class="progress progress-striped active mobile-hidden" style="position: relative; bottom: 13%">
    <div class="progress-bar" style="width: 1%"></div>
</div>

<%@include file="included/js_list.jsp" %>
<script src="content/js/jquery.onepage-scroll.min.js"></script>
<script src="content/js/onepage-config.js"></script>
<script src="content/js/onepage-navigation.js"></script>
<script src="content/js/ajax/singup.js"></script>

</body>
</html>