package com.xx.web.login.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 基础控制器
 */

public class BaseController {

    protected HttpServletRequest req;

    protected HttpServletResponse resp;

    protected JSONObject result = new JSONObject();


    /**
     * 注入Http请求和响应对象
     * @param req http请求
     * @param resp http响应
     */
    @ModelAttribute
    protected void setHttpReqAndResp(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }


    /**
     * 从cookie中获取登陆用户的id
     * @return 登陆用户的id
     */
    protected String getUserId() {
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("WY_USER")) {
                return cookie.getValue();
            }
        }
        return "";
    }

    /**
     * 从session中获取登陆用户的id
     * @return
     */
    protected String getLoginUserId() {
        return String.valueOf(req.getSession().getAttribute("LOGIN_USER_ID"));
    }


    protected void success() {
        result.put("state", true);
    }

    protected void success(String message) {
        success();
        result.put("message", message);
    }

    protected void error() {
        result.put("state", false);
    }

    protected void error(String message) {
        error();
        result.put("message", message);
    }

}
