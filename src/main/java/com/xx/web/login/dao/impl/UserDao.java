package com.xx.web.login.dao.impl;

import com.xx.web.login.base.BaseDao;
import com.xx.web.login.dao.IUserDao;
import com.xx.web.login.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/10/17.
 *
 * 用户操作实现类
 */

@Repository
public class UserDao extends BaseDao implements IUserDao {


    public void save(User user) {
        String sql = "insert into t_user values(?,?,?,?)";
        jt.update(sql, new Object[]{user.getId(), user.getUsername(), user.getPassword(), user.getCreateTime()});
    }

    public User get(String id) {
        String sql = "select * from t_user where id = ? ";
        return jt.query(sql, new Object[]{id}, new ResultSetExtractor<User>() {
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setCreateTime(rs.getDate("create_time"));
                    return user;
                } else {
                    return null;
                }
            }
        });
    }

    public User getByName(String name) {
        String sql = "select * from t_user where username = ? ";
        return jt.query(sql, new Object[]{name}, new ResultSetExtractor<User>() {
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setCreateTime(rs.getDate("create_time"));
                    return user;
                } else {
                    return null;
                }
            }
        });
    }
}
