/**
 * Created by wxiao on 2016.10.19.
 */

// 表单校验
$("#userForm").validate({
    rules: {
        username: {
            required: true
        },
        password: {
            required: true
        },
        email: {
            email: true
        }
    },
    messages: {
        username: {
            required: "请填写用户名！"
        },
        password: {
            required: "请填写密码！"
        },
        email: {
            email: "邮箱格式有误！"
        }
    }
});


// 点击按钮提交表单
$("#loginBtn").click(function () {

    // 提交表单
    $("#userForm").ajaxSubmit({
        // 地址
        url: path + "/login.htm",
        // 方式
        type: "post",
        //返回数据类型　　　
        dataType: "json",
        // 回调函数
        success: function (result) {
            console.log(result);
            if (result.state) {
                // 重定向到首页
                window.location.href = path + "/index.htm";
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