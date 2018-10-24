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

    var checkdata = "buildtoken="+ buildtoken +"&nickname=" + nickname;

    jQuery.ajax({
        type: "POST",
        url: "../EditBlogCheck",
        data: checkdata,
        success: callback
    });
};

function callback(data) {
    var permission = JSON.parse(data);

    if(permission.checkdata == "outoflogin"){
        jQuery("#editblog").text("身份信息已失效，请重新登录。");
        jQuery("#editblog").attr("href","/login");
    }else if (permission.checkdata == "VISITOR"){
        jQuery("#editblog").text("发表博客功能尚未对游客开放，敬请期待。");
        jQuery("#editblog").attr("href","#");
    }
}

//封装去重函数
function Trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}