<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.index.title" var="title"/>

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
            <strong>Attention!</strong>
            <p>You can't change your personal data, because your application was created</p>
        </div>
    </c:if>

    <form class="form-horizontal" name="update" accept-charset="utf-8" action="profile" method="post">

        <div class="row">
            <div class="col-md-6">
                <a href="javascript:update.submit()" id="saveBtn"
                   class="btn btn-primary btn-lg btn-block btn-raised">SAVE</a>
            </div>
            <div class="col-md-6">
                <a href="javascript:void(0)" id="cancelBtn"
                   class="btn btn-default btn-lg btn-block btn-raised">CANCEL</a>
            </div>
        </div>

        <input type="hidden" name="command" value="update-profile">

        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-default clear">
                    <div class="panel-heading">Account Data</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="newEmail">Email</label>
                            <div class="col-md-8">
                                <input type="email" name="email" id="newEmail" class="form-control"
                                       placeholder="Email" value="${student.user.email}" required>
                            </div>
                        </div>
                        <div class="form-group" id="passwordGroup">
                            <label for="newPassword" class="col-md-3 control-label">Password</label>

                            <div class="col-md-8">
                                <input type="password" name="password" class="form-control" id="newPassword"
                                       placeholder="Password"
                                       pattern=".{5,20}" required>
                                <p class="help-block">Password length should be between 6 and 20 characters </p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 col-md-offset-9">
                                <a id="updAccount" class="btn btn-primary">Update</a>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="panel panel-default  clear">
                    <div class="panel-heading">Privileges</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">Medal</label>

                            <div class="col-md-8">
                                <div class="radio radio-primary" id="medalType">
                                    <label>
                                        <input type="radio" name="medal" id="none" value="none"
                                        <c:if test="${student.profile.medalType eq 'NONE'}">
                                               checked="checked" </c:if> readonly>
                                        None
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="medal" id="silver" value="silver"
                                        <c:if test="${student.profile.medalType eq 'SILVER'}">
                                               checked="checked" </c:if> readonly>
                                        Silver
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="medal" id="golden" value="golden"
                                        <c:if test="${student.profile.medalType eq 'GOLDEN'}">
                                               checked="checked" </c:if> readonly>
                                        Golden
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="privileges" class="col-md-3 control-label">Privileges</label>

                            <div class="col-md-8">
                                    <textarea name="privileges" class="form-control" rows="3"
                                              id="privileges">
                                        ${student.profile.privileges}
                                    </textarea>
                                <span class="help-block">Not required</span>
                            </div>
                        </div>

                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updPrivileges" class="btn btn-primary">Update</a>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>

                <div class="panel panel-default clear">
                    <div class="panel-heading">Contact Info</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="phone" class="col-md-3 control-label">Phone Number</label>

                            <div class="col-md-8">
                                <input name="phone" type="text" class="form-control phone" id="phone"
                                       placeholder="Phone Number"
                                       value="${student.profile.phone}" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address" class="col-md-3 control-label">Address</label>

                            <div class="col-md-8">
                                    <textarea name="address" class="form-control" rows="3" id="address"
                                              required>
                                        ${student.profile.address}
                                    </textarea>
                                <span class="help-block">Lalalala.</span>
                            </div>
                        </div>

                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updContact" class="btn btn-primary">Update</a>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>

            </div>

            <div class="col-md-6">
                <div class="panel panel-default clear">
                    <div class="panel-heading">Personal Data</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="passportID">Passport ID</label>
                            <div class="col-md-8">
                                <input name="passportID" type="text" id="passportID" class="form-control"
                                       placeholder="Passport ID"
                                       value="${student.profile.passportId}" required>
                                <p class="help-block">You can find ID on the last page of your Passport </p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="col-md-3 control-label">First Name</label>

                            <div class="col-md-8">
                                <input name="firstName" type="text" class="form-control" id="firstName"
                                       placeholder="First Name"
                                       value="${student.profile.firstName}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="middleName" class="col-md-3 control-label">Middle Name</label>

                            <div class="col-md-8">
                                <input name="middleName" type="text" class="form-control" id="middleName"
                                       placeholder="Middle Name"
                                       value="${student.profile.middleName}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="lastName" class="col-md-3 control-label">Last Name</label>

                            <div class="col-md-8">
                                <input name="lastName" type="text" class="form-control" id="lastName"
                                       placeholder="Last Name"
                                       value="${student.profile.lastName}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dateBirth" class="col-md-3 control-label">Birth Date</label>

                            <div class="col-md-8">
                                <input name="dateBirth" type="text" class="form-control date" id="dateBirth"
                                       placeholder="Birth Date"
                                       value="<fmt:formatDate pattern="dd.MM.yyyy"
                                       value="${student.profile.birthDate.time}" />" required>
                            </div>
                        </div>
                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updPersonal" class="btn btn-primary">Update</a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>

                <div class="panel panel-default clear">
                    <div class="panel-heading">Education</div>
                    <div class="panel-body">

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-8">
                                    <label for="faculty" class="col-md-3 control-label">Faculty</label>
                                    <div class="col-md-8">
                                        <select name="facultyID" id="faculty" class="form-control" readonly="">
                                            <option disabled selected>Choose faculty</option>
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
                                                   <c:if test="${student.profile.freeForm}"> checked</c:if>>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div id="subjects">
                            <div class="form-group" id="subject1Group" style="display: none">
                                <label for="subject1" id="label1" class="col-md-5 control-label">

                                </label>

                                <div class="col-md-4">
                                    <input type="number" min="0" max="100" class="form-control point"
                                           id="subject1"
                                           placeholder="Points" onkeyup="refreshTotal();" onchange="refreshTotal();"
                                           pattern="\d{1,3}">
                                    <p class="help-block" id="help1">Minimal point for this subjest is: </p>
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
                                    <p class="help-block" id="help2">Minimal point for this subjest is: </p>
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
                                    <p class="help-block" id="help3">Minimal point for this subjest is: </p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gpa" class="col-md-5 control-label">GPA</label>

                                <div class="col-md-4">
                                    <input type="number" min="30" max="100" class="form-control point" id="gpa"
                                           placeholder="Points" onkeyup="refreshTotal();" onchange="refreshTotal();"
                                           pattern="\d{1,3}">
                                </div>
                            </div>

                        </div>

                        <div class="form-group">
                            <label for="totalPoints" class="col-md-5 control-label">Total:</label>

                            <div class="col-md-4">
                                <input type="number" name="points" class="form-control" id="totalPoints"
                                       placeholder="0"
                                       pattern="\d{1,3}"
                                       value="${student.profile.points}" required readonly>
                            </div>
                        </div>

                        <c:if test="${not student.profile.applied}">
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updEducation" class="btn btn-primary">Update</a>
                                </div>
                            </div>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </form>
    <c:if test="${not student.profile.applied}">
        <form action="home" method="post">
            <input type="hidden" name="command" value="delete-profile">
            <button type="submit"
                    class="btn btn-raised btn-block btn-danger" style="background-color: #bd5050;">Delete
            </button>

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
<script src="content/js/ajax/singup.js"></script>

</body>
</html>
