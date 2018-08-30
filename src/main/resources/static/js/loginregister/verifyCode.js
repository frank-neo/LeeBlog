/**
 * 验证code-js
 */

//点击换一张验证码
$('.imgcode').hover(function () {
    layer.tips("<span style='font-size:14px;height:30px;line-height:45px;'>" + "点击更换(不区分大小写)" + "</span>", '#imgObj', {
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

var isRun=true;
// 验证码验证
function isRightCode() {
    //防止重复提交部分
    if(isRun){
        console.log('提交按钮被点击');
        isRun=false;
        //提交数据部分
        var code = $("#verify").val();
        var user = $("#inputEmail").val();
        var psw = $("#inputPassword").val();
        //alert(code);
        code = "c=" + code+"&u="+user+"&p="+psw;
        $.ajax({
            type: "POST",
            url: "../ResultServlet",
            data: code,
            success: callback
        });
    }
    setTimeout(function(){
        isRun=true;
    },3500); //点击后相隔多长时间可执行
}

// 验证以后处理提交信息或错误信息
function callback(data) {
    var jsonReturn = JSON.parse(data);
    alert(jsonReturn.code);
    if (jsonReturn.code == 1) {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/y_alt.png' style='margin-top: 7px'>" + "</div>", '#verify', {
            time: 6000,
            tips: [4, '#ffffff']
        });
        return;
    }else if (data.toString() == 0){
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    } else {
        layer.tips("<div style='width:32px; height:32px;color:#ffffff'>" + "<img src='../css/assets/x_alt.png' style='margin-top: 7px'>" + "</div>", '#verify', {
            time: 6000,
            tips: [4, '#ffffff']
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