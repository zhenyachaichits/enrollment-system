<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 02.02.16
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="locale.data.current" var="lang"/>

<script src="//code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="content/js/ripples.min.js"></script>
<script src="content/js/dropdown.js"></script>
<script src="content/js/material.min.js"></script>
<script src="content/js/moment.js"></script>
<script src="content/js/bootstrap-material-datetimepicker.js"></script>
<script src="content/js/maskedinput.min.js"></script>
<script src="content/js/snackbar.min.js"></script>

<script src="content/js/ajax/authenticate.js"></script>
<script src="content/js/locale.js"></script>

<script>
    $.material.init();

    $('.date').bootstrapMaterialDatePicker({format: 'DD.MM.YYYY', lang: '${lang}', weekStart: 1, time: false});
    $(".select-dropdown").dropdown({"autoinit": "select"});

    $('.date-end').bootstrapMaterialDatePicker({format: 'DD.MM.YYYY', lang: '${lang}', weekStart: 1, time: false});
    $('.date-start').bootstrapMaterialDatePicker({format: 'DD.MM.YYYY', lang: '${lang}', weekStart: 1, time: false}).on('change', function(e, date)
    {
        $('#date-end').bootstrapMaterialDatePicker('setMinDate', date);
    });

    $(function ($) {
        $(".phone").mask("+999 (99) 999-99-99", {placeholder: "_"});
        $(".score").mask("999", {placeholder: ""});
        $("#passportID").mask("aa9999999", {placeholder: ""});
    });

    var allInputs = $(":input");
    allInputs.each(function() {
        $(this).val($.trim($(this).val()));
    });

    var li = document.getElementsByTagName('li');

    for(var i = 0; i < li.length; i++) {
        li[i].innerHTML = $.trim(li[i].innerHTML);
    }

</script>
