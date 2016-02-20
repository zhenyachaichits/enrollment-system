/**
 * Created by Zheny Chaichits on 31.01.2016.
 */


$('.date').bootstrapMaterialDatePicker({format: 'DD.MM.YYYY', lang: 'en', weekStart: 1, time: false});
$("select").dropdown({"autoinit": "select"});

$(function ($) {
    $(".phone").mask("+999 (99) 999-99-99", {placeholder: "_"});
    $(".score").mask("999", {placeholder: ""});
    $("#passportID").mask("aa9999999", {placeholder: ""});
});

$(document).ready(function () {

    $('body').bind('mousewheel', function (e) {
        e.preventDefault();
        e.stopPropagation();
    });
});

$('.scroll').onepage_scroll({
    sectionContainer: "section",
    easing: "ease",                  // "ease", "linear", "ease-in"
    animationTime: 800,
    pagination: false,
    updateURL: false,
    beforeMove: function (index) {
    },
    afterMove: function (index) {
    },
    loop: false,
    keyboard: false,
    responsiveFallback: 1000,
    direction: "vertical"
});

$(document).keydown(function (objEvent) {
    if (objEvent.keyCode == 9) {
        objEvent.preventDefault();
    }
});

if (matchMedia) {
    var mq = window.matchMedia("(min-width: 1000px)");
    mq.addListener(switchScrollability);
    switchScrollability(mq);
}

function switchScrollability(mq) {
    $('.scroll').moveTo(1);
    if (mq.matches) {
        $('body').bind('mousewheel', function (e) {
            e.preventDefault();
            e.stopPropagation();
        });
    } else {
        $('body').unbind('mousewheel');
    }
}

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

    if (res) {
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

    if ((m = re.exec(passportID)) !== null) {
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


$('#toPersonal').click(function () {
    var password = $('#newPassword').val();
    var email = $('#newEmail').val();
    var patt = new RegExp(".*@.*");
    var res = patt.test(email);

    if (email === "" ||
        password.length < 5 ||
        password.length > 20 ||
        res == false ||
        isEmailOk == false) {

        $('#errorMessage').snackbar('show');
    }
    else {
        $('.scroll').moveDown();
    }
});

$('#toEducation').click(function () {
    if ($('#passportID').val() == "" ||
        $('#firstName').val() == "" ||
        $('#middleName').val() == "" ||
        $('#lastName').val() == "" ||
        $('#dateBirth').val() == "") {

        $('#errorMessage').snackbar('show');
    }
    else {
        $('.scroll').moveDown();
    }
});

$('#toPrivileges').click(function () {
    if ($('#faculty').val() == "" ||
        $('#subject1').val() == "" ||
        $('#subject2').val() == "" ||
        $('#subject3').val() == "" ||
        $('#gpa').val() == "") {

        $('#errorMessage').snackbar('show');
    }
    else {
        $('.scroll').moveDown();
    }
});

$('#toContact').click(function () {
    if ($('#privilegies').val() == "") {

        $('#errorMessage').snackbar('show');
    }
    else {
        $('.scroll').moveDown();
    }
});

$('#confirm').click(function () {
    if ($('#phone').val() == "" ||
        $('#address').val() == "" ||
        $('#passportID').val() == "" ||
        $('#firstName').val() == "" ||
        $('#middleName').val() == "" ||
        $('#lastName').val() == "" ||
        $('#dateBirth').val() == "" ||
        $('#faculty').val() == "" ||
        $('#privilegies').val() == "" ||
        $('#subject1').val() == "" ||
        $('#subject2').val() == "" ||
        $('#subject3').val() == "" ||
        $('#gpa').val() == "") {

        $('#errorMessage').snackbar('show');
    }
});
