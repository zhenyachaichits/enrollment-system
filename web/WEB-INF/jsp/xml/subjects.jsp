<?xml version="1.0" encoding="UTF-8"?>

<%@page contentType="application/xml" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tr" uri="http://epam.com/project/university/transliterate" %>

<data>
<c:forEach var="subject" items="${subjects}" varStatus="loopCounter">
    <subject>
        <name><tr:transl>${subject.name}</tr:transl></name>
        <minPoint>${subject.minPoint}</minPoint>
    </subject>
</c:forEach>
</data>
