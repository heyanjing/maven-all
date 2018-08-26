package com.he.maven.ssh.web.dao;

import com.he.maven.hibernate.dao.Dao;
import com.he.maven.ssh.entity.User;

/**
 * Created by heyanjing on 2018/8/21 10:02.
 */

public interface UserDao extends Dao<User, String> {

    User getByUserName(String userName);

    Integer updateIsLoginedById(String id,Integer isLogined);
}
