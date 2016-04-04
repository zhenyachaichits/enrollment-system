/**
 * Created by Zheny Chaichits on 01.03.2016.
 */

var isNameOk = false;
var isSubjectsOk = false;

$("#subjects").focusout(function () {
    if ($("select option:selected").length != 4) {
        $('#subjectsErrorMessage').snackbar('show');
        $("#subjects").focus();
        isSubjectsOk = false;
    } else {
        isSubjectsOk = true;
    }
});

var currentFacultyName = $('#facultyName').val();
$('#facultyName').focusout(function () {

    var facultyName = $('#facultyName').val();
    var re = /.{2,}/;
    var m;

    if ((m = re.exec(facultyName)) !== null && facultyName !== currentFacultyName) {
        $(".loader").attr("aria-busy", "true");
        $.post(
            '/action',
            {
                command: 'check-faculty-name',
                facultyName: facultyName
            },
            function (resp) {
                status = $.trim(resp.toLowerCase());
                if (status === "positive") {
                    $('#nameErrorMessage').snackbar('show');
                    $('#facultyName').focus();
                    isNameOk = false;
                } else {
                    isNameOk = true;
                }
                $(".loader").attr("aria-busy", "false");
            })
            .fail(function () {
                alert("Request failed.");
                $(".loader").attr("aria-busy", "false");
            });
    }
});

$('#createFaculty').click(function () {
    if(isNameOk && isSubjectsOk) {
        $('form#createFacultyForm').submit();
    } else {
        $('#errorMessage').snackbar('show');
    }
});