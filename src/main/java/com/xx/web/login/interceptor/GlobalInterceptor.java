package com.xx.web.login.interceptor;

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

        // 遍历Cookie，判断请求是否合法
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("WY_USER")) {
//                String userId = cookie.getValue();
//                if (userId.equals("") || userId.equals("")) {
//                    response.sendRedirect("index.htm");
//                    return false;
//                }
//            }
//        }

        // 通过session判断请求是否合法
        Object userId = request.getSession().getAttribute("LOGIN_USER_ID");
        if(userId == null || String.valueOf(userId).equals("")) {
              response.sendRedirect("index.htm");
            return false;
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
