$(() => {
    $(".btnSignup").click((e) => {
        let passCurrent = $("#pass").val();
        let passRes = $("#passRes").val();
        if (passCurrent != passRes) {
            e.preventDefault();
            $("#message").text("Pass Res dose not match!");
            $(".btnSignup").addClass("mt-3");
        } else {
            $("#message").text("");
            $(".btnSignup").removeClass("mt-3");
        }
    });
})