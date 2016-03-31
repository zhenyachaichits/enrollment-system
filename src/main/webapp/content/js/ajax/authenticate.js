/**
 * Created by Zheny Chaichits on 06.02.16.
 */


function authenticateCookies() {
    $(".loader").attr("aria-busy", "true");
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
            $(".loader").attr("aria-busy", "false");
        })
        .fail(function () {
            alert("Request failed.");
            $(".loader").attr("aria-busy", "false");
        });
}


$('#submit').click(function () {

    $(".loader").attr("aria-busy", "true");
    var userEmail = $('#email').val();
    var userPassword = $('#password').val();
    var isRemembered = $('#remember').is(':checked');
    
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
            $(".loader").attr("aria-busy", "false");
        })
        .fail(function () {
            alert("Request failed.");
            $(".loader").attr("aria-busy", "false");
        });
});