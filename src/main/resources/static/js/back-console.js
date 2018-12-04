//标题修改提交
function changeTitle(num) {

    var formadd = 'form-add-b'+num;

    // 获取页面已有的一个form表单
    var form = document.getElementById(formadd);
    // 用表单来初始化
    var formData = new FormData(form);
    // 我们可以根据name来访问表单中的字段
    // 获取标题
    //var title1_title = formData.get("title1_title");
    //获取文章地址
    //var work_link = formData.get("work_link");
    //获取摘要
    //var abs_text = formData.get("abs_text");
    // 当然也可以在此基础上，添加其他数据
    //区分种类
    formData.append("type", "B");
    //标记下标
    formData.append("index", "1");

    jQuery.ajax({
        type: "POST",
        url: "../backConsoleCheck",
        data: formData,
        async: false,
        cache: false,
        contentType: false,//用了火狐报错XML 解析错误：找不到根元素,不用后台报错request无法转型为MultipartFile
        processData: false,
        success: ajaxcheck
    });

}

function ajaxcheck(data) {
    alert(data);
}