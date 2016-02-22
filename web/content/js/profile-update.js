/**
 * Created by Zheny Chaichits on 21.02.2016.
 */

function setReadOnly() {
    $("#newEmail").prop("readonly", true);
    $("#medalType").prop("readonly", true);
    $("#privileges").prop("readonly", true);
    $("#phone").prop("readonly", true);
    $("#address").prop("readonly", true);
    $("#passportID").prop("readonly", true);
    $("#firstName").prop("readonly", true);
    $("#middleName").prop("readonly", true);
    $("#lastName").prop("readonly", true);
    $("#dateBirth").prop("readonly", true);
    $("#faculty").prop("readonly", true);
}

$(document).ready(function () {
    $("#passwordGroup").hide(0);
    $("#saveBtn").hide(0);
    $("#cancelBtn").hide(0);
    
    setReadOnly();
});

$("#cancelBtn").click(function () {
    $("#passwordGroup").hide(500);
    $("#saveBtn").hide(500);
    $("#cancelBtn").hide(500);

    $("#updAccount").show(500);
    $("#updPrivileges").show(500);
    $("#updContact").show(500);
    $("#updPersonal").show(500);
    
    setReadOnly();
});


$("#updAccount").click(function () {
    $("#newEmail").prop("disabled", false);
    
    $("#passwordGroup").show(500);
    $("#updAccount").hide(500);
    
    $("#saveBtn").show(500);
    $("#cancelBtn").show(500);
});

$("#updPrivileges").click(function () {
    $("#medalType").prop("readonly", false);
    $("#privileges").prop("readonly", false);
    
    $("#updPrivileges").hide(500);
    $("#saveBtn").show(500);
    $("#cancelBtn").show(500);
});

$("#updContact").click(function () {
    $("#phone").prop("readonly", false);
    $("#address").prop("readonly", false);

    $("#updContact").hide(500);
    $("#saveBtn").show(500);
    $("#cancelBtn").show(500);
});

$("#updPersonal").click(function () {
    $("#passportID").prop("readonly", false);
    $("#firstName").prop("readonly", false);
    $("#middleName").prop("readonly", false);
    $("#lastName").prop("readonly", false);
    $("#dateBirth").prop("readonly", false);

    $("#updPersonal").hide(500);
    $("#saveBtn").show(500);
    $("#cancelBtn").show(500);
});

$("#updEducation").click(function () {
    $("#faculty").prop("readonly", false);
    $("#totalPoints").prop("readonly", false);

    $("#updEducation").hide(500);
    $("#saveBtn").show(500);
    $("#cancelBtn").show(500);
});