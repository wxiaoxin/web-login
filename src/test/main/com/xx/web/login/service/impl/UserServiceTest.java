package com.xx.web.login.service.impl;

import com.xx.web.login.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/10/17.
 */

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testLogin() throws Exception {
        Map<String, Object> login = userService.login("ab", "ab");
        System.out.println(login);
    }
}