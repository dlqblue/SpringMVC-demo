<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/23
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC Demo 首页</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/form-elements.css">
    <link rel="stylesheet" href="/css/style.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>Big News</strong> 后台管理中心</h1>
                    <div class="description">
                        <p>
                            搞个大新闻！报道绝无偏差！作者新浪微博 <a href="http://weibo.com/dlqblue"><strong>键盘摄影师_le</strong></a>, 欢迎互粉~
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>登录页面</h3>
                            <p>输入你的用户名和密码</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" class="login-form">
                            <div class="form-group">
                                <label class="sr-only" for="username">用户名</label>
                                <input type="text" name="nickname" placeholder="请输入用户名"
                                       class="form-username form-control" id="username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="password">密码</label>
                                <input type="password" name="password" placeholder="请输入密码"
                                       class="form-password form-control" id="password">
                            </div>
                            <button type="button" class="btn btn-sm btn-success" id="login">登陆！</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/js/jquery.backstretch.min.js"></script>
<script>

    jQuery(document).ready(function() {

        /*
         Fullscreen background
         */
        $.backstretch("/img/backgrounds/1.jpg");

    });

    $("#login").click(login);

    function login(){
        var username = $("#username").val();
        var pwd =$("#password").val();
        if(username==""||username==null){
            alert("账号不能为空");
            return false;
        }
        if(pwd==""||pwd==null){
            alert("密码不能为空");
            return false;
        }

        $.ajax({
            url:"/admin/login.do",
            type:"POST",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify({
                username:username,
                password:pwd
            }),
            success:function(data){

                var status = data.msg;
                if (status == "success") {
                    sessionStorage.setItem("user","common");
                    window.location.href = "admin/news";
                } else if (status == "successAdmin"){
                    sessionStorage.setItem("user","admin");
                    window.location.href = "admin/users";
                } else {
                    alert("用户名或者密码错误");
                }

            },
            error:function(data){
                alert("请求失败，网络异常");
            }
        });

    }

</script>

</body>
</html>