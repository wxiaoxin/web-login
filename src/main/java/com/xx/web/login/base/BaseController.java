package com.xx.web.login.base;

import com.alibaba.fastjson.JSONObject;
import com.xx.web.login.constant.CookieConstant;
import com.xx.web.login.constant.SessionConstant;
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
     *
     * @param req  http请求
     * @param resp http响应
     */
    @ModelAttribute
    protected void setHttpReqAndResp(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    /**
     * 判断用户是否已登陆
     */
    protected boolean isLogin() {
        /**
         * 当且仅当cookie中和sesion中保存的用户身份信息一致时，才判定当前用户合法登陆成功
         * 但是，当非法劫持session和伪造cookie时，该验证安全性丧失
         */
        String userId = String.valueOf(req.getSession().getAttribute(SessionConstant.NAME));
        if (userId != null && !userId.equals("")) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 当cookie中的id与session中的id一致时，判定当前用户已登陆
                    if (cookie.getName().equals(CookieConstant.WY_USER) && cookie.getValue().equals(userId)) {
                        return true;
                    }
                }
            }
        }
        // 否则，判定用户尚未登陆
        return false;
    }

    /**
     * 从session中获取登陆用户的id
     *
     * @return
     */
    protected String getLoginUserId() {
        return String.valueOf(req.getSession().getAttribute(SessionConstant.NAME));
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
