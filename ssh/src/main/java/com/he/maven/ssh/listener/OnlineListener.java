package com.he.maven.ssh.listener;

import com.he.maven.core.time.Times;
import com.he.maven.core.web.Springs;
import com.he.maven.ssh.Constant;
import com.he.maven.ssh.entity.User;
import com.he.maven.ssh.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by heyanjing on 2018/8/25 10:28.
 */
@Slf4j
public class OnlineListener implements HttpSessionListener {
    private UserDao userDao = null;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.warn("创建session  sessionId:{}    时间:{}", session.getId(), Times.parseMillisecond2LocalDateTime(session.getCreationTime()));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (userDao == null) {
            userDao = Springs.getBean("userDaoImpl");
        }
        HttpSession session = se.getSession();
        log.error("销毁session  sessionId:{}    时间:{}", session.getId(), Times.parseMillisecond2LocalDateTime(session.getCreationTime()));
        User user = (User) session.getAttribute(Constant.USER_SESSION);
        if (userDao != null && user != null) {
            userDao.updateSessionIdAndIsLoginedById(user.getId(), null, null);
        }
    }
}