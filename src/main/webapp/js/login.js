/**
 * Created by Administrator on 2017/5/3.
 */
function login(){
    var account = $("#username").val();
    var pwd =$("#password").val();
    if(account==""||account==null){
        alert("账号不能为空");
        return;
    }
    if(pwd==""||pwd==null){
        alert("密码不能为空");
        return;
    }

    $.ajax({
        type:"GET",
        url:"/admin/login.do",
        dataType:"json",
        async: false,
        data:{
            username:account,
            password:pwd
        },
        success:function(data){

            console.log(data);
            var status = data.msg;
            if (status == "成功") {
                location.href = "/admin/news";
            } else if (status == "成功管理员"){
                location.href = "/admin/users";
            } else {
                alert("用户名或者密码错误");
            }

        },
        error:function(data){
            alert("请求失败，网络异常");
            console.log(data);
        }
    });
}