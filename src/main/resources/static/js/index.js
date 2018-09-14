//加载执行
var buildtoken;
var nickname;
window.onload = function () {
    var cookieString = document.cookie;
    var arrycookie = cookieString.split(";");
    for (var i = 0; i < arrycookie.length; i++) {
        var arr = arrycookie[i].split("=");
        if (Trim(arr[0]) == "buildtoken") {
            buildtoken = arr[1];
        }
        if (Trim(arr[0]) == "nickname") {
            nickname = arr[1];
        }
    }

    if (nickname == undefined || buildtoken == undefined) {
        //console.log("登录信息失效。")
    } else {
        jQuery("#loginlink").text("欢迎，" + nickname + "  [退出登录]");
        jQuery("#loginlink").attr('href', "/outlogin?nickname="+nickname+"&buildtoken="+buildtoken);
        jQuery("#loginlink").attr('target', "_Self");
    }
};

//封装去重函数
function Trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

