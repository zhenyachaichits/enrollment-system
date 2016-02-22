/**
 * Created by Zheny Chaichits on 31.01.2016.
 */

var currentEmail;
var currentPassportId;

$(document).ready(function () {
    currentEmail = $('#newEmail').val();
    currentPassportId = $('#passportID').val();
});

function refreshTotal() {
    var total = 0;
    $(".point").each(function () {
        var element = $(this);
        var value = parseInt(element.val());
        ;
        total += value;
    });
    $("#totalPoints").val(total);
};

$("#faculty").change(function () {

    $("#subject1Group").hide("fast");
    $("#subject2Group").hide("fast");
    $("#subject3Group").hide("fast");

    var faculty = $('#faculty').val();

    $.get("/action?command=get-subjects&facultyID=" + faculty, function (responseXml) {
        $("#subjects").html($(responseXml).find("data").html());
        $("#subject1Group").show("fast");
        $("#subject2Group").show("fast");
        $("#subject3Group").show("fast");
    });
});

var isEmailOk = true;

$('#newEmail').focusout(function () {
    var userEmail = $('#newEmail').val();
    var patt = new RegExp(".*@.*");
    var res = patt.test(userEmail);

    if (res && userEmail !== currentEmail) {
        $.post(
            '/action',
            {
                command: 'check-email',
                email: userEmail,
            },
            function (resp) {
                status = $.trim(resp.toLowerCase());
                if (status === "positive") {
                    $('#emailErrorMessage').snackbar('show');
                    $('#newEmail').focus();
                    isEmailOk = false;
                } else {
                    isEmailOk = true;
                }
            })
            .fail(function () {
                alert("Request failed.");
            });
    }
});

var isPassportOk = true;

$('#passportID').focusout(function () {
    var passportID = $('#passportID').val();
    var re = /[a-zA-Zа-яА-Я]{2}\d{7}/;
    var m;

    if ((m = re.exec(passportID)) !== null && passportID !== currentPassportId) {
        $.post(
            '/action',
            {
                command: 'check-passport-id',
                passportID: passportID,
            },
            function (resp) {
                status = $.trim(resp.toLowerCase());
                if (status === "positive") {
                    $('#passportErrorMessage').snackbar('show');
                    $('#passportID').focus();
                    isPassportOk = false;
                } else {
                    isPassportOk = true;
                }
            })
            .fail(function () {
                alert("Request failed.");
            });
    }
});

