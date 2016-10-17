package com.xx.web.login.interceptor;

import com.xx.web.login.constant.CookieConstant;
import com.xx.web.login.constant.SessionConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 全局拦截器
 */

public class GlobalInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 先判断session中是否保存了用户的登陆信息
        String userId = String.valueOf(request.getSession().getAttribute(SessionConstant.NAME));
        if (userId != null && !userId.equals("")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 当且仅当cookie中的id与session中的id一致时，判定当前用户已登陆，操作合法
                    if (cookie.getName().equals(CookieConstant.WY_USER) && cookie.getValue().equals(userId)) {
                        return true;
                    }
                }
            }
        }
        // 否则，判定用户尚未登陆，重定向到登陆页面
        response.sendRedirect("login.htm");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
