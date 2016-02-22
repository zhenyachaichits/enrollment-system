/**
 * Created by Zheny Chaichits on 21.02.2016.
 */

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
