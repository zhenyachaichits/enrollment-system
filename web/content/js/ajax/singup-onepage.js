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

$('body').on({
    'mousewheel': function (e) {
        e.preventDefault();
        e.stopPropagation();
    }
});

var isEmailOk = true;

$('#newEmail').focusout(function() {
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

$('#toContact').click(function () {
    if ($('#faculty').val() == "" ||
        $('#privilegies').val() == "") {

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
        $('#privilegies').val() == "") {

        $('#errorMessage').snackbar('show');
    }
});
