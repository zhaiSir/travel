//登录
    $("#loginForm").submit(function () {
        $("#errorMsg").hide();
        $.post("loginServlet", $(this).serialize(), function (data) {
            if (data.flag) {
                //跳转成功页面
                window.location.href = "index.html";
            } else {
                $("#errorMsg").show();
                $("#errorMsg").html(data.errorMsg);
            }
        });
        return false;
    })
