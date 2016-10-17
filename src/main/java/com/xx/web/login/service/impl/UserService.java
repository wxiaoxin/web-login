package com.xx.web.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xx.web.login.dao.IUserDao;
import com.xx.web.login.entity.User;
import com.xx.web.login.service.IUserService;
import com.xx.web.login.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 用户服务实现类
 */

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    public Map<String, Object> login(String name, String password) {
        User user = userDao.getByName(name.trim());
        Map<String, Object> result = new HashMap<String, Object>();
        if (user != null) {
            if (user.getPassword().equals(password)) {
                // 用户名和密码匹配 登陆成功
                result.put("stateCode", 1);
                result.put("message", "登陆成功");
                result.put("user", user);
            } else {
                // 用户名和密码不一致 登陆失败
                result.put("stateCode", 2);
                result.put("mesage", "用户名和密码不一致");
                result.put("user", null);
            }
        } else {
            // 用户不存在
            result.put("stateCode", -1);
            result.put("mesage", "用户名不存在");
            result.put("user", null);
        }
        return result;
    }

    /**
     * 设置响应的Cookie
     * @param user 用户
     * @param autoLogin 自动登陆
     * @param resp http响应
     */
    public void setCookie(User user, boolean autoLogin, HttpServletResponse resp) {
        // 设置身份Cookie
        Cookie userIdCookie = new Cookie("userid", JSONObject.toJSONString(user));
        resp.addCookie(userIdCookie);
        if(autoLogin) {
            // 设置自动登陆的Cookie
            Cookie autoLoginCookie = new Cookie("autoLogin", autoLogin ? "true":"false");
            // 设置自动登陆有效时间 5分钟 * 60秒
            autoLoginCookie.setMaxAge(5 * 60);
            resp.addCookie(autoLoginCookie);
        }
    }

}
