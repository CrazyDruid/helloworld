/**
 * Created by Administrator on 2018/5/22.
 */
$(document).ready(function(){
    var search = {};
    search["userId"] = $('#userId').html();
    $.ajax({
        url:"http://localhost:8080/things/dataThings",
        contentType: "application/json",
        data:JSON.stringify(search),
        dataType: 'json',
        type:"POST",
        success:function(data){
            var html = "";
            if(data.length == 0){
                alert("没数据");
                return;
            }
            for (var i = 0; i < data.length; i++) {
                var obj = data[i];
                //alert(obj.name);
                html += "<li>" + obj.name + "  ￥" + obj.price +  " name:" + obj.username +"</li>";
            }
            $("#ulul").html(html);
        },
        error:function(){
            alert("数据错误!");
        }
    });
});