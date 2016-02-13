<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 06.02.16
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>

    <%@include file="included/css_list.jsp" %>

</head>
<body>
<br/>

<div class="container">
    <div class="jumbotron">
        <h1>Oops.. Something is wrong</h1>

        <p>We're sorry... The page you are looking for cannot be found.</p>

        <form action="../../index.jsp" method="post">
            <input type="hidden" name="command" value="go-home">
            <p>
                <button type="submit" class="btn btn-primary btn-lg">Back to Home Page</button>
            </p>
        </form>
    </div>
</div>

<%@include file="included/js_list.jsp" %>
<script src="content/js/locale.js"></script>

</body>
</html>