package com.he.maven.ssh.web.dao.impl;

import com.he.maven.hibernate.dao.impl.DaoImpl;
import com.he.maven.ssh.entity.User;
import com.he.maven.ssh.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author heyanjing
 * @date 2018/8/21 10:02
 */

@Slf4j
@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl extends DaoImpl<User, String> implements UserDao {

    @Override
    public User getByUserName(String userName) {
        String sql = "select * from user where userName=?";
        return super.getBySql(sql, userName);
    }

    @Override
    public Integer updateIsLoginedById(String id, Integer isLogined) {
        String sql = "update  user set islogined=? where id=?";
        return super.executeBySql(sql, isLogined, id);
    }
}
