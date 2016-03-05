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
                    Create new subject
                </div>
                <div class="panel-body">
                    <div class="row">
                        <form action="management" name="addUser" method="post">
                            <input type="hidden" name="command" value="create-terms">
                            <fieldset>

                                <div class="form-group">
                                    <label for="startDate" class="col-md-3 control-label">Start Date</label>

                                    <div class="col-md-8">
                                        <input name="startDate" type="text" class="form-control date-start"
                                               id="startDate" placeholder="Start Date"
                                               required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="endDate" class="col-md-3 control-label">End Date</label>

                                    <div class="col-md-8">
                                        <input name="endDate" type="text" class="form-control date-end"
                                               id="endDate" placeholder="End Date"
                                               required>
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

    <c:if test="${fn:length(termsList) gt 0}">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-body">

                        <p class="lead text-muted">Records found: ${fn:length(termsList)}</p>

                        <div class="table-responsive">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="terms" items="${termsList}">
                                    <tr>
                                        <form action="management" id="updateTerms" name="updateSubject" method="post">
                                            <input type="hidden" name="command" value="update-terms">
                                            <input type="hidden" name="termsID" value="${terms.id}">
                                            <td>
                                                <div class="form-group" style="margin: 7px 0 0 0">
                                                    <input name="startDate" type="text" class="form-control date-start"
                                                           placeholder="Birth Date"
                                                           value=" <fmt:formatDate pattern="dd.MM.yyyy"
                                                                    value="${terms.startDate.time}"/>" required>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="form-group" style="margin: 7px 0 0 0">
                                                    <input name="endDate" type="text" class="form-control date-end"
                                                           placeholder="Birth Date"
                                                           value=" <fmt:formatDate pattern="dd.MM.yyyy"
                                                                    value="${terms.endDate.time}"/>" required>
                                                </div>
                                            </td>

                                        </form>
                                        <td>
                                            <a href="javascript:void(0)" onclick="$('form#updateTerms').submit();"
                                               class="btn btn-primary">
                                                Save
                                            </a>
                                        </td>
                                        <td>
                                            <form action="management" id="deleteTerms" name="deleteSubject" method="post">
                                                <input type="hidden" name="command" value="delete-terms">
                                                <input type="hidden" name="termsID" value="${terms.id}">
                                            </form>
                                            <a href="javascript:void(0)" onclick="$('form#deleteTerms').submit();"
                                               class="btn btn-danger">
                                                Delete
                                            </a>
                                        </td>
                                    </tr>
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
    <span id="emailErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="Account with such Email already exists" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/user-management.js"></script>
<script src="content/js/ajax/singup.js"></script>


</body>
</html>