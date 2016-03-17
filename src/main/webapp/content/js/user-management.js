/**
 * Created by Zheny Chaichits on 29.02.2016.
 */


$('li').click(function () {
    switch ($(this).text()) {
        case 'Admin':
            $('#findRole').val('ADMIN');
            $('form#searchUsers').submit();
            break;
        case 'Администратор':
            $('#findRole').val('ADMIN');
            $('form#searchUsers').submit();
            break;
        case 'Student':
            $('#findRole').val('STUDENT');
            $('form#searchUsers').submit();
            break;
        case 'Студент':
            $('#findRole').val('STUDENT');
            $('form#searchUsers').submit();
            break;
        case 'Support':
            $('#findRole').val('SUPPORT');
            $('form#searchUsers').submit();
            break;
        case 'Оператор':
            $('#findRole').val('SUPPORT');
            $('form#searchUsers').submit();
            break;
    }
});

$('#findStudent').select(function () {
    $('#findRole').val('STUDENT');
    $('form#searchUsers').submit();
});

$('#findAdmin').select(function () {
    $('#findRole').val('ADMIN');
    $('form#searchUsers').submit();
});

$('#findSupport').select(function () {
    $('#findRole').val('SUPPORT');
    $('form#searchUsers').submit();
});