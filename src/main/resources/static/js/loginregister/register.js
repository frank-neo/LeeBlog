/**
 * 验证注册code-js
 */

var isRun = true;
var remember_flag;//1是记住，2是不记住
var user;
var psw;
var pswcheck;
var nickname;
var sexflag;
var vscode;
//点击换一张验证码
$('.imgcode').hover(function () {
    layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "点击更换(不区分大小写)" + "</span>", '#imgObj', {
        tipsMore: true,
        time: 6000,
        tips: [2, "#ffffff"]
    });
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

        if (frontCheck() == 1) {
            var code = "c=" + vscode + "&u=" + user + "&p=" + psw + "&r=" + remember_flag + "&s=" + sexflag + "&n=" + nickname;
            alert(code);
            $.ajax({
                type: "POST",
                url: "../RegisterCheck",
                data: code,
                success: callback
            });
        }
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
    if (jsonReturn.code == 12 && jsonReturn.password == 32 && jsonReturn.username == 22) {
        //$(location).attr('href','http://ragdollhouse.club');
        window.location.replace("http://10.14.6.85/")
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

//前端验证
function frontCheck() {
    user = $("#inputEmail").val();
    psw = $("#inputPassword").val();
    pswcheck = $("#inputPasswordcheck").val();
    nickname = $("#nickName").val();
    sexflag = $("input[name='sex']:checked").val();
    vscode = $("#verify").val();

    if (user == "") {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "邮箱不能为空" + "</span>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
    }
    if (psw == "") {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "密码不能为空" + "</span>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
    }
    if (pswcheck == "") {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPasswordcheck', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "密码不能为空" + "</span>", '#inputPasswordcheck', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
    }else{
        if (pswcheck != psw){
            layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPasswordcheck', {
                tipsMore: true,
                time: 6000,
                tips: [4, '#ffffff']
            });
            layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "两次输入密码不一致！" + "</span>", '#inputPasswordcheck', {
                tipsMore: true,
                time: 6000,
                tips: [2, "#ffffff"]
            });
        }
    }
    if (nickname == "") {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#nickName', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "昵称不能为空" + "</span>", '#nickName', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
    }
    if (sexflag == "" || sexflag == undefined) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 0px'>" + "</div>", '#sexbox', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "性别不能为空" + "</span>", '#sexbox', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
    }
    //alert("邮箱：" + user + ";密码：" + psw + ";确认密码：" + pswcheck + ";昵称：" + nickname + "；性别：" + sexflag);
    if (user != "" && psw != "" && pswcheck != "" && nickname != "" && (sexflag != "" || sexflag != undefined) && pswcheck == psw) {

        return 1;
    }
}
