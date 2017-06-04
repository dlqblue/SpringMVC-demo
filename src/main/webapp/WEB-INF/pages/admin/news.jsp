<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/25
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>新闻管理</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>新闻管理</h1>
    <hr/>

    <h3>所有新闻 <a href="/admin/news/add" type="button" class="btn btn-primary btn-sm">添加</a>
                <a href="/admin/users" id="userbtn" type="button" class="btn btn-primary btn-sm btn-warning" style="display: none">用户管理</a>
                <a id="cancelBtn" type="button" class="btn btn-primary btn-sm btn-danger">注销当前用户</a>
    </h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty newsList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>新闻列表为空，请<a href="/admin/news/add" type="button" class="btn btn-primary btn-sm">添加</a>
        </div>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty newsList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>标题</th>
                <th>作者</th>
                <th>发布日期</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${newsList}" var="news">
                <tr>
                    <td>${news.id}</td>
                    <td>${news.title}</td>
                    <td>${news.userByUserId.nickname}</td>
                    <td><fmt:formatDate value="${news.pushDate }" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <a href="/admin/news/show/${news.id}" type="button" class="btn btn-sm btn-success">详情</a>
                        <a href="/admin/news/update/${news.id}" type="button" class="btn btn-sm btn-warning">修改</a>
                        <a href="/admin/news/delete/${news.id}" id="delete" style="display: none" type="button" class="deleteBtn btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script>

    $(function () {
        var user = sessionStorage.getItem("user");
        if (user == "admin") {
            $("#userbtn").show();
        }
    });

    function ifAdmin() {
        var user = sessionStorage.getItem("user");
        if (user == "admin") {
            $(".deleteBtn").show();
        }
    }

    $("#cancelBtn").click(cancel);
    $("#delete").load(ifAdmin());

    function cancel() {
        sessionStorage.removeItem("user");
        window.location.href = "/";
    }

</script>
</body>
</html>
