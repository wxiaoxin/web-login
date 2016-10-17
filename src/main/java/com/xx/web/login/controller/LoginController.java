package com.xx.web.login.controller;

import com.xx.web.login.base.BaseController;
import com.xx.web.login.entity.User;
import com.xx.web.login.service.IUserService;
import com.xx.web.login.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import java.util.HashMap;
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
     * 登陆
     *
     * @param user
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(User user, boolean autoLogin) {
        Map<String, Object> loginResult = userService.login(user.getUsername(), MD5Utils.MD5(user.getPassword().trim()));
        // 读取登陆结果
        String stateCode = String.valueOf(loginResult.get("stateCode"));
        if (stateCode.equals("1")) {
            // 登陆成功
            success("登陆成功");
            // 获取成功登陆的用户对象
            User loginUser = (User) loginResult.get("user");
            // 设置Cookie
            Cookie userIdCookie;
            if (autoLogin) {
                // 7天之内免登陆，则保存用户id，用户名和用户密码到cookie中
                userIdCookie = new Cookie("WY_USER", loginUser.getId() + "-" + loginUser.getUsername() + "-" + loginUser.getPassword());
                // 设置自动登陆有效时间7天
                userIdCookie.setMaxAge(60 * 5);
            } else {
                userIdCookie = new Cookie("WY_USER", loginUser.getId());
            }
            // 添加cookie
            resp.addCookie(userIdCookie);
            // 将成功登陆的用户id保存进session中
            req.getSession().setAttribute("LOGIN_USER_ID", loginUser.getId());
            // 用户无操作时，session维持最长存活时间
            req.getSession().setMaxInactiveInterval(60 * 10);

        } else if (stateCode.equals("2")) {
            // 用户名和密码不一致
            error("用户名和密码不一致");
        } else {
            // 用户名不存在
            error("用户名不存在");
        }
        return result.toJSONString();
    }


    /**
     * 跳转到欢迎页
     */
    @RequestMapping("/welcome")
    public Map<String, Object> welcome() {
        Map<String, Object> map = new HashMap<String, Object>();
        String userId = getLoginUserId();
        map.put("userid", userId);
        return map;
    }

}
