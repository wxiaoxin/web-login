/**
 * Created by wxiao on 2016.10.19.
 */

// 邮编
jQuery.validator.addMethod("zipcode", function (value, element) {
    // 自定义校验方法
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
});

// 表单校验
$("#userForm").validate({
    rules: {
        username: {
            required: true,
            rangelength: [4, 10]
        },
        age: {
            required: true,
            digits: true,
            range: [0, 100]
        },
        email: {
            email: true
        },
        zipCode: {
            zipcode: true
        },
        password: {
            required: true,
            minlength: 4,
            maxlength: 50
        },
        password2: {
            required: true,
            equalTo: "#password"
        },
        autoLogin: {
            required: true
        }
    },
    messages: {
        username: {
            required: "请填写用户名！",
            rangelength: "用户名长度在4~10个之间！"
        },
        age: {
            required: "请填写年龄！",
            digits: "请输入合法的年龄！",
            range: "请输入合适的年龄"
        },
        email: {
            email: "邮箱格式有误！"
        },
        zipCode: {
            zipcode: "请输入正确格式的邮编！"
        },
        password: {
            required: "请填写密码！"
        },
        password2: {
            required: "请填写确认密码！",
            equalTo: "两次填写密码不同！"
        },
        autoLogin: {
            required: "勾选同意！"
        }
    },
    // errorClass: "has-error",
    errorElement: "span",
    errorPlacement: function(error, element) {
        element.next().text(error.text());
        // error.appendTo(element.parent());
        // $eleParent = element.parent();
        // $eleParent.removeClass("has-success");
        // $eleParent.addClass("has-error");
    },
    // success: function(error, element) {
        // 传入的element是dom对象，需要包装成jQuery对象
        // $eleParent = $(element).parent();
        // $eleParent.removeClass("has-error");
        // $eleParent.addClass("has-success");
    // }
});

// 点击按钮提交表单
$("#loginBtn").click(function () {

    if(!$("#userForm").valid()) {
        alert("表单校验失败");
    }


});