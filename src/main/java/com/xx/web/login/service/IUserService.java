package com.xx.web.login.service;

import com.xx.web.login.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 用户服务接口
 */

public interface IUserService {

    /**
     * 登陆
     *
     * @param name     用户名
     * @param password 密码
     */
    Map<String, Object> login(String name, String password);


    /**
     * 设置响应的Cookie
     *
     * @param user      用户
     * @param autoLogin 自动登陆
     * @param resp      http响应
     */
    void setCookie(User user, boolean autoLogin, HttpServletResponse resp);

}
