let isRepeated = true;
let uploading = false;
let recodeRepeated = true;


$(document).ready(function () {

    $("#tool-text").bind(function () {
        isRepeated = false;
    });

    $("#tool-size").bind(function () {
        isRepeated = false;
    });

    $("#qrCodeFile").on("change",function () {
        recodeRepeated = false;
    });

});


function qrcode_convert() {
    let t = $("#tool-text").val();
    let e = $("#tool-size").val();
    if ("" == t) {
        alert("请填写内容！"),
            $("#tool-text").focus();
        return;
    }
    if ("" == e) {
        alert("请填写尺寸！");
        $("#tool-size").focus();
        return;
    }
    if ((e = parseInt(e)) < 50 || 800 < e) {
        alert("请填写50~800px内的尺寸！");
        $("#tool-size").focus();
        return;
    }
    if(!isRepeated){
        return;
    }

    $("#qrcode > img").attr("src", "/qrcode/make?text=" + t + "&size=" + e);
}

function qrcode_spot() {
    if(!!!$("#qrCodeFile").val() ){
        alert("请选择文件!");
        return false;
    }
    if(recodeRepeated){
        alert("请重新选择文件!");
        return false;
    }
    if(uploading){
        alert("文件正在上传中，请稍候");
        return false;
    }
    var data =new FormData() ;
    data.append("file", document.getElementById("qrCodeFile").files[0]);

    console.log(data);
    $.ajax({
        url:  "/qrcode/spot",
        type: 'POST',
        cache: false,
        data: data,
        processData: false,
        contentType: false,
        dataType:"json",
        beforeSend: function(){
            uploading = true;
        },
        success : function(data) {
            recodeRepeated = true;
            if (data.code == 1) {
                $("#spot-result > p").text(data.data);
            } else {
                alert(data.msg);
            }
            uploading = false;
        }
    });

}
