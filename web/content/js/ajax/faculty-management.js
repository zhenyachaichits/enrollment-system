/**
 * Created by Zheny Chaichits on 01.03.2016.
 */

$("#subjects").change(function () {
    if($("select option:selected").length != 3) {
        $('#subjectsErrorMessage').snackbar('show');
    }
});