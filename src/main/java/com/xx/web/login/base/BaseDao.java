package com.xx.web.login.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Administrator on 2016/10/17.
 *
 * DAO的基础类
 */

public class BaseDao {

    @Autowired
    protected JdbcTemplate jt;

}
