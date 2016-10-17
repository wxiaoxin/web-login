package com.xx.web.login.controller;

import com.xx.web.login.base.BaseController;
import com.xx.web.login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 首页控制器
 */

@Controller
public class IndexController extends BaseController {


    @Autowired
    private IUserService userService;


    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        // 判断用户是否已经登录
        if (!isLogin()) {
            // 如果用户尚未登陆，则根据cookie判断是否自动登陆
            if (userService.autoLogin(req, resp)) {
                // 自动登陆成功，则自动跳转到首页
                String userId = getLoginUserId();
                mv.addObject("userid", userId);
                mv.setViewName("index");
            } else {
                // 用户尚未登陆，则无法自动登陆，则跳转到登陆页
                mv.setViewName("login");
            }
        } else {
            mv.setViewName("index");
            // 自动登陆成功，则自动跳转到首页
            mv.addObject("userid", getLoginUserId());
            mv.setViewName("index");
        }
        return mv;
    }

}

