/**
 * Created by Zheny Chaichits on 26.02.2016.
 */

$('#findByPassportId').click(function () {
    $('#findByPassportCommand').val('search-profile-by-passport');
    $('form#searchByPassport').submit();
});

$('#findAppByPassportId').click(function () {
    $('#findByPassportCommand').val('search-applied-by-passport');
    $('form#searchByPassport').submit();
});

$('#findByName').click(function () {
    $('#findByNameCommand').val('search-profiles-by-last-name');
    $('form#searchByLastName').submit();
});

$('#findAppByName').click(function () {
    $('#findByNameCommand').val('search-applied-by-last-name');
    $('form#searchByLastName').submit();
});

$('#findAllProfiles').click(function () {
    $('#findAllCommand').val('search-all-profiles');
    $('form#searchAll').submit();
});

$('#findAllApplied').click(function () {
    $('#findAllCommand').val('search-all-applied');
    $('form#searchAll').submit();
});
