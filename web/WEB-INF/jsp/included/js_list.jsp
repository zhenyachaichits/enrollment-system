<%--
  Created by IntelliJ IDEA.
  User: Zheny Chaichits
  Date: 02.02.16
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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


    $('.date').bootstrapMaterialDatePicker({format: 'DD.MM.YYYY', lang: 'en', weekStart: 1, time: false});
    $("select").dropdown({"autoinit": "select"});

    $(function ($) {
        $(".phone").mask("+999 (99) 999-99-99", {placeholder: "_"});
        $(".score").mask("999", {placeholder: ""});
        $("#passportID").mask("aa9999999", {placeholder: ""});
    });

</script>
