<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.terms.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.terms.header" var="divHeader"/>
<fmt:message bundle="${loc}" key="locale.page.terms.start" var="startLabel"/>
<fmt:message bundle="${loc}" key="locale.page.terms.end" var="endLabel"/>
<fmt:message bundle="${loc}" key="locale.page.terms.start" var="startLabel"/>
<fmt:message bundle="${loc}" key="locale.page.terms.found" var="foundLabel"/>

<fmt:message bundle="${loc}" key="locale.page.terms.button.create" var="createBtn"/>
<fmt:message bundle="${loc}" key="locale.page.terms.button.save" var="saveBtn"/>
<fmt:message bundle="${loc}" key="locale.page.terms.button.delete" var="deleteBtn"/>

<html>
<head>
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="content">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <p class="lead text-muted">${foundLabel} ${fn:length(termsList)}</p>
                    <c:if test="${fn:length(termsList) gt 0}">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>${startLabel}</th>
                                    <th>${endLabel}</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="terms" items="${termsList}">
                                    <tr>
                                        <form action="management" id="updateTerms${terms.id}" method="post">
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
                                            <a href="javascript:void(0)"
                                               onclick="$('form#updateTerms${terms.id}').submit();"
                                               class="btn btn-primary">
                                                    ${saveBtn}
                                            </a>
                                        </td>
                                        <td>
                                            <form action="management" id="deleteTerms${terms.id}" method="post">
                                                <input type="hidden" name="command" value="delete-terms">
                                                <input type="hidden" name="termsID" value="${terms.id}">
                                            </form>
                                            <a href="javascript:void(0)"
                                               onclick="$('form#deleteTerms${terms.id}').submit();"
                                               class="btn btn-danger" style="color: #d23f31;">
                                                    ${deleteBtn}
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>


    <div id="addFaculty" class="modal fade" tabindex="-1" style="display: none;">
        <div class="modal-dialog modal-md" style=" margin-top: 120px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close btn btn-primary btn-fab small"
                            data-dismiss="modal" aria-hidden="true"><i class="material-icons">close</i></button>
                    <h4>${divHeader}</h4>
                </div>
                <div class="modal-body">
                    <form action="management" name="addUser" method="post">
                        <input type="hidden" name="command" value="create-terms">
                        <fieldset>

                            <div class="form-group">
                                <label for="startDate" class="col-md-3 control-label">${startLabel}</label>

                                <div class="col-md-8">
                                    <input name="startDate" type="text" class="form-control date-start"
                                           id="startDate" placeholder="${startLabel}"
                                           required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="endDate" class="col-md-3 control-label">${endLabel}</label>

                                <div class="col-md-8">
                                    <input name="endDate" type="text" class="form-control date-end"
                                           id="endDate" placeholder="${endLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-7">
                                    <button class="btn btn-primary">${createBtn}</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <button data-toggle="modal" data-target="#addFaculty" href="javascript:void(0)"
            style="position: fixed;
                        box-shadow: 0 0 4px rgba(0,0,0,.14),0 4px 8px rgba(0,0,0,.28);
                        z-index: 25;
                        bottom: 25px;
                        right: 25px;
                        background-color: #d23f31;
                        height: 56px;
                        width: 56px;
                        outline: none;"
            class="btn btn-danger btn-fab rounded-btn"><i class="material-icons">add</i></button>

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
