<?xml version="1.0" encoding="UTF-8"?>

<%@page contentType="application/xml" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tr" uri="http://epam.com/project/university/transliterate"%>

<data>
    <c:forEach var="subject" items="${subjects}" varStatus="loopCounter" >

        <div class="form-group" id="subject${loopCounter.count}Group" style="display: none">
            <label for="subject${loopCounter.count}" class="col-md-5 control-label">
            <tr:transl>${subject.name}</tr:transl>
            </label>

            <div class="col-md-4">
                <input type="number" min="${subject.minPoint}" max="100" class="form-control point" id="subject${loopCounter.count}"
                       placeholder="Points"
                       pattern="\d{1,3}"> </input>
                <p class="help-block">Minimal point for this subjest is: ${subject.minPoint} </p>
            </div>
        </div>
    </c:forEach>
</data>