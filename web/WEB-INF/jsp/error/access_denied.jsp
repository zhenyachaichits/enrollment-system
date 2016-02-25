<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 22.02.2016
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>

    <%@include file="../included/css_list.jsp" %>

</head>
<body>
<br/>

<div class="container">
    <div class="jumbotron">
        <h1>Hey! Don't try to deceive me.</h1>

        <p>This account can not see this page.</p>

        <form action="home" method="post">
            <input type="hidden" name="command" value="go-home">
            <p>
                <button type="submit" class="btn btn-primary btn-lg">Back to Home Page</button>
            </p>
        </form>
    </div>
</div>

<%@include file="../included/js_list.jsp" %>
<script src="content/js/locale.js"></script>

</body>
</html>