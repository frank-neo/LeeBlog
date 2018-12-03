/**
 * 验证登录code-js
 */

var isRun = true;
var remember_flag;//1是记住，2是不记住
var user;
var psw;

$(function () {

    var cookieString = document.cookie;
    var arrycookie = cookieString.split(";");
    for (var i = 0; i < arrycookie.length; i++) {
        var arr = arrycookie[i].split("=");
        if ($.trim(arr[0]) == "username") {
            user = arr[1];
            if ($("#inputEmail").val() == null || $("#inputEmail").val() == "") {
                $("#inputEmail").attr("value", user);
            }
        }
        if ($.trim(arr[0]) == "pswd") {
            psw = arr[1];
            if ($("#inputPassword").val() == null || $("#inputPassword").val() == "") {
                $("#inputPassword").attr("value", psw);
            }
        }
    }

});

//点击换一张验证码
$('.imgcode').hover(function () {
    layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "点击更换(不区分大小写)" + "</span>", '#imgObj', {
        tipsMore: true,
        time: 6000,
        tips: [2, "#ffffff"]
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
    //防止重复提交部分
    if (isRun) {
        //console.log('提交按钮被点击');
        isRun = false;

        if ($("#remember-me").is(':checked')) {
            remember_flag = 1;
        } else {
            remember_flag = 2;
        }
        //提交数据部分
        var psw_md5;
        if ($("#inputPassword").val() == psw) {
            psw_md5 = psw;
        } else {
            psw_md5 = hex_md5($("#inputPassword").val());
        }
        user = $("#inputEmail").val();
        var vcode = $("#verify").val();
        var code = "c=" + vcode + "&u=" + user + "&p=" + psw_md5 + "&r=" + remember_flag;
        $.ajax({
            type: "POST",
            url: "../ResultServlet",
            data: code,
            success: callback
        });
    }
    setTimeout(function () {
        isRun = true;
    }, 3500); //点击后相隔多长时间可执行
}

// 验证以后处理提交信息或错误信息
function callback(data) {
    var jsonReturn = JSON.parse(data);
    //alert(jsonReturn.username + ";" + jsonReturn.password + ";" + jsonReturn.code + ";" + jsonReturn.sessionval);

    //用户名
    if (jsonReturn.username == 20) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    } else if (jsonReturn.username == 21) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    } else {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/y_alt.png' style='margin-top: 7px'>" + "</div>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    }

    //密码
    if (jsonReturn.password == 30) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    } else if (jsonReturn.password == 31) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    } else {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/y_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    }


    //验证码
    if (jsonReturn.sessionval == 0) {
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    } else {

        if (jsonReturn.code == 12) {
            layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/y_alt.png' style='margin-top: 7px'>" + "</div>", '#verify', {
                tipsMore: true,
                time: 6000,
                tips: [4, '#ffffff']
            });
        } else {
            layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#verify', {
                tipsMore: true,
                time: 6000,
                tips: [4, '#ffffff']
            });
        }

    }

    //全部正确后重定向到首页
    if (jsonReturn.code == 12 && jsonReturn.password == 32 && jsonReturn.username == 22){
        //$(location).attr('href','http://ragdollhouse.club');
        //window.location.replace("http://10.14.6.246/")
        window.location.replace("http://ragdollhouse.club/")
    }
}

//记住登录名和密码
$("#remember-me").click(function () {
    var n = document.getElementById("remember-me").checked;
    if (n) {
        remember_flag = 1;
    } else {
        remember_flag = 0;
    }
});