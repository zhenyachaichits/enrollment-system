<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.header" var="divHeader"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.name" var="nameLabel"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.quota.free" var="freeQuotaLabel"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.quota.paid" var="paidQuotaLabel"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.terms" var="termsLabel"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.subjects" var="subjectsLabel"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.button.more" var="moreBtn"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.button.create" var="createBtn"/>
<fmt:message bundle="${loc}" key="locale.page.faculties.found" var="foundLabel"/>


<html>
<head>
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>


<div class="content">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">

            <div class="panel panel-default clear">
                <div class="panel-heading">
                    <p>${divHeader}</p>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <form action="management" name="addFaculty" method="post">
                            <input type="hidden" name="command" value="create-faculty">
                            <fieldset>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="facultyName">${nameLabel}</label>
                                    <div class="col-md-8">
                                        <input type="text" name="facultyName" id="facultyName" class="form-control"
                                               placeholder="${nameLabel}"
                                               required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="freeQuota" class="col-md-3 control-label">
                                        ${freeQuotaLabel}
                                    </label>

                                    <div class="col-md-8">
                                        <input type="number" name="freeQuota" min="1" max="999"
                                               class="form-control point"
                                               id="freeQuota" pattern="\d{1,3}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="freeQuota" class="col-md-3 control-label">
                                        ${paidQuotaLabel}
                                    </label>

                                    <div class="col-md-8">
                                        <input type="number" name="paidQuota" min="1" max="999"
                                               class="form-control point"
                                               id="paidQuota" pattern="\d{1,3}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="terms" class="col-md-3 control-label">${termsLabel}</label>

                                    <div class="col-md-8">
                                        <select id="terms" name="termsID" class="form-control select-dropdown">
                                            <c:forEach var="termsLabel" items="${termsList}">
                                                <option value="${termsLabel.id}">
                                                    <fmt:formatDate pattern="dd.MM.yyyy"
                                                                    value="${termsLabel.startDate.time}"/>-<fmt:formatDate
                                                        pattern="dd.MM.yyyy" value="${termsLabel.endDate.time}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="subjects" class="col-md-3 control-label">${subjectsLabel}</label>
                                    <div class="col-md-8">
                                        <select id="subjects" name="subjects" class="form-control" multiple>
                                            <c:forEach var="subject" items="${subjects}">
                                                <option value="${subject.id}">
                                                    <tr:transl>${subject.name}</tr:transl>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12 col-md-offset-8">
                                        <button class="btn btn-primary">${createBtn}</button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <c:if test="${fn:length(faculties) gt 0}">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-body">

                        <p class="lead text-muted">${foundLabel} ${fn:length(faculties)}</p>

                        <div class="table-responsive">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Terms</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="faculty" items="${faculties}">
                                    <form action="management" name="goFacultyDataForm" method="post">
                                        <input type="hidden" name="command" value="go-faculty-data">
                                        <tr>
                                            <td>
                                                <p class="centred">
                                                <tr:transl>${faculty.name}</p></tr:transl>
                                            </td>
                                            <td>
                                                <c:forEach var="termsLabel" items="${termsList}">
                                                    <c:if test="${faculty.termsId eq termsLabel.id}">
                                                        <p class="centred">
                                                            <fmt:formatDate pattern="dd.MM.yyyy"
                                                                            value="${termsLabel.startDate.time}"/>
                                                            -
                                                            <fmt:formatDate pattern="dd.MM.yyyy"
                                                                            value="${termsLabel.endDate.time}"/>
                                                        </p>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <button name="facultyID" value="${faculty.id}"
                                                        class="btn btn-primary">
                                                    ${moreBtn}
                                                </button>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>


<span id="errorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
      data-content="One or more records are incorrect" data-timeout="4000"
      data-snackbar-id="snackbar1454251274095"></span>
<span id="nameErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
      data-content="This faculty name already user" data-timeout="4000"
      data-snackbar-id="snackbar1454251274095"></span>
<span id="subjectsErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
      data-content="You should choose 3 subjects" data-timeout="4000"
      data-snackbar-id="snackbar1454251274095"></span>

<%@include file="included/js_list.jsp" %>
<script src="content\js\ajax\faculty-management.js"></script>


</body>
</html>
