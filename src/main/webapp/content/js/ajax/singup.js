/**
 * Created by Zheny Chaichits on 31.01.2016.
 */

var currentEmail;
var currentPassportId;

$(document).ready(function () {
    currentEmail = $('#newEmail').val();
    currentPassportId = $('#passportID').val();
    $("#subject1Group").hide("fast");
    $("#subject2Group").hide("fast");
    $("#subject3Group").hide("fast");
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

    $(".loader").attr("aria-busy", "true");
    $("#subject1Group").hide("fast");
    $("#subject2Group").hide("fast");
    $("#subject3Group").hide("fast");

    var faculty = $('#faculty').val();

    $.post(
        '/action',
        {
            command: 'get-subjects',
            facultyID: faculty,
        },
        function (responseXml) {
            var i = 1;
            $(responseXml).find("data").find("subject").each(function () {
                $("#label" + i).text($(this).find("name").text());
                $("#subject" + i).attr('min', $(this).find("minPoint").text());
                $("#help" + i).text($("#subjectMessage").text() + $(this).find("minPoint").text());
                $("#subject" + i + "Group").show("fast");
                i++;
            });
            $(".loader").attr("aria-busy", "false");
        })
        .fail(function () {
            alert("Request failed.");
            $(".loader").attr("aria-busy", "false");
        });
});

var isEmailOk = true;

$('#newEmail').focusout(function () {
    var userEmail = $('#newEmail').val();
    var patt = new RegExp(".*@.*");
    var res = patt.test(userEmail);

    if (res && userEmail !== currentEmail) {
        $(".loader").attr("aria-busy", "true");
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
                $(".loader").attr("aria-busy", "false");
            })
            .fail(function () {
                alert("Request failed.");
                $(".loader").attr("aria-busy", "false");
            });
    }
});

var isPassportOk = true;

$('#passportID').focusout(function () {

    var passportID = $('#passportID').val();
    var re = /[a-zA-Zа-яА-Я]{2}\d{7}/;
    var m;

    if ((m = re.exec(passportID)) !== null && passportID !== currentPassportId) {
        $(".loader").attr("aria-busy", "true");
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
                $(".loader").attr("aria-busy", "false");
            })
            .fail(function () {
                alert("Request failed.");
                $(".loader").attr("aria-busy", "false");
            });
    }
    
});

