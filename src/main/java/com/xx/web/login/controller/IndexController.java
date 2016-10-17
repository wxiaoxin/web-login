package com.xx.web.login.controller;

import com.xx.web.login.base.BaseController;
import com.xx.web.login.entity.User;
import com.xx.web.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 首页控制器
 */

@Controller
public class IndexController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index() {

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("WY_USER")) {
                String value = cookie.getValue();
                if (!value.equals("")) {
                    // 从Cookie中读取身份信息，进行自动登陆
                    String[] userInfo = value.split("-");
                    String userId = userInfo[0];
                    String userName = userInfo[1];
                    String password = userInfo[2];
                    Map<String, Object> loginResult = userService.login(userName, password);
                    String stateCode = String.valueOf(loginResult.get("stateCode"));
                    if(stateCode.equals("1")) {
                        // 获取成功登陆的用户对象
                        User loginUser = (User) loginResult.get("user");
                        // 将成功登陆的用户id保存进session中
                        req.getSession().setAttribute("LOGIN_USER_ID", loginUser.getId());
                        // 用户无操作时，session维持最长存活时间
                        req.getSession().setMaxInactiveInterval(60 * 10);
                        // 自动登陆成功，重定向到欢迎页面
                        return "redirect:/welcome.htm";
                    }

                }
            }
        }
        return "index";
    }

}

