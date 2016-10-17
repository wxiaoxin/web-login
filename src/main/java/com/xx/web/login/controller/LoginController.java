package com.xx.web.login.controller;

import com.xx.web.login.base.BaseController;
import com.xx.web.login.entity.User;
import com.xx.web.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 登陆控制器
 */

@Controller
public class LoginController extends BaseController {


    @Autowired
    private IUserService userService;


    /**
     * 登陆页面
     *
     * @return 如果自动登陆成功，则跳转到首页，否则跳转到登陆页
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    /**
     * 登陆
     *
     * @param user 用户
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(User user, boolean autoLogin) {
        Map<String, Object> loginResult = userService.login(user.getUsername(), user.getPassword(), autoLogin, req, resp);
        // 读取登陆结果
        String stateCode = String.valueOf(loginResult.get("stateCode"));
        if (stateCode.equals("1")) {
            success("登陆成功");
        } else if (stateCode.equals("2")) {
            error("用户名和密码不一致");
        } else {
            error("用户名不存在");
        }
        return result.toJSONString();
    }


    /**
     * 退出登陆
     *
     * @return 登陆页面
     */
    @RequestMapping("/loginout")
    public String loginout() {
        // 清除session
        userService.invalidSession(req);
        // 清楚cookie
        userService.clearCookie(req);
        // 回到登陆页面
        return "login";
    }


}
