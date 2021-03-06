<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.title" var="title"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.header" var="divHeader"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.name" var="nameLabel"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.points" var="pointsLabel"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.button.create" var="createBtn"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.button.save" var="saveBtn"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.button.delete" var="deleteBtn"/>
<fmt:message bundle="${loc}" key="locale.page.subjects.found" var="foundLabel"/>

<fmt:message bundle="${loc}" key="locale.message.subjectexist" var="nameMessage"/>
<fmt:message bundle="${loc}" key="locale.message.wrongfield" var="fieldMessage"/>

<html>
<head>
    <title>${title}</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>

<jsp:include page="included/navbar.jsp"/>

<div class="content">
    <div class="row">

        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-body">
                    <p class="lead text-muted">${foundLabel} ${fn:length(subjects)}</p>

                    <c:if test="${fn:length(subjects) gt 0}">
                        <div class="table-responsive">
                            <table class="table table-striped table-hover ">
                                <thead>
                                <tr>
                                    <th>${nameLabel}</th>
                                    <th>${pointsLabel}</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="subject" items="${subjects}">
                                    <tr>
                                        <form action="management" id="updateSub${subject.id}" method="post">
                                            <input type="hidden" name="command" value="update-subject">
                                            <input type="hidden" name="subjectID" value="${subject.id}">
                                            <td>
                                                <div class="form-group" style="margin: 7px 0 0 0">
                                                    <input class="form-control point subject-name saved" name="subjectName"
                                                           value="${subject.name}"/>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="form-group" style="margin: 7px 0 0 0">
                                                    <input type="number" min="1" max="80" class="form-control point"
                                                           name="minPoint" value="${subject.minPoint}"/>
                                                </div>
                                            </td>

                                        </form>
                                        <td>
                                            <a href="javascript:void(0)"
                                               onclick="$('form#updateSub${subject.id}').submit();"
                                               class="btn btn-primary">
                                                    ${saveBtn}
                                            </a>
                                        </td>
                                        <td>
                                            <form action="management" id="deleteSub${subject.id}" method="post">
                                                <input type="hidden" name="command" value="delete-subject">
                                                <input type="hidden" name="subjectID" value="${subject.id}">
                                            </form>
                                            <a href="javascript:void(0)"
                                               onclick="$('form#deleteSub${subject.id}').submit();"
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
        <div class="modal-dialog modal-md" style=" margin-top: 140px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close btn btn-primary btn-fab small"
                            data-dismiss="modal" aria-hidden="true"><i class="material-icons">close</i></button>
                    <h4>${divHeader}</h4>
                </div>
                <div class="modal-body">
                    <form action="management" name="addUser" id="addUser" method="post">
                        <input type="hidden" name="command" value="create-subject">
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="subjectName">${nameLabel}</label>
                                <div class="col-md-8">
                                    <input type="text" name="subjectName" id="subjectName" class="form-control subject-name"
                                           placeholder="${nameLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="minPoints" class="col-md-5 control-label">
                                    ${pointsLabel}
                                </label>

                                <div class="col-md-4">
                                    <input type="number" name="minPoint" min="1" max="80"
                                           class="form-control point"
                                           id="minPoints"
                                           placeholder="${pointsLabel}" value="1" pattern="\d{1,3}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 col-md-offset-7">
                                    <a class="btn btn-primary" id="addSubject">${createBtn}</a>
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
          data-content="${fieldMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274095"></span>
    <span id="nameErrorMessage" class="btn btn-material-deeppurple" data-toggle="snackbar"
          data-content="${nameMessage}" data-timeout="4000"
          data-snackbar-id="snackbar1454251274096"></span>

<%@include file="included/js_list.jsp" %>
<script src="content/js/ajax/subject-management.js"></script>
<script src="content/js/ajax/singup.js"></script>


</body>
</html>
