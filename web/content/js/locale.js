/**
 * Created by Zheny Chaichits on 07.02.16.
 */

$(document).ready(function () {

    var currentLocale = $('#currentLocale').val();

    if (currentLocale === 'en') {
        $('#enLabel').addClass('text-success');
    } else {
        $('#ruLabel').addClass('text-success');
    }

    $('#localeForm').attr('action', window.location.pathname);

    $('#enBtn').click(function () {
        $('#localeValue').val('en');
        $('form#localeForm').submit();
    });

    $('#ruBtn').click(function () {
        $('#localeValue').val('ru');
        $('form#localeForm').submit();
    });

});