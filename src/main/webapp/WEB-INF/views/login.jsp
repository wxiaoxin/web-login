<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ include file="init.jsp" %>
<html>

<head>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <form action="${path}/login.htm" method="post">

        <div class="form-group">
            <label for="username" class="control-label">用户名</label>
            <input type="text" id="username" name="username" class="form-control">
        </div>

        <div class="form-group">
            <label for="password" class="control-label">密码</label>
            <input type="password" id="password" name="password" class="form-control">
        </div>

        <div class="form-group">
            <label class="control-label">
                <input type="checkbox" name="autoLogin" value="true"> &nbsp;7天之内免登陆
            </label>
        </div>

        <div class="form-group">
            <button class="btn btn-primary" type="submit">登陆</button>
        </div>

    </form>

</div>



</body>

</html>
