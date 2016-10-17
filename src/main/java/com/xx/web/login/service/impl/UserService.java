package com.xx.web.login.service.impl;

import com.xx.web.login.constant.CookieConstant;
import com.xx.web.login.constant.SessionConstant;
import com.xx.web.login.dao.IUserDao;
import com.xx.web.login.entity.User;
import com.xx.web.login.service.IUserService;
import com.xx.web.login.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.CollationKey;
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

    /**
     * 用户登录
     *
     * @param name      用户名
     * @param password  密码
     * @param autoLogin 是否自动登陆
     * @param req       http请求
     * @param resp      http响应
     * @return 登陆结果
     */
    public Map<String, Object> login(String name, String password, boolean autoLogin, HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userDao.getByName(name.trim());
        if (user != null) {
            if (user.getPassword().equals(MD5Utils.MD5(password.trim()))) {
                // 用户名和密码匹配，登陆成功
                result.put("stateCode", 1);
                result.put("message", "登陆成功");
                result.put("user", user);
                // 设置Cookie
                setCookie(user, autoLogin, resp);
                // 设置session
                setSession(user, req);
            } else {
                // 用户名和密码不一致，登陆失败
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
     * 自动登陆
     *
     * @param req   http请求
     * @param resp  http响应
     * @return      自动的登陆结果：true-自动登陆成功，false-自动登陆失败
     */
    public boolean autoLogin(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CookieConstant.WY_USER_AL)) {
                    String value = cookie.getValue();
                    if (!value.equals("")) {
                        // 从Cookie中读取身份信息，进行自动登陆
                        String[] userInfo = value.split("-");
                        if (userInfo.length > 2) {
                            String userId = userInfo[0];
                            String userName = userInfo[1];
                            String password = userInfo[2];
                            if (!userName.equals("")) {
                                User user = userDao.getByName(userName);
                                if (user.getPassword().equals(password)) {
                                    // 设置cookie
                                    setCookie(user, true, resp);
                                    // 自动登陆成功，保存该用户信息到sesssion中
                                    setSession(user, req);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void clearCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(CookieConstant.WY_USER)) {
                // 设置cookie立即失效
                cookie.setMaxAge(-1);
            }
        }
    }

    @Override
    public void invalidSession(HttpServletRequest req) {
        req.getSession().invalidate();
    }

    /**
     * 设置响应的Cookie
     *
     * @param user 已成功登陆用户
     * @param autoLogin 是否自动登陆
     * @param resp      http响应
     */
    private void setCookie(User user, boolean autoLogin, HttpServletResponse resp) {
        // 设置自动登陆的Cookie
        if (autoLogin) {
            // 7天之内免登陆，则保存用户id，用户名和用户密码到cookie中
            Cookie auLoginCookie = new Cookie(CookieConstant.WY_USER_AL, user.getId() + "-" + user.getUsername() + "-" + user.getPassword());
            // 设置自动登陆有效时间7天
            auLoginCookie.setMaxAge(60 * 5);
            resp.addCookie(auLoginCookie);
        }
        // 登陆成功后即设置cookie中只保存用户的id，且生存周期默认至浏览器关闭
        Cookie userCookie = new Cookie(CookieConstant.WY_USER, user.getId());
        // 添加cookie
        resp.addCookie(userCookie);
    }


    /**
     * 设置会话session。将成功登陆的用户的id保存到服务器的session中
     *
     * @param user 已成功登陆的用户
     * @param req   http请求
     */
    private void setSession(User user, HttpServletRequest req) {
        // 将成功登陆的用户id保存进session中，以后每次请求都从session中读取用户的身份信息
        req.getSession().setAttribute(SessionConstant.NAME, user.getId());
        // 用户无操作时，session维持最长存活时间
        req.getSession().setMaxInactiveInterval(60 * 10);
    }



}
