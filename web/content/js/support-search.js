/**
 * Created by Zheny Chaichits on 26.02.2016.
 */

$('#findByPassport').click(function () {
    $('#findPassportCommand').val('search-profiles-by-passport');
    $('form#searchForm').submit();
});

$('#findAppByPassport').click(function () {
    $('#findPassportCommand').val('search-applied-by-passport');
    $('form#searchAppForm').submit();
});

$('#findByName').click(function () {
    $('#findNameCommand').val('search-profiles-by-last-name');
    $('form#searchForm').submit();
});

$('#findAppByName').click(function () {
    $('#findNameCommand').val('search-applied-by-last-name');
    $('form#searchAppForm').submit();
});