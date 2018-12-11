//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('editor');

//获取文章正文
function getContent() {
    var arr = UE.getEditor('editor').getContent().replace(/\%/g,'%25');
    //var blog_data = JSON.stringify(arr);
    return  arr;
}

//获取文章标题
function gettitle() {
    var title = jQuery("#blog_title").val();
    return title;
}

//获取文章作者
function getauthor() {
    var author = jQuery("#author").val();
    return author;
}

//获取文章标题
function getblog_type() {
    var blog_type = jQuery("#blog_type").val();
    return blog_type;
}

function dosubmit() {

    //等号左右一定不能有空格
    var blogcontent = "blogcontent="+getContent()+"&title="+gettitle()+"&author="+getauthor()+"&getblog_type="+getblog_type();

    jQuery.ajax({
        type: "POST",
        url: "../ueditorSubmit",
        data: blogcontent,
        dataType:"JSON",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: callback
    });
}

function callback(data) {
    var str = JSON.stringify(data);
    var str1 = JSON.parse(str);
    if (str1.insert_state == "success"){
        alert("提交成功啦！！！！点击确定跳转博客列表。");
        //window.location.replace("http://10.14.6.246/blog");
        window.location.replace("http://ragdollhouse.club/blog");
    }else{
        alert("失败。");
    }
}