package com.he.maven.ssh.web.dao;

import com.he.maven.hibernate.dao.Dao;
import com.he.maven.ssh.entity.LoginedInfo;

import java.util.List;

/**
 *
 * @author heyanjing
 * @date 2018/8/21 10:02
 */

public interface LoginInfoDao extends Dao<LoginedInfo, String> {

    List<LoginedInfo> findByUserId(String userId);

    Integer deleteBySessionId(String sessionId);

    long countByUserId(String userId);

    LoginedInfo getBySessionId(String sessionId);
    LoginedInfo getFirst();

    LoginedInfo getEnd();

}
