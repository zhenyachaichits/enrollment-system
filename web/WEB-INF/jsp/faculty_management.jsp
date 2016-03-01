<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.index.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.index.header" var="divHeader"/>
<fmt:message bundle="${loc}" key="locale.page.index.description" var="description"/>
<fmt:message bundle="${loc}" key="locale.page.index.button.statistics" var="statisticsBtn"/>

<fmt:message bundle="${loc}" key="locale.modal.signin.message.error" var="errorMessage"/>

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
                    <h4>Create new faculty</h4>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <form action="management" name="addFaculty" method="post">
                            <input type="hidden" name="command" value="create-faculty">
                            <fieldset>
                                <div class="form-group">
                                    <label class="col-md-3 control-label" for="name">Faculty Name</label>
                                    <div class="col-md-8">
                                        <input type="text" name="name" id="name" class="form-control"
                                               placeholder="Name"
                                               required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="freeQuota" class="col-md-5 control-label">
                                        Free Form Quota
                                    </label>

                                    <div class="col-md-4">
                                        <input type="number" name="freeQuota" min="1" max="999"
                                               class="form-control point"
                                               id="freeQuota"
                                               placeholder="Points" pattern="\d{1,3}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="freeQuota" class="col-md-5 control-label">
                                        Paid Form Quota
                                    </label>

                                    <div class="col-md-4">
                                        <input type="number" name="freeQuota" min="1" max="999"
                                               class="form-control point"
                                               id="paidQuota"
                                               placeholder="Points" pattern="\d{1,3}">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="terms" class="col-md-3 control-label">Terms</label>

                                    <div class="col-md-8">
                                        <select id="terms" name="terms" class="form-control">
                                            <c:forEach var="terms" items="${termsList}">
                                                <option value="${terms.id}">
                                                    <fmt:formatDate pattern="dd.MM.yyyy"
                                                                    value="${terms.startDate}"/>
                                                    -
                                                    <fmt:formatDate pattern="dd.MM.yyyy"
                                                                    value="${terms.endDate}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="subjects" class="col-md-3 control-label">Subjects</label>

                                    <div class="col-md-8">
                                        <select id="subjects" name="subjects" multiple="" class="form-control">
                                            <c:forEach var="subject" items="subjects">
                                                <option value="${subject.id}">${subject.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12 col-md-offset-8">
                                        <button class="btn btn-primary">Add</button>
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

                        <p class="lead text-muted">Faculties found: ${fn:length(faculties)}</p>

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
                                    <form action="management" name="goApplyForm" method="post">
                                        <input type="hidden" name="command" value="go-faculty-data">
                                        <tr>
                                            <td>
                                                <p class="centred">
                                                <tr:transl>${faculty.name}</p></tr:transl>
                                            </td>
                                            <td>
                                                <c:forEach var="terms" items="termsList">
                                                    <c:if test="${faculty.termsId eq terms.id}">
                                                        <p class="centred">
                                                            <fmt:formatDate pattern="dd.MM.yyyy"
                                                                            value="${terms.startDate}"/>
                                                            -
                                                            <fmt:formatDate pattern="dd.MM.yyyy"
                                                                            value="${terms.endDate}"/>
                                                        </p>
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <button name="userID" value="${faculty.id}"
                                                        class="btn btn-primary">
                                                    More
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
<span id="subjectsErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
      data-content="You should choose 3 subjects" data-timeout="4000"
      data-snackbar-id="snackbar1454251274095"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/ajax/faculty_management.js"></script>


</body>
</html>
