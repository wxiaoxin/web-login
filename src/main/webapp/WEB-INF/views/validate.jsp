<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ include file="init.jsp" %>
<html>

<head>
    <title>表单校验实例</title>
    <style>
        span.error {
            color: red;
        }
    </style>
</head>

<body>

<div class="container">

    <form id="userForm">

        <div class="form-group" >
            <label for="username" class="control-label">用户名</label>
            <input id="username" name="username" type="text" class="form-control">
            <span class="error"></span>
        </div>

        <div class="form-group">
            <label for="age" class="control-label">年龄</label>
            <input id="age" name="age" type="number" class="form-control">
            <span class="error"></span>
        </div>

        <div class="form-group">
            <label for="email" class="control-label">邮箱</label>
            <input id="email" name="email" type="email" class="form-control">
            <span class="error"></span>
        </div>


        <div class="form-group">
            <label for="zipCode" class="control-label">邮编</label>
            <input id="zipCode" name="zipCode" type="number" class="form-control">
            <span class="error"></span>
        </div>

        <div class="form-group">
            <label for="password" class="control-label">密码</label>
            <input id="password" name="password" type="password" class="form-control">
            <span class="error"></span>
        </div>

        <div class="form-group">
            <label for="password2" class="control-label">确认密码</label>
            <input id="password2" name="password2" type="password" class="form-control">
        </div>

        <div class="form-group">
            <label class="control-label">
                <input id="agreeCheckbox" name="autoLogin" type="checkbox" value="true" > &nbsp;同意协议
            </label>
        </div>


    </form>


    <div class="form-group">
        <button class="btn btn-primary" id="loginBtn">登陆</button>
    </div>

</div>

<%-- 表单校验--%>
<script src="${path}/resouces/plugins/jquery-validate/jquery.validate.js"></script>
<%-- 异步提交表单插件 --%>
<script src="${path}/resouces/plugins/jquery-form/jquery.form.min.js"></script>

<script src="${path}/resouces/js/validate.js"></script>

</body>

</html>
