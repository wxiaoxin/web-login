package com.xx.web.login.dao.impl;

import com.xx.web.login.dao.IUserDao;
import com.xx.web.login.entity.User;
import com.xx.web.login.util.MD5Utils;
import com.xx.web.login.util.UUIDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 用户操作测试类
 */

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {


    @Autowired
    private IUserDao userDao;

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setId(UUIDGenerator.randomUUID());
        user.setUsername("a");
        user.setPassword(MD5Utils.MD5("123456"));
        user.setCreateTime(new Date());
        userDao.save(user);
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testGetByName() throws Exception {

    }
}