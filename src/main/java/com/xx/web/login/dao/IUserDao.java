package com.xx.web.login.dao;

import com.xx.web.login.entity.User;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 用户类操作接口
 */

public interface IUserDao {

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 获取
     * @param id    用户id
     * @return
     */
    User get(String id);


    /**
     * 通过用户名获取
     * @param name  用户名
     * @return
     */
    User getByName(String name);

}
