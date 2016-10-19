<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ include file="init.jsp" %>
<html>

<head>
    <title>登陆</title>
</head>

<body>

<div class="container">

    <form id="userForm">

        <div class="form-group">
            <label for="username" class="control-label">用户名</label>
            <input id="username" name="username" type="text" class="form-control">
        </div>

        <div class="form-group">
            <label for="password" class="control-label">密码</label>
            <input id="password" name="password" type="password" class="form-control">
        </div>

        <div class="form-group">
            <label class="control-label">
                <input name="autoLogin" type="checkbox" value="true"> &nbsp;7天之内免登陆
            </label>
        </div>

        <div class="form-group">
            <button class="btn btn-primary" id="loginBtn" type="button">登陆</button>
        </div>

    </form>

</div>

<%-- 表单校验--%>
<script src="${path}/resouces/plugins/jquery-validate/jquery.validate.min.js"></script>
<%-- 异步提交表单插件 --%>
<script src="${path}/resouces/plugins/jquery-form/jquery.form.min.js"></script>

<script src="${path}/resouces/js/login.js"></script>

</body>

</html>
