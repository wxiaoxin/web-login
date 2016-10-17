package com.xx.web.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 用户服务接口
 */

public interface IUserService {

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
    Map<String, Object> login(String name, String password, boolean autoLogin, HttpServletRequest req, HttpServletResponse resp);

    /**
     * 自动登陆
     *
     * @param req http请求
     * @return 自动的登陆结果：true-自动登陆成功，false-自动登陆失败
     */
    boolean autoLogin(HttpServletRequest req, HttpServletResponse resp);

    /**
     * 清除登陆cookie
     *
     * @param req http请求
     */
    void clearCookie(HttpServletRequest req);

    /**
     * 清楚session
     *
     * @param req http请求
     */
    void invalidSession(HttpServletRequest req);

}
