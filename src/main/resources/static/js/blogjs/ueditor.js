//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');

function getContent() {
    var arr = [];
    arr.push(UE.getEditor('editor').getContent());
    var blog_data = JSON.stringify(arr);
    alert(blog_data);
    return blog_data;
}

function dosubmit() {

    jQuery.ajax({
        type: "POST",
        url: "../ueditorSubmit",
        data: getContent(),
        dataType:"JSON",
        success: callback
    });
}


function callback(data) {
    
}