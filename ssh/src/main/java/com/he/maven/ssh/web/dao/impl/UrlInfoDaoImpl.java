package com.he.maven.ssh.web.dao.impl;

import com.he.maven.hibernate.dao.impl.DaoImpl;
import com.he.maven.ssh.entity.UrlInfo;
import com.he.maven.ssh.web.dao.UrlInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author heyanjing
 * @date 2018/8/28 14:00
 */

@Slf4j
@Repository
@Transactional(rollbackFor = Exception.class)
public class UrlInfoDaoImpl extends DaoImpl<UrlInfo, String> implements UrlInfoDao {

}
