package com.xx.web.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wxiao on 2016.10.19.
 *
 * 表单校验实例页
 */

@Controller
public class ValidateController {


    @RequestMapping("/validate")
    public String validate() {
        return "validate";
    }


}
