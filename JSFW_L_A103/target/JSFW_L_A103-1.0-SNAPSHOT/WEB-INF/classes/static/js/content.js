$(() => {
    $(document).on("click", "#btnDeleteContent", function () {
        let id = $(this).attr("data");
        if (confirm("Are you sure want to do this?")) {
            $.ajax({
                type: "DELETE",
                url: "/member/content/delete/" + id,
                success: function (response) {
                    alert("Delete Successfully!");
                    window.location.href = "/member/content";
                },
                error: function (error) {
                    alert("Do yo now delete this is row, because not authorization for you! OKE!")
                }
            })
        }
    });
    $(document).on("click", "#btnDetailContent", function () {
        let id = $(this).attr("data");
        $.ajax({
            type: "GET",
            url: "/member/content/" + id,
            success: function (response) {
                let content = JSON.parse(response);
                $("#idInput").val(content.id);
                $("#title").val(content.title);
                $("#brief").val(content.brief);
                $("#content").val(content.content);
            },
            error: function (error) {
                console.log(error)
            }
        });
    });
    $("#btnUpdateContent").click(() => {
        if (confirm("Are you sure want to do this?")) {
            $.ajax({
                url: "/member/content/update",
                type: "PUT",
                data: JSON.stringify({
                    id: $("#idInput").val(),
                    title: $("#title").val(),
                    brief: $("#brief").val(),
                    content: $("#content").val(),
                }),
                contentType: "application/json",
                dataType: "json",
                success: function (response) {
                    window.location.reload();
                },
                error: function (error) {
                    $("#titleError").text("");
                    $("#briefError").text("");
                    $("#contentError").text("");
                    error.responseJSON.forEach(item => {
                        if (item.field == "title") {
                            $("#titleError").text(item.defaultMessage);
                        } else if (item.field == "brief") {
                            $("#briefError").text(item.defaultMessage);
                        } else {
                            $("#contentError").text(item.defaultMessage);
                        }
                    })
                }
            })
        }

    });
    let typingTimer;
    var timeout = 1000;
    $("#inputSearchTitle").keyup((e) => {
        clearTimeout(typingTimer);
        typingTimer = setTimeout(() => {
            console.log($("#inputSearchTitle").val());
        }, timeout);
    });
})