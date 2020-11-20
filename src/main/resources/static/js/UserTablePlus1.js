//可以编写多个$(document).ready(function (){}) !

var btnUploadText = '选择图片';

//*************左侧菜单栏部分************
$(document).ready(function () {
    var trigger = $('.hamburger'),
        overlay = $('.overlay'),
        isClosed = false;


    trigger.click(function () {
        hamburger_cross();
    });

    function hamburger_cross() {

        if (isClosed == true) {
            overlay.hide();
            trigger.removeClass('is-open');
            trigger.addClass('is-closed');
            isClosed = false;
        } else {
            overlay.show();
            trigger.removeClass('is-closed');
            trigger.addClass('is-open');
            isClosed = true;
        }
    }

    $('[data-toggle="offcanvas"]').click(function () {
        $('#wrapper').toggleClass('toggled');
    });

    overlay.click(function () {
        overlay.hide();
        trigger.removeClass('is-open');
        trigger.addClass('is-closed');
        isClosed = false;
        $('#wrapper').toggleClass('toggled');
    })
});
//*************左侧菜单栏部分************

$(document).ready(function () {
    $("#selectImg").text(btnUploadText)
    $(".imageShow").hide()
    $('#selectImg').click(function () {
        $('#imagePic').click();
    });
    $("#imagePic").on("change", function (e) {
        var file = e.target.files[0]; //获取图片资源
        var fileTypes = ["bmp", "jpg", "png", "jpeg"];
        var bTypeMatch = false
        for (var i = 0; i < fileTypes.length; i++) {
            var start = file.name.lastIndexOf(".");
            var fileType = file.name.substring(start + 1);
            if (fileType.toLowerCase() == fileTypes[i]) {
                bTypeMatch = true;
                break;
            }
        }
        if (bTypeMatch) {
            if (file.size <= 1024 * 1024 * 10) {
                var reader = new FileReader();
                reader.readAsDataURL(file); // 读取文件
                // 渲染文件
                reader.onload = function (arg) {
                    $(".imageShow").show()
                    $("#img_add_show").attr("src", arg.target.result)
                    btnUploadText = '重新上传'
                    $("#selectImg").text(btnUploadText)
                }
            } else {
                alert('仅支持不超过10M的图片');
                emptyImageUpload("#imagePic")
                $("#img_add_show").attr("src", "")
                $(".imageShow").hide()
                btnUploadText = '上传'
                $("#selectImg").text(btnUploadText)
                return false;
            }
        } else {
            alert('仅限bmp，jpg，png，jpeg图片格式');
            emptyImageUpload("#imagePic")
            $("#img_add_show").attr("src", "")
            $(".imageShow").hide()
            btnUploadText = '上传'
            $("#selectImg").text(btnUploadText)
            return false;
        }
    });


    //修改信息栏图片上传
    $("#selectImg_update").text(btnUploadText)
    $(".imageShow_update").hide()
    $('#selectImg_update').click(function () {
        $('#imagePic_update').click();
    });
    $("#imagePic_update").on("change", function (e) {
        var file = e.target.files[0]; //获取图片资源
        var fileTypes = ["bmp", "jpg", "png", "jpeg"];
        var bTypeMatch = false
        for (var i = 0; i < fileTypes.length; i++) {
            var start = file.name.lastIndexOf(".");
            var fileType = file.name.substring(start + 1);
            if (fileType.toLowerCase() == fileTypes[i]) {
                bTypeMatch = true;
                break;
            }
        }
        if (bTypeMatch) {
            if (file.size <= 1024 * 1024 * 10) {
                var reader = new FileReader();
                reader.readAsDataURL(file); // 读取文件
                // 渲染文件
                reader.onload = function (arg) {
                    $(".imageShow_update").show()
                    $("#img_update").attr("src", arg.target.result)
                    btnUploadText = '重新上传'
                    $("#selectImg_update").text(btnUploadText)
                }
            } else {
                alert('仅支持不超过10M的图片');
                emptyImageUpload("#imagePic_update")
                $("#img_update").attr("src", "")
                $(".imageShow_update").hide()
                btnUploadText = '上传'
                $("#selectImg_update").text(btnUploadText)
                return false;
            }
        } else {
            alert('仅限bmp，jpg，png，jpeg图片格式');
            emptyImageUpload("#imagePic_update")
            $("#img_update").attr("src", "")
            $(".imageShow_update").hide()
            btnUploadText = '上传'
            $("#selectImg_update").text(btnUploadText)
            return false;
        }
    });

})

//清空上传图片信息
function emptyImageUpload(selector) {
    var fi;
    var sourceParent;
    if (selector) {
        fi = $(selector);
        sourceParent = fi.parent();
    } else {
        return;
    }
    $("<form id='tempImgForm'></form>").appendTo(document.body);

    var tempImgForm = $("#tempImgForm");
    tempImgForm.append(fi);
    tempImgForm.get(0).reset();
    sourceParent.append(fi);
    tempImgForm.remove();
}

//上传照片
function uploadImg() {
    if($("#imagePic").val() != "") {
        //创建FormData对象
        var formData = new FormData();
        formData.append('photo', document.getElementById('imagePic').files[0]);
        $.ajax({
            type: "POST",
            url:"http://localhost:8084/uploadPhoto",//后台接口
            dataType: "json",
            data:formData,
            fileElementId:"file",  // 文件的id
            async: true, // 使用同步操作
            contentType:false,        /*不可缺*/
            processData: false,         /*不可缺*/
            success: function(data,status,xhr){
                console.log("返回信息:"+data.msg+" "+data.filepath+data.filename);
                $("#img_add").attr("value",data.filename);
                console.log("返回码："+data.status+" code:"+data.code+" status:"+status+" xhr:"+xhr.status)
                if(xhr.status === 200) {
                    alert("上传成功:"+xhr.status+" OK");
                    $("#add_text").text("上传成功"+xhr.status);
                }
            },
            error: function (d,status,xhr) {
                console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                alert("上传失败："+d.status);
                $("#add_text").text("上传失败"+xhr.status);
            },
        });
    } else {
        alert("请先选择文件");
    }
}

//修改信息栏的上传照片
function uploadImgUpdate() {
    if($("#imagePic_update").val() != "") {
        //创建FormData对象
        var formData = new FormData();
        formData.append('photo', document.getElementById('imagePic_update').files[0]);
        $.ajax({
            type: "POST",
            url:"http://localhost:8084/uploadPhoto",//后台接口
            dataType: "json",
            data:formData,
            fileElementId:"file",  // 文件的id
            async: true, // 使用同步操作
            contentType:false,        /*不可缺*/
            processData: false,         /*不可缺*/
            success: function(data,status,xhr){
                console.log("返回信息:"+data.msg+" "+data.filepath+data.filename);
                console.log("返回码："+data.status+" code:"+data.code+" status:"+status+" xhr:"+xhr.status)
                $("#img_text_update").attr("value",data.filename);
                if(xhr.status === 200) {
                    alert("上传成功:"+xhr.status+" OK");
                    $("#update_text").text("上传成功"+xhr.status);
                }
            },
            error: function (d,status,xhr) {
                console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                alert("上传失败："+d.status);
                $("#update_text").text("上传失败"+d.status);
            },
        });
    } else {
        alert("请先选择文件");
    }
}

//修改信息模态框
function updateMsg() {
    var rows = $("#teacher_table").bootstrapTable('getSelections');
    if (rows.length < 1) {
        alert("请选择一条数据");
        return false;
    }
    var rowItem = rows[0];
    $('#myModalUpdate').modal();

    $('#id_update').attr("value",rowItem.userId);
    $('#name_update').attr("value",rowItem.userName);
    $('#phone_update').attr("value",rowItem.userPhone);
    var filePath = "http://localhost:8084/downloadPhoto/"+rowItem.userImg;
    $('#img_update_show').attr("src",filePath);

    //身份栏显示
    //将对象转为Json
    var temp = JSON.stringify(rowItem.roles);
    console.log('修改信息栏的身份：'+temp);
    //将Json转为字符数组
    var obj = eval(temp);
    var role='';
    var str = '';
    for(var i=0;i<obj.length;i++){
        role = obj[i].roleName;
        console.log('role='+role);
        if(role==='admin'){
            $("#admin_box").prop("checked",true);
        }else if(role==='user'){
            $("#user_box").prop("checked",true);
        }

    }

    console.log(filePath)

}

//修改提交的信息
function updateMsgAjax() {

    //获取复选框里的被选中的内容
    var arr = [];
    $('input[name=identify]').each(function () {
        console.log($(this).prop('checked'));
        if($(this).prop('checked')===true){
            arr.push($(this).val())
        }
    });

    console.log("修改信息 arr = "+arr);

    var data = {};
    data["userId"] = $('#id_update').val();
    data["userName"] = $('#name_update').val();
    data["userPhone"] = $('#phone_update').val();
    data["userImg"] = $('#img_text_update').val();
    data["roleNames"] = arr;

    $.ajax({
        type: "POST",
        url:"http://localhost:8084/table/edit",//后台接口
        dataType: "json",
        contentType:"application/json",
        data:JSON.stringify(data),
        success: function(data,status,xhr){
            console.log("返回码："+data.status+" code:"+data.code+" status:"+status+" xhr:"+xhr.status)
            if(data.status === 200) {
                initMsg();
                alert("修改成功:"+xhr.status+" OK");
            }
        },
        error: function (d,status,xhr) {
            if(d.status === 200) {
                alert("修改成功:"+d.status+" OK");
                console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                initMsg();
            }else{
                console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                alert("修改失败："+d.status);
            }
        },
    });
}

//还原数据（清除控件显示的旧数据）
function initMsg() {
    $('#myModalUpdate').modal('hide');
    $('#myModalDelete').modal('hide');
    $('#myModalAdd').modal('hide');
    $('#teacher_table').bootstrapTable('refresh');
    $("input[type=reset]").trigger("click");
    $("#selectImg").text("选择图片");
    $("#img_add_show").attr("src","");
    $("#add_text").text("--");
    $("#basicMsg").attr("class","tab-pane fade in active");
    $("#basicMsg_add").attr("class","tab-pane fade in active");
    $("#userImg_add").attr("class","tab-pane fade");
    $("#img_update").attr("src","");
    $("#update_text").text("--");
    $("#userImg").attr("class","tab-pane" );
    $("#li1_add").attr("class","active col-md-4");
    $("#li2_add").attr("class","col-md-4");
    $("#li1_update").attr("class","active col-md-4");
    $("#li2_update").attr("class","col-md-4");
    $("#admin_box").prop("checked",false);
    $("#user_box").prop("checked",false);
}

//删除数据模态框
function deleteMsg() {
    var rows = $("#teacher_table").bootstrapTable('getSelections');
    if (rows.length < 1) {
        alert("请选择一条数据");
        return false;
    }
    var rowItem = rows[0];
    $('#myModalDelete').modal();
    $('#id_del').attr("value",rowItem.userId);
    $('#name_del').attr("value",rowItem.userName);
    $('#phone_del').attr("value",rowItem.userPhone);


}

//执行删除数据
function deleteMsgAjax() {
    var data = {};
    data["userId"] =  $('#id_del').val();

    $.ajax({
        type: "POST",
        url:"http://localhost:8084/table/delete",//后台接口
        dataType: "json",
        contentType:"application/json",
        data:JSON.stringify(data),
        success: function(data,status,xhr){
            console.log("返回码："+data.status+" code:"+data.code+" status:"+status+" xhr:"+xhr.status)
            if(data.status === 200) {
                initMsg();
                alert("删除成功:"+xhr.status+" OK");
            }
        },
        error: function (d,status,xhr) {
            if(d.status === 200) {
                alert("删除成功:"+d.status+" OK");
                console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                initMsg();
            }else{
                console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                alert("删除失败："+d.status);
            }
        },
    });
}

//添加数据模态框
function addMsg(){
    $('#myModalAdd').modal('show');
}

//执行添加数据
function addMsgAjax(){

    var id = $('#id_add').val();
    var name = $('#name_add').val();
    var phone = $('#phone_add').val();
    var img = $('#img_add').val();
    var password = $('#password_add').val();

    if(id === ''|| name === ''|| phone === ''){
        alert("请填完整信息！");
    }else{
        var data = {};
        data["userId"] =  id;
        data["userName"] =  name;
        data["userPhone"] =  phone;
        data["userImg"] =  img;
        data["userPassword"] =  password;
        $.ajax({
            type: "POST",
            url:"http://localhost:8084/table/add",//后台接口
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(data),
            success: function(data,status,xhr){
                console.log("返回码："+data.status+" code:"+data.code+" status:"+status+" xhr:"+xhr.status)
                if(data.returnCode === "CODE5001") {
                    alert(data.returnMsg);
                }else if(data.returnCode === "CODE6000"){
                    alert(data.returnMsg);
                }
                initMsg();
            },
            error: function (d,status,xhr) {
                if(d.status === 200) {
                    alert("添加成功:"+d.status+" OK");
                    console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                    initMsg();
                }else{
                    console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                    alert("添加失败："+d.status);
                }
            },
        });
    }
}

//查询数据
function queMsg(){
    var id = $('#id_que').val();
    var name = $('#name_que').val();
    var phone = $('#phone_que').val();
    var img = $('#date_que').val();
    var date = $('#date_que').val();
    date = date.replace(/-/g,"");
    var status = $('#status_que').val();

    //如果什么也没有填，则查询到所有数据
    if(id === ''&& name === ''&& phone === ''){
        $('#teacher_table').bootstrapTable('refreshOptions',{url:"http://localhost:8084/table/all"});
    }else {
        var data = {};
        data["userId"] =  id;
        data["userName"] =  name;
        data["userPhone"] =  phone;
        console.log('查询：'+id+' '+name+' '+phone);

        $.ajax({
            type: "POST",
            url:"http://localhost:8084/table/query",//后台接口
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(data),
            success: function(data,status,xhr){
                console.log(data)
                $('#teacher_table').bootstrapTable('refreshOptions',{url:null,data:data});  //查询数据，刷新表格
                console.log("返回码："+data.status+" code:"+data.code+" status:"+status+" xhr:"+xhr.status)
            },
            error: function (d,status,xhr) {
                if(d.status === 200) {
                    console.log(data)
                    $('#teacher_table').bootstrapTable({data:data});
                    console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                }else{
                    console.log("返回码："+d.status+" code:"+d.code+" status:"+status+" xhr:"+xhr.status)
                }
            },
        });
    }

}

//查看数据模态框
function readMsg(){
    var rows = $("#teacher_table").bootstrapTable('getSelections');
    if (rows.length < 1) {
        alert("请选择一条数据");
        return false;
    }
    var rowItem = rows[0];
    $('#myModalRead').modal();

    $('#id_read').attr("value",rowItem.userId);
    $('#name_read').attr("value",rowItem.userName);
    $('#phone_read').attr("value",rowItem.userPhone);
    var filePath = "http://localhost:8084/downloadPhoto/"+rowItem.userImg;
    $('#img_read_show').attr("src",filePath);
    console.log(filePath)

    //身份栏显示
    //将对象转为Json
    var temp = JSON.stringify(rowItem.roles);
    console.log('查看信息栏的身份：'+temp);
    //将Json转为字符数组
    var obj = eval(temp);
    var role='';
    var str = '';
    $("#iden_adm_read").hide();
    $("#iden_user_read").hide();
    for(var i=0;i<obj.length;i++){
        role = obj[i].roleName;
        console.log('role='+role);
        if(role==='admin'){
            $("#iden_adm_read").show();
        }else if(role==='user'){
            $("#iden_user_read").show();
        }
    }
}

function identifyFormatter(value, row, index) {
    if(value==null || value===''){
        return '';
    }

    //将对象转为Json
    var temp = JSON.stringify(value);
    //将Json转为字符数组
    var obj = eval(temp);
    var role='';
    var str = '';
    for(var i=0;i<obj.length;i++){
        role = obj[i].roleName;
        console.log('role='+role);
        if(role==='admin'){
            str+='<span class="label label-info">'+role+'</span>'+' ';
        }else if(role==='user'){
            str+='<span class="label label-success">'+role+'</span>'+' ';
        }

    }

    console.log(obj.length);
    console.log('value = '+temp);
    console.log(str);
    return str;
}

function imageFormatter(value) {
    var imgName = value;
    console.log('imgName = '+imgName);
    return '<img src="http://localhost:8084/downloadPhoto/'+imgName +'"'+ 'alt="" style="weight:30px;height:30px">';
}

//菜单栏的链接跳转
$(function () {
    //首次进入显示主页
    $('#principalPart').load('index.html');

    $('#toUserTablePage').click(function () {
        $('#principalPart').empty();
        console.log("跳转至用户表");
        $('#principalPart').load('UserTable.html');
    });

    $('#toHomePage').click(function () {
        $('#principalPart').empty();
        console.log("跳转至用户表");
        $('#principalPart').load('index.html');
    });
});
