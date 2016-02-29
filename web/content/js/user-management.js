/**
 * Created by Zheny Chaichits on 29.02.2016.
 */

$('#findStudent').click(function () {
    $('#findRole').val('STUDENT');
    $('form#searchUsers').submit();
});

$('#findAdmin').click(function () {
    $('#findRole').val('ADMIN');
    $('form#searchUsers').submit();
});

$('#findSupport').click(function () {
    $('#findRole').val('SUPPORT');
    $('form#searchUsers').submit();
});