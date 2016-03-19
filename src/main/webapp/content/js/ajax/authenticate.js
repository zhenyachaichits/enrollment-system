/**
 * Created by Zheny Chaichits on 06.02.16.
 */


function authenticateCookies() {
    $('#newEmail').val("");
    $.post(
        '/home',
        {
            command: 'cookie-authenticate'
        },
        function (resp) {
            status = $.trim(resp.toLowerCase());
            if (status != "negative") {
                window.location.href = resp;
            }
        })
        .fail(function () {
            alert("Request failed.");
        });
}


$('#submit').click(function () {

    var userEmail = $('#email').val();
    var userPassword = $('#password').val();
    var isRemembered = $('#remember').val();

    $.post(
        '/home',
        {
            command: 'authenticate',
            email: userEmail,
            password: userPassword,
            remember: isRemembered
        },
        
        function (resp) {
            status = $.trim(resp.toLowerCase());
            if (status === "negative") {
                $('#errorMessage').snackbar('show');
            } else {
                window.location.href = resp;
            }
        })
        .fail(function () {
            alert("Request failed.");
        });
});