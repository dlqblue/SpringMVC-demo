<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/25
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>编辑新闻</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="shortcut icon" href="http://mindmup.s3.amazonaws.com/lib/img/favicon.ico" >
    <link rel="stylesheet" type="text/css" href="/css/prettify.css">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-wysihtml5.css">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css" rel="stylesheet" type="text/css">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet" type="text/css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>编辑新闻</h1>
    <hr/>
    <form:form action="/admin/news/updateP" method="post" commandName="newsP" role="form">
        <div class="form-group">
            <label for="title">标题：</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题：" value="${news.title}"/>
        </div>
        <div class="form-group">
            <label for="userByUserId.id">作者：</label>
            <select class="form-control" id="userByUserId.id" name="userByUserId.id">
                <c:forEach items="${userList}" var="user">
                    <c:if test="${user.id==news.userByUserId.id}">
                        <option value="${user.id}" selected="selected">${user.nickname}</option>
                    </c:if>
                    <c:if test="${user.id!=news.userByUserId.id}">
                        <option value="${user.id}">${user.nickname}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="content">内容：</label>
            <%--<textarea class="form-control" id="content" name="content" rows="3"--%>
                      <%--placeholder="Please Input Content"></textarea>--%>
            <textarea class="form-control" id="content" name="content" placeholder="请输入内容：" rows="10">${news.content}</textarea>
        </div>
        <div class="form-group">
            <label for="pushDate">日期：</label>
            <input type="date" class="form-control" id="pushDate" name="pushDate"
                   value="<fmt:formatDate value="${news.pushDate }" pattern="yyyy-MM-dd"/>"/>
        </div>
        <!-- 把 id 一并写入 newsP 中 -->
        <input type="hidden" id="id" name="id" value="${news.id}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>

<script src="/js/wysihtml5-0.3.0.js"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="/js/prettify.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="/js/bootstrap-wysihtml5.js"></script>

<script>
    $('#content').wysihtml5();
</script>
</body>
</html>
