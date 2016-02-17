<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 02.02.16
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.navigation.brand" var="brand"/>
<fmt:message bundle="${loc}" key="locale.navigation.statistics" var="statistics"/>
<fmt:message bundle="${loc}" key="locale.navigation.signin" var="signIn"/>
<fmt:message bundle="${loc}" key="locale.navigation.pofile" var="profile"/>
<fmt:message bundle="${loc}" key="locale.navigation.logout" var="logOut"/>
<fmt:message bundle="${loc}" key="locale.navigation.language" var="language"/>
<fmt:message bundle="${loc}" key="locale.navigation.language.english" var="englishLanguage"/>
<fmt:message bundle="${loc}" key="locale.navigation.language.russian" var="russianLanguage"/>

<fmt:message bundle="${loc}" key="locale.modal.signin.legend" var="legend"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.label.email" var="emailLabel"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.label.password" var="passwordLabel"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.label.remember" var="rememberLabel"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.button.signin" var="signInButton"/>
<fmt:message bundle="${loc}" key="locale.modal.signin.button.signup" var="signUpButton"/>

<div class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <form name="home" action="../../../index.jsp" method="post">
                <input type="hidden" name="command" value="go-home">
                <a href="javascript:home.submit()" class="navbar-brand">${brand}</a>
            </form>
        </div>
        <div class="navbar-collapse collapse navbar-responsive-collapse">

            <form hidden="hidden" name="toStatistics" action="statistics" method="post">
                <input type="hidden" name="command" value="go-statistics">
            </form>
            <ul class="nav navbar-nav">
                <li><a href="javascript:toStatistics.submit();">${statistics}</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <c:if test="${sessionScope.userEmail != null}">
                    <li class="dropdown">
                        <a href="" data-target="#" class="dropdown-toggle"
                           data-toggle="dropdown">${sessionScope.userEmail}
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">

                            <li><a href="javascript:void(0)">${profile}</a></li>
                            <li class="divider"></li>
                            <form hidden="hidden" name="logout" action="" method="post">
                                <input type="hidden" name="command" value="log-out">
                            </form>
                            <li>
                                <a href="javascript:logout.submit();">${logOut}</a>
                            </li>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${sessionScope.userEmail == null}">
                    <li><a href="javascript:void(0)" id="signInLink" data-toggle="modal" data-target="#signIn">${signIn}</a></li>
                </c:if>

                <li class="dropdown">
                    <form id="localeForm" hidden="hidden" action="locale" method="get">
                        <input type="hidden" name="command" value="set-locale">
                        <input id="currentLocale" type="hidden" value="${sessionScope.locale}">
                        <input id="localeValue" type="hidden" name="locale" value="">
                    </form>
                    <a href="bootstrap-elements.html" class="dropdown-toggle" data-toggle="dropdown">${language}
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

<c:if test="${sessionScope.userEmail == null}">
    <div id="signIn" class="modal fade" tabindex="-1" style="display: none;">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h2 class="modal-title">${legend}</h2>
                </div>
                <div class="modal-body">
                    <form id="signInForm" action="" class="form-horizontal" method="post">
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
                                <c:if test="${sessionScope.locale eq 'en'}"><div class="col-md-offset-4"></c:if>
                                <c:if test="${sessionScope.locale ne 'en'}"><div class="col-md-offset-3"></c:if>
                                    <a href="javascript:signup.submit();" class="btn btn-primary">${signUpButton}</a>
                                    <a href="javascript:void(0)" id="submit"
                                       class="btn btn-raised btn-primary">${signInButton}</a>
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