<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 02.02.16
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tr" uri="http://epam.com/project/university/transliterate"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <%@include file="included/css_list.jsp" %>
</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="scroll">
    <form class="form-horizontal" action="../../index.jsp">
        <input type="hidden" name="command" value="sign-up">
        <section id="account">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well bs-component">
                            <legend>Account Data</legend>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="newEmail">Email</label>
                                <div class="col-md-8">
                                    <input type="email" name="email" id="newEmail" class="form-control"
                                           placeholder="Email"
                                           required>
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
                                    <a id="toPersonal" class="btn btn-primary scroll-button">Next</a>
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
                            <legend>Personal Data</legend>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="passportID">Passport ID</label>
                                <div class="col-md-8">
                                    <input name="passportID" type="text" id="passportID" class="form-control"
                                           placeholder="Passport ID"
                                           required>
                                    <p class="help-block">You can find ID on the last page of your Passport </p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstName" class="col-md-3 control-label">First Name</label>

                                <div class="col-md-8">
                                    <input name="firstName" type="text" class="form-control" id="firstName"
                                           placeholder="First Name"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="middleName" class="col-md-3 control-label">Middle Name</label>

                                <div class="col-md-8">
                                    <input name="middleName" type="text" class="form-control" id="middleName"
                                           placeholder="Middle Name"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="col-md-3 control-label">Last Name</label>

                                <div class="col-md-8">
                                    <input name="lastName" type="text" class="form-control" id="lastName"
                                           placeholder="Last Name"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="dateBirth" class="col-md-3 control-label">Birth Date</label>

                                <div class="col-md-8">
                                    <input name="dateBirth" type="text" class="form-control date" id="dateBirth"
                                           placeholder="Birth Date"
                                           required>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-6">
                                    <a onclick="$('.scroll').moveUp();"
                                       class="btn btn-primary scroll-button">Previous</a>
                                    <a id="toEducation" class="btn btn-primary scroll-button">Next</a>
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
                            <legend>Education</legend>

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

                            <div id="subjects"> </div>

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
                                               pattern="\d{1,3}" required readonly>
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
        </section>

        <section id="privilegesSection">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="well bs-component">
                            <legend>Privileges</legend>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Medal</label>

                                <div class="col-md-8">
                                    <div class="radio radio-primary">
                                        <label>
                                            <input type="radio" name="medal" id="none" value="none"
                                                   checked="checked">
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
                                              id="privileges"></textarea>
                                    <span class="help-block">Lalalala.</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-6">
                                    <a onclick="$('.scroll').moveUp();"
                                       class="btn btn-primary scroll-button">Previous</a>
                                    <a id="toContact" class="btn btn-primary scroll-button">Next</a>
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
                            <legend>Contact Info</legend>
                            <div class="form-group">
                                <label for="phone" class="col-md-3 control-label">Phone Number</label>

                                <div class="col-md-8">
                                    <input name="phone" type="text" class="form-control phone" id="phone"
                                           placeholder="Phone Number"
                                           required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="address" class="col-md-3 control-label">Address</label>

                                <div class="col-md-8">
                                    <textarea name="address" class="form-control" rows="3" id="address"
                                              required></textarea>
                                    <span class="help-block">Lalalala.</span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-6">
                                    <a onclick="$('.scroll').moveUp();"
                                       class="btn btn-primary scroll-button">Previous</a>
                                    <button id="confirm" type="submit" class="btn btn-raised btn-primary">Confirm
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
          data-content="One or more records are incorrect" data-timeout="4000"
          data-snackbar-id="snackbar1454251274095"></span>
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="Account with such Email already exists" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>
    <span id="passportErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="Account with such Passport ID already exists" data-timeout="4000"
          data-snackbar-id="snackbar1454251274097"></span>

</div>

<%@include file="included/js_list.jsp" %>
<script src="content/js/ajax/singup-onepage.js"></script>
<script src="content/js/locale.js"></script>

</body>
</html>