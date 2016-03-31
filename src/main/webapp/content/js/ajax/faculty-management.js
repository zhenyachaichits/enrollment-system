/**
 * Created by Zheny Chaichits on 01.03.2016.
 */

$("#subjects").focusout(function () {
    if ($("select option:selected").length != 4) {
        $('#subjectsErrorMessage').snackbar('show');
        $("#subjects").focus();
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
                facultyName: facultyName,
            },
            function (resp) {
                status = $.trim(resp.toLowerCase());
                if (status === "positive") {
                    $('#nameErrorMessage').snackbar('show');
                    $('#facultyName').focus();
                    isPassportOk = false;
                } else {
                    isPassportOk = true;
                }
                $(".loader").attr("aria-busy", "false");
            })
            .fail(function () {
                alert("Request failed.");
                $(".loader").attr("aria-busy", "false");
            });
    }
});