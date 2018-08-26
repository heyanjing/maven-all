package com.he.maven.ssh.web.dao.impl;

import com.he.maven.hibernate.dao.impl.DaoImpl;
import com.he.maven.ssh.entity.LoginedInfo;
import com.he.maven.ssh.web.dao.LoginInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by heyanjing on 2018/8/21 10:02.
 */

@Repository
@Transactional
@Slf4j
public class LoginInfoDaoImpl extends DaoImpl<LoginedInfo, String> implements LoginInfoDao {

    @Override
    public List<LoginedInfo> findByUserId(String userId) {
        String sql = "select * from LoginedInfo where userId=? order by createDateTime";
        return super.findBySql(sql, userId);
    }

    @Override
    public LoginedInfo getBySessionId(String sessionId) {
        String sql = "select * from LoginedInfo where sessionId=?";
        return super.getBySql(sql, sessionId);
    }

    @Override
    public Integer deleteBySessionId(String sessionId) {
        String sql = "delete from LoginedInfo where sessionId=?";
        return super.executeBySql(sql, sessionId);
    }

    @Override
    public long countByUserId(String userId) {
        String sql = "select count(*) from LoginedInfo where userId=?";
        return super.count(sql, userId);
    }

    @Override
    public LoginedInfo getFirst() {
        String sql = "select * from LoginedInfo p where p.createDateTime=(select min(createDateTime) from  LoginedInfo)";
        return super.getBySql(sql);
    }

    @Override
    public LoginedInfo getEnd() {
        String sql = "select * from LoginedInfo p where p.createDateTime=(select max(createDateTime) from  LoginedInfo)";
        return super.getBySql(sql);
    }
}
