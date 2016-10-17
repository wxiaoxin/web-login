<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ include file="init.jsp" %>
<html>

<head>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <form id="userForm">

        <div class="form-group">
            <label for="username" class="control-label">用户名</label>
            <input type="text" id="username" name="username" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="password" class="control-label">密码</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>

        <div class="form-group">
            <label class="control-label">
                <input type="checkbox" name="autoLogin" value="true"> &nbsp;7天之内免登陆
            </label>
        </div>

        <div class="form-group">
            <button class="btn btn-primary" id="loginBtn">登陆</button>
        </div>

    </form>

</div>

<%-- jquery --%>
<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<%-- 异步提交表单插件 --%>
<script src="http://cdn.bootcss.com/jquery.form/3.51/jquery.form.min.js"></script>
<script>

    // 点击按钮提交表单
    $("#loginBtn").click(function () {

        // 提交表单
        $("#userForm").ajaxSubmit({
            // 地址
            url: "${path}/login.htm",
            // 方式
            type: "post",
            //返回数据类型　　　
            dataType: "json",
            // 回调函数
            success: function (result) {
                console.log(result);
                if(result.state)  {
                    // 重定向到首页
                    window.location.href = "${path}/index.htm";
                } else {
                    alert(result.message);
                }
            },
            // 错误
            error: function (result) {
                console.log("表单提交失败，稍后重试");
            }
        });
    });

</script>


</body>

</html>
