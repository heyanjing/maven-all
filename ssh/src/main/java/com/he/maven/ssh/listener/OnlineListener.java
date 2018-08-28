package com.he.maven.ssh.listener;

import com.he.maven.core.time.Times;
import com.he.maven.core.web.Springs;
import com.he.maven.ssh.Constant;
import com.he.maven.ssh.entity.User;
import com.he.maven.ssh.web.dao.LoginInfoDao;
import com.he.maven.ssh.web.dao.UserDao;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author heyanjing
 * @date 2018/8/25 10:28
 * 监听用户session销毁时，改变用户的登录信息
 */
@Slf4j
public class OnlineListener implements HttpSessionListener {
    private static UserDao userDao = null;
    private static LoginInfoDao loginInfoDao = null;

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
        if (loginInfoDao == null) {
            loginInfoDao = Springs.getBean("loginInfoDaoImpl");
        }
        HttpSession session = se.getSession();
        log.error("销毁session  sessionId:{}    时间:{}", session.getId(), Times.parseMillisecond2LocalDateTime(session.getCreationTime()));
        User user = (User) session.getAttribute(Constant.USER_SESSION_KEY);
        if (userDao != null && user != null) {
            long l = loginInfoDao.countByUserId(user.getId());
            loginInfoDao.deleteBySessionId(session.getId());
            if (l == 1L) {
                userDao.updateIsLoginedById(user.getId(), null);
            }
        }
    }
}
