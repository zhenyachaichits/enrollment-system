/**
 * Created by Zheny Chaichits on 04.04.2016.
 */


var currentSubjectName;
$('.saved').click(function () {
    currentSubjectName = $(this).val();
});

var isNewName = false;

$('.subject-name').focusout(function () {
    var subjectName = $(this).val();
    var re = /.{2,}/;
    var m;

    if ((m = re.exec(subjectName)) !== null) {

        if (subjectName !== currentSubjectName || $(this).hasClass('saved')) {
            $(".loader").attr("aria-busy", "true");
            $.post(
                '/action',
                {
                    command: 'check-subject-name',
                    subjectName: subjectName
                },
                function (resp) {
                    status = $.trim(resp.toLowerCase());
                    if (status === "positive") {
                        $('#nameErrorMessage').snackbar('show');
                        $(this).focus();
                        isNewName = false;
                    } else {
                        isNewName = true;
                    }
                    $(".loader").attr("aria-busy", "false");
                })
                .fail(function () {
                    alert("Request failed.");
                    $(".loader").attr("aria-busy", "false");
                    isNewName = false;
                });
        }
    }
});

$('#addSubject').click(function () {
    if (isNewName) {
        $('form#addUser').submit();
    } else {
        $('#errorMessage').snackbar('show');
    }
})