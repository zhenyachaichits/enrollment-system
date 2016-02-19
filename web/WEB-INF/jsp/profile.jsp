<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.index.title" var="title"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>


<form class="form-horizontal" action="profile">
    <input type="hidden" name="command" value="update-profile">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-default clear">
                    <div class="panel-heading">Account Data</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="newEmail">Email</label>
                            <div class="col-md-8">
                                <input type="email" name="email" id="newEmail" class="form-control"
                                       placeholder="Email" value="${student.user.email}" required readonly>
                            </div>
                        </div>
                        <div class="form-group">
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
                                <a id="updAccount" class="btn btn-primary scroll-button">Update</a>
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
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="medal" id="none" value="none"
                                               checked="checked" readonly>
                                        None
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="medal" id="silver" value="silver">
                                        Silver
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <label>
                                        <input type="radio" name="medal" id="golden" value="golden">
                                        Golden
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="privileges" class="col-md-3 control-label">Privileges</label>

                            <div class="col-md-8">
                                    <textarea name="privileges" class="form-control" rows="3"
                                              id="privileges">${student.profile.privileges}</textarea>
                                <span class="help-block">Lalalala.</span>
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
                                       value="${student.profile.phone}" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address" class="col-md-3 control-label">Address</label>

                            <div class="col-md-8">
                                    <textarea name="address" class="form-control" rows="3" id="address"
                                              required>${student.profile.address}</textarea>
                                <span class="help-block">Lalalala.</span>
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
                                       value="${student.profile.passportId}" required>
                                <p class="help-block">You can find ID on the last page of your Passport </p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstName" class="col-md-3 control-label">First Name</label>

                            <div class="col-md-8">
                                <input name="firstName" type="text" class="form-control" id="firstName"
                                       placeholder="First Name"
                                       value="${student.profile.firstName}"required>
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
                                       value="${student.profile.birthDate}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 col-md-offset-9">
                                <a id="updPersonal" class="btn btn-primary scroll-button">Update</a>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="panel panel-default clear">
                    <div class="panel-heading">Education</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="faculty" class="col-md-3 control-label">Faculty</label>

                            <div class="col-md-8">
                                <select name="facultyID" id="faculty" class="form-control">
                                    <option disabled selected>Choose faculty</option>
                                    <c:forEach var="faculty" items="${faculties}">
                                        <option value="${faculty.id}">
                                            <tr:transl>${faculty.name}</tr:transl>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div id="subjects"></div>

                        <div class="form-group">
                            <label for="gpa" class="col-md-5 control-label">GPA</label>

                            <div class="col-md-4">
                                <input type="number" min="30" max="100" class="form-control point" id="gpa"
                                       placeholder="Points"
                                       pattern="\d{1,3}" required> </input>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="col-md-3">
                                    <label for="totalPoints" class=" control-label">Total:</label>
                                </div>
                                <div class="col-md-3">
                                    <input type="number" name="points" class="form-control" id="totalPoints"
                                           placeholder="0"
                                           pattern="\d{1,3}"
                                           value="${student.profile.points}" required readonly>
                                </div>
                                <a onclick="$('.scroll').moveUp();"
                                   class="btn btn-primary scroll-button">Previous</a>
                                <a id="toPrivileges" class="btn btn-primary scroll-button">Next</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</form>

<%@include file="included/js_list.jsp" %>

</body>
</html>