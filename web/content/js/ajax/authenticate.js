/**
 * Created by Zheny Chaichits on 06.02.16.
 */


function authenticateCookies() {
    $('#newEmail').val("");
    $.post(
        '/action',
        {
            command: 'cookie-authenticate'
        },
        function (resp) {
            status = $.trim(resp.toLowerCase());
            if (status === "positive") {
                location.reload();
            }
        })
        .fail(function () {
            alert("Request failed.");
        });
}


$('#submit').click(function () {

    var userEmail = $('#email').val();
    var userPassword = $('#password').val();

    $.post(
        '/action',
        {
            command: 'check-account',
            email: userEmail,
            password: userPassword
        },
        function (resp) {
            status = $.trim(resp.toLowerCase());
            if (status === "negative") {
                $('#errorMessage').snackbar('show');
            } else {
                $('form#signInForm').submit();
            }
        })
        .fail(function () {
            alert("Request failed.");
        });
});