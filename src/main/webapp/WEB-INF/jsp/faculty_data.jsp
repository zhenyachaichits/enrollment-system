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


<div class="container">
    <div class="well col-md-6 col-md-offset-3" id="confirmForm">
        <div class="col-md-10 col-md-offset-1">
            <div class="list-group">
                <form action="management" name="updateFaculty" method="post">
                    <input type="hidden" name="command" value="update-faculty-data">
                    <input type="hidden" name="facultyID" value="${faculty.id}">
                    <div class="list-group-item">
                        <div class="row-action-primary">
                            <i style="font-size:36px" class="material-icons">location_city</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">Name</h4>
                            <p class="list-group-item-text">
                            <div class="form-group" style="margin: 7px 0 0 0">
                                <div class="col-md-10">
                                    <input type="text" name="facultyName" class="form-control" id="facultyName"
                                           value="${faculty.name}" placeholder="Faculty Name" required>
                                </div>
                            </div>
                            </p>
                        </div>
                    </div>
                    <div class="list-group-separator"></div>
                    <div class="list-group-item" style="overflow: visible;">
                        <div class="row-action-primary">
                            <i style="font-size:36px" class="material-icons">date_range</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">Terms</h4>
                            <p class="list-group-item-text">
                            <div class="form-group" style="margin: 7px 0 0 0">
                                <div class="col-md-10">
                                    <select id="terms" name="termsID" class="form-control select-dropdown">
                                        <c:forEach var="termsLabel" items="${termsList}">
                                            <option value="${termsLabel.id}"
                                                    <c:if test="${termsLabel.id eq faculty.termsId}"> selected </c:if> >
                                                <fmt:formatDate pattern="dd.MM.yyyy"
                                                                value="${termsLabel.startDate.time}"/>-<fmt:formatDate
                                                    pattern="dd.MM.yyyy" value="${termsLabel.endDate.time}"/>
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            </p>
                        </div>
                    </div>
                    <div class="list-group-separator"></div>
                    <div class="list-group-item">
                        <div class="row-action-primary">
                            <i style="font-size:36px" class="material-icons">compare_arrows</i>
                        </div>
                        <div class="row-content">
                            <h4 class="list-group-item-heading">Quota</h4>
                            <p class="list-group-item-text">
                            <div class="row">
                                <div class="col-md-10">
                                    <div class="form-group label-floating" style="margin: 7px 0 0 0">
                                        <label class="control-label" for="freeQuota">Free Quota</label>

                                        <input type="number" name="freeQuota" class="form-control" id="freeQuota"
                                               value="${faculty.freeQuota}" min="1" max="999" required>
                                    </div>
                                    <div class="form-group label-floating" style="margin: 7px 0 0 0">
                                        <label class="control-label col-md-offset-6" for="paidQuota">Paid Quota</label>
                                        <input type="number" name="paidQuota" class="form-control" id="paidQuota"
                                               value="${faculty.paidQuota}" min="1" max="999" required>
                                    </div>
                                </div>
                            </div>
                            </p>
                        </div>
                    </div>
                </form>
                <div class="form-group">
                    <div class="col-md-12 col-md-offset-6">
                        <form name="deleteFaculty" action="management" method="post" hidden>
                            <input type="hidden" name="command" value="delete-faculty">
                            <input type="hidden" name="facultyID" value="${faculty.id}">
                        </form>
                        <a href="javascript:deleteFaculty.submit()" class="btn btn-raised btn-primary"
                           style="background-color: #bd5050;">Delete</a>
                        <a href="javascript:updateFaculty.submit()" class="btn btn-raised btn-primary">Save</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="container">
    <div class="col-md-6 col-md-offset-3 clear">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Action</h3>
            </div>
            <div class="panel-body">
                <div class="horizontal-center">
                    <div class="form-group" style="margin: 7px 0 0 0">
                        <div class="col-md-6 col-md-offset-3">
                            <input type="text" class="form-control"
                                   value="${faculty.freePoint}" placeholder="Email" readonly>
                            <input type="text" class="form-control"
                                   value="${faculty.paidPoint}" placeholder="Email" readonly>
                        </div>
                    </div>

                    <form action="management" method="post">
                        <input type="hidden" name="command" value="update-faculty-points">
                        <input type="hidden" name="facultyID" value="${faculty.id}">
                        <input type="hidden" name="freeQuota" value="${faculty.freeQuota}">
                        <input type="hidden" name="paidQuota" value="${faculty.paidQuota}">
                        <button class="btn btn-raised btn-default">Update points</button>
                    </form>
                    <form action="management" method="post">
                        <input type="hidden" name="command" value="confirm-faculty-applications">
                        <input type="hidden" name="facultyID" value="${faculty.id}">
                        <input type="hidden" name="freeQuota" value="${faculty.freeQuota}">
                        <input type="hidden" name="paidQuota" value="${faculty.paidQuota}">
                        <button class="btn btn-raised btn-default">Apply</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
