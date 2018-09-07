//加载执行
var username;
var buildtoken;
var nickname;
window.onload = function () {
    var cookieString = document.cookie;
    var arrycookie = cookieString.split(";");
    for (var i = 0; i < arrycookie.length; i++) {
        var arr = arrycookie[i].split("=");
        if (Trim(arr[0]) == "username") {
            username = arr[1];
        }
        if (Trim(arr[0]) == "buildtoken") {
            buildtoken = arr[1];
        }
        if (Trim(arr[0]) == "nickname") {
            nickname = arr[1];
        }
    }

    if (username == undefined || buildtoken == undefined) {
        //console.log("登录信息失效。")
    } else {
        var code = "username=" + username + "&buildtoken=" + buildtoken;
        logonStatu(code);
    }
};

//检测登录状态
function logonStatu(code) {
    jQuery.ajax({
        type: "POST",
        url: "/logonStatu",
        data: code,
        success: callback
    });
}

function callback(date) {
    if (date == 1) {
        jQuery("#loginlink").text("欢迎，" + nickname + "  [退出登录]");
        jQuery("#loginlink").attr('href', "/outlogin");
        jQuery("#loginlink").attr('target', "_Self");
    } else {
        //console.log("登录失败。");
    }
}

//封装去重函数
function Trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

