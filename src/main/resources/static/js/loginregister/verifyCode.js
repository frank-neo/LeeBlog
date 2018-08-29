/**
 * 验证code-js
 */

//点击换一张验证码
$('.imgcode').hover(function () {
    layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "点击更换(不区分大小写)" + "</span>", '.verify', {
        time: 6000,
        tips: [2, "#f7fafc"]
    })
}, function () {
    layer.closeAll('tips');
}).click(function () {
    var imgSrc = $("#imgObj");
    var src = imgSrc.attr("src");
    imgSrc.attr("src", chgUrl(src));
});


// 时间戳
// 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    url = url.substring(0, 17);
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}

// 验证码验证
function isRightCode() {
    var code = $("#verify").val();
    //alert(code);
    code = "c=" + code;
    $.ajax({
        type: "POST",
        url: "ResultServlet",
        data: code,
        success: callback
    });
}

// 验证以后处理提交信息或错误信息
function callback(data) {
    if (data.toString() == 1) {
        layer.tips("<span style='font-size:16px;height:30px;line-height:45px;'>" + "<img src='../css/assets/y_alt.png'>" + "</span>", '#verify', {
            tips: [4, '#f7fafc']
        });
        return;
    } else {
        layer.tips("<div style='width:32px; height:32px;color:#f7fafc'>" + "<img src='../css/assets/x_alt.png'>" + "</div>", '#verify-div', {
            tips: [4, '#f7fafc']
        });
        return;
    }
}

//记住密码
$("#remember-me").click(function () {
    var n = document.getElementById("remember-me").checked;
    if (n) {
        $(".zt").show();
    } else {
        $(".zt").hide();
    }
});