<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 02.02.16
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.navigation.brand" var="brandLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.statistics" var="statisticsLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.signin" var="signInLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.pofile" var="profileLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.application" var="applicationLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.search" var="searchLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.users" var="usersLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.faculties" var="facultiesLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.subjects" var="subjectsLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.terms" var="termsLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.logout" var="logOutLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.language" var="languageLabel"/>
<fmt:message bundle="${loc}" key="locale.navigation.language.english" var="englishLanguage"/>
<fmt:message bundle="${loc}" key="locale.navigation.language.russian" var="russianLanguage"/>

<fmt:message bundle="${loc}" key="locale.modal.signin.legend" var="legend"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.label.email" var="emailLabel"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.label.password" var="passwordLabel"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.label.remember" var="rememberLabel"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.button.signin" var="signInButton"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.button.signup" var="signUpButton"/>

<c:set var="req" value="${pageContext.request}"/>
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}"/>

<header>  <div class="loader" aria-busy="true" aria-label="Loading, please wait." role="progressbar"></div></header>

<div class="navbar navbar-default"  style="box-shadow: 0 0 4px rgba(0,0,0,.14),0 4px 8px rgba(0,0,0,.28);
 position: fixed; top: 0; z-index: 1000; width: 100%;">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <form name="home" action="home" method="post">
                <input type="hidden" name="command" value="go-home">
                <a href="javascript:home.submit()" class="navbar-brand">${brandLabel}</a>
            </form>
        </div>
        <div class="navbar-collapse collapse navbar-responsive-collapse">

            <form hidden="hidden" name="toStatistics" action="statistics" method="post">
                <input type="hidden" name="command" value="go-statistics">
            </form>
            <ul class="nav navbar-nav">
                <li><a href="javascript:toStatistics.submit();">${statisticsLabel}</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <c:if test="${sessionScope.userEmail != null}">
                    <li class="dropdown">
                        <a href="" data-target="#" class="dropdown-toggle"
                           data-toggle="dropdown">${sessionScope.userEmail}
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">

                            <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                                <form hidden="hidden" name="goUserManagement" action="management" method="post">
                                    <input type="hidden" name="command" value="go-user-management">
                                </form>
                                <li><a href="javascript:goUserManagement.submit();">${usersLabel}</a></li>
                                <li class="divider"></li>
                            </c:if>

                            <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                                <form hidden="hidden" name="goFacultyManagement" action="management" method="post">
                                    <input type="hidden" name="command" value="go-faculty-management">
                                </form>
                                <li><a href="javascript:goFacultyManagement.submit();">${facultiesLabel}</a></li>
                                <li class="divider"></li>
                            </c:if>

                            <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                                <form hidden="hidden" name="goSubjectManagement" action="management" method="post">
                                    <input type="hidden" name="command" value="go-subject-management">
                                </form>
                                <li><a href="javascript:goSubjectManagement.submit();">${subjectsLabel}</a></li>
                                <li class="divider"></li>
                            </c:if>

                            <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                                <form hidden="hidden" name="goTermsManagement" action="management" method="post">
                                    <input type="hidden" name="command" value="go-terms-management">
                                </form>
                                <li><a href="javascript:goTermsManagement.submit();">${termsLabel}</a></li>
                                <li class="divider"></li>
                            </c:if>

                            <c:if test="${sessionScope.userRole eq 'STUDENT'}">
                                <form hidden="hidden" name="goProfile" action="profile" method="post">
                                    <input type="hidden" name="command" value="go-profile">
                                </form>
                                <li><a href="javascript:goProfile.submit();">${profileLabel}</a></li>
                                <li class="divider"></li>
                            </c:if>

                            <c:if test="${sessionScope.userRole eq 'STUDENT'}">
                                <form hidden="hidden" name="goApplicationData" action="profile" method="post">
                                    <input type="hidden" name="command" value="go-application-data">
                                </form>
                                <li><a href="javascript:goApplicationData.submit();">${applicationLabel}</a></li>
                                <li class="divider"></li>
                            </c:if>

                            <c:if test="${sessionScope.userRole eq 'SUPPORT'}">
                                <form hidden="hidden" name="goProfile" action="search" method="post">
                                    <input type="hidden" name="command" value="go-support-search">
                                </form>
                                <li><a href="javascript:goProfile.submit();">${searchLabel}</a></li>
                                <li class="divider"></li>
                            </c:if>

                            <form hidden="hidden" name="logout" action="home" method="post">
                                <input type="hidden" name="command" value="log-out">
                            </form>
                            <li>
                                <a href="javascript:logout.submit();">${logOutLabel}</a>
                            </li>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${sessionScope.userEmail == null}">
                    <li><a href="javascript:void(0)" onclick="authenticateCookies()" id="signInLink" data-toggle="modal"
                           data-target="#signIn" style="outline: none;">${signInLabel}</a></li>
                </c:if>

                <li class="dropdown">
                    <form id="localeForm" hidden="hidden" action="locale"
                          method="post">
                        <input type="hidden" name="command" value="set-locale">
                        <input id="currentLocale" type="hidden" value="${sessionScope.locale}">
                        <input id="localeValue" type="hidden" name="locale" value="">
                    </form>
                    <a href="bootstrap-elements.html" class="dropdown-toggle" data-toggle="dropdown">${languageLabel}
                        <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a id="enBtn" href="javascript:void(0)"><p id="enLabel">${englishLanguage}</p></a></li>
                        <li><a id="ruBtn" href="javascript:void(0)"><p id="ruLabel">${russianLanguage}</p></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="filler"></div>

<c:if test="${sessionScope.userEmail == null}">
    <div id="signIn" class="modal fade in" tabindex="-1" style="display: none;">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close btn btn-primary btn-fab small"
                            data-dismiss="modal" aria-hidden="true"><i class="material-icons">close</i></button>
                    <h4>${legend}</h4>
                </div>
                <div class="modal-body centred">
                    <form id="signInForm" action="home" class="form-horizontal" method="post">
                        <input type="hidden" name="command" value="authenticate">
                        <fieldset>
                            <div class="form-group label-floating">
                                <div class="col-md-12">
                                    <label class="control-label" for="email">${emailLabel}</label>
                                    <input class="form-control" name="email" id="email" type="email">
                                </div>
                            </div>
                            <div class="form-group label-floating">
                                <div class="col-md-12">
                                    <label class="control-label" for="password">${passwordLabel}</label>
                                    <input class="form-control" name="password" id="password" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <div class="checkbox">
                                        <label>
                                            <input id="remember" name="remember" type="checkbox">${rememberLabel}
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <c:if test="${sessionScope.locale eq 'en'}">
                                <div class="col-md-offset-4"></c:if>
                                    <c:if test="${sessionScope.locale ne 'en'}">
                                    <div class="col-md-offset-3"></c:if>
                                        <a href="javascript:signup.submit();"
                                           class="btn btn-primary">${signUpButton}</a>
                                        <a href="javascript:void(0)" id="submit"
                                           class="btn btn-raised btn-primary">${signInButton}</a>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>

                    <form id="goSignUpForm" hidden="hidden" name="signup" action="signup" method="post">
                        <input type="hidden" name="command" value="go-signup">
                    </form>

                </div>
            </div>
        </div>
    </div>
</c:if>