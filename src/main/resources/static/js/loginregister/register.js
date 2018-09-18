/**
 * 验证注册code-js
 */

var isRun = true;
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
            var code = "c=" + vscode + "&u=" + user + "&p=" + psw + "&s=" + sexflag + "&n=" + nickname;
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
    var callbackflag = true;
    var jsonReturn = JSON.parse(data);

    //用户名
    if (jsonReturn.username == 21) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "邮箱已被注册过！" + "</span>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
        callbackflag = false;
    } else {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/y_alt.png' style='margin-top: 7px'>" + "</div>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    }

    //昵称
    if (jsonReturn.nickname == 32) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/y_alt.png' style='margin-top: 7px'>" + "</div>", '#nickName', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
    } else {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#nickName', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "昵称已经有人使用！" + "</span>", '#nickName', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
        callbackflag = false;
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
            callbackflag = false;
        }

    }

    //全部正确后提示激活邮件已经发送，请前往激活
    if (callbackflag == true) {
        window.location.replace("http://ragdollhouse.club/messgaeIssue");
    }
}


//前端验证
function frontCheck() {
    var frontCheckFlag = true;
    user = $("#inputEmail").val();
    psw = $("#inputPassword").val();
    pswcheck = $("#inputPasswordcheck").val();
    nickname = $("#nickName").val();
    sexflag = $("input[name='sex']:checked").val();
    vscode = $("#verify").val();

    //邮箱
    if (user == "" || user.length == 0) {
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
        frontCheckFlag = false;
    } else if ((!/^\w+@\w+(\.\w+)+$/.test(user)) || user.length > 100) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "邮箱格式不正确" + "</span>", '#inputEmail', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
        frontCheckFlag = false;
    }

    //密码
    if (psw == "" || psw.length == 0) {
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
        frontCheckFlag = false;
    } else if (psw.length > 100) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "密码过长" + "</span>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
        frontCheckFlag = false;
    }else if (psw.length < 6){
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "密码太短，安全系数过低。" + "</span>", '#inputPassword', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
        frontCheckFlag = false;
    }
    
    //重复密码
    if (pswcheck == "" || pswcheck.length == 0) {
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
        frontCheckFlag = false;
    } else {
        if (pswcheck != psw) {
            layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#inputPasswordcheck', {
                tipsMore: true,
                time: 6000,
                tips: [4, '#ffffff']
            });
            frontCheckFlag = false;
            layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "两次输入密码不一致！" + "</span>", '#inputPasswordcheck', {
                tipsMore: true,
                time: 6000,
                tips: [2, "#ffffff"]
            });
            frontCheckFlag = false;
        }
    }
    //昵称
    if (nickname == ""||nickname.length == 0) {
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
        frontCheckFlag = false;
    }else if (nickname.length >10) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#nickName', {
            tipsMore: true,
            time: 6000,
            tips: [4, '#ffffff']
        });
        layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "昵称过长！" + "</span>", '#nickName', {
            tipsMore: true,
            time: 6000,
            tips: [2, "#ffffff"]
        });
        frontCheckFlag = false;
    }

    //性别
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
        frontCheckFlag = false;
    }
    if (frontCheckFlag == true) {
        return 1;
    }
}
