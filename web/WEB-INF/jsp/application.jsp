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
                            <h4 class="list-group-item-heading">Name</h4>
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
                            <h4 class="list-group-item-heading">Faculty</h4>
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
                        <div class="row-action-primary">
                            <i style="font-size:36px" class="material-icons">date_range</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">Applied</h4>
                            <p class="list-group-item-text">
                                <fmt:formatDate pattern=" dd.MM.yyyy" value="${application.date.time}" />
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
                            <h4 class="list-group-item-heading">Out of competition</h4>

                            <p class="list-group-item-text"></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-md-offset-8">
                <a href="javascript:deleteApplication.submit();"
                   class="btn btn-raised btn-danger" style="background-color: #bd5050;">Delete</a>
            </div>
        </div>

    </form>

    <form class="form-horizontal" name="update" accept-charset="utf-8" action="profile" method="post">
        <div class="container">
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

            <input type="hidden" name="command" value="update-student-data">
            <input type="hidden" name="userID" value="${profile.userId}">

            <div class="row">
                <div class="col-md-6">

                    <div class="panel panel-default  clear">
                        <div class="panel-heading">Privileges</div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">Medal</label>

                                <div class="col-md-8">
                                    <div class="radio radio-primary" id="medalType">
                                        <label>
                                            <input type="radio" name="medal" id="none" value="none"
                                            <c:if test="${profile.medalType eq 'NONE'}">
                                                   checked="checked"
                                            </c:if>
                                                   readonly>
                                            None
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="silver" value="silver"
                                            <c:if test="${profile.medalType eq 'SILVER'}">
                                                   checked="checked"
                                            </c:if>
                                                   readonly>
                                            Silver
                                        </label>
                                    </div>
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="golden" value="golden"
                                            <c:if test="${profile.medalType eq 'GOLDEN'}">
                                                   checked="checked"
                                            </c:if>
                                                   readonly>
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
                                        ${profile.privileges}
                                    </textarea>
                                    <span class="help-block">Not required</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updPrivileges" class="btn btn-primary">Update</a>
                                </div>
                            </div>

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
                                           value="${profile.phone}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="address" class="col-md-3 control-label">Address</label>

                                <div class="col-md-8">
                                    <textarea name="address" class="form-control" rows="3" id="address"
                                              required>
                                        ${profile.address}
                                    </textarea>
                                    <span class="help-block">Lalalala.</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updContact" class="btn btn-primary">Update</a>
                                </div>
                            </div>

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
                                           value="${profile.passportId}" required>
                                    <p class="help-block">You can find ID on the last page of your Passport </p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstName" class="col-md-3 control-label">First Name</label>

                                <div class="col-md-8">
                                    <input name="firstName" type="text" class="form-control" id="firstName"
                                           placeholder="First Name"
                                           value="${profile.firstName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="middleName" class="col-md-3 control-label">Middle Name</label>

                                <div class="col-md-8">
                                    <input name="middleName" type="text" class="form-control" id="middleName"
                                           placeholder="Middle Name"
                                           value="${profile.middleName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="col-md-3 control-label">Last Name</label>

                                <div class="col-md-8">
                                    <input name="lastName" type="text" class="form-control" id="lastName"
                                           placeholder="Last Name"
                                           value="${profile.lastName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="dateBirth" class="col-md-3 control-label">Birth Date</label>

                                <div class="col-md-8">
                                    <input name="dateBirth" type="text" class="form-control date" id="dateBirth"
                                           placeholder="Birth Date"
                                           value="<fmt:formatDate pattern=" dd.MM.yyyy"
                                value="${profile.birthDate.time}" />" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updPersonal" class="btn btn-primary">Update</a>
                                </div>
                            </div>
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
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div id="subjects"></div>

                            <div class="form-group">
                                <label for="totalPoints" class="col-md-5 control-label">Total:</label>

                                <div class="col-md-4">
                                    <input type="number" name="points" class="form-control" id="totalPoints"
                                           placeholder="0"
                                           pattern="\d{1,3}"
                                           value="${profile.points}" required readonly>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-9">
                                    <a id="updEducation" class="btn btn-primary">Update</a>
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
