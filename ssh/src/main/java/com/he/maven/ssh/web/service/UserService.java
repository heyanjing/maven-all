package com.he.maven.ssh.web.service;

import com.he.maven.core.bean.Result;
import com.he.maven.ssh.Constant;
import com.he.maven.ssh.entity.LoginedInfo;
import com.he.maven.ssh.entity.User;
import com.he.maven.ssh.web.dao.LoginInfoDao;
import com.he.maven.ssh.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by heyanjing on 2018/8/21 10:14.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginInfoDao loginInfoDao;

    public Result login(String userName, String password, String sessionId) {
        Result result = Result.failure();
        User user = this.userDao.getByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            Integer isLogined = user.getIsLogined();
            String userId = user.getId();
            if (isLogined == null) {
                // 未登录 修改用户的isLogined
                user.setIsLogined(101);
                this.userDao.saveOrUpdate(user);
            } else {
                //已登录 检查是否超过人数限制，怎么踢人
                List<LoginedInfo> loginedList = this.loginInfoDao.findByUserId(userId);
                if (loginedList.size() >= Constant.USER_ALLOW_NUM) {
                    if (Constant.USER_KICKOUT_BEFORE) {
                        //查询第一条logined
                        LoginedInfo first = loginedList.get(0);
                        first.setKickout("yes");
                        this.loginInfoDao.saveOrUpdate(first);
                    } else {
                        //result.setCode(409);
                        result.setMsg("该账号登录人数已达上线");
                        return result;
                    }
                }
            }
            LoginedInfo logined = new LoginedInfo();
            logined.setCreateDateTime(LocalDateTime.now());
            logined.setUserId(userId);
            logined.setSessionId(sessionId);
            this.loginInfoDao.save(logined);
            result.setData(user);
            result.setSuccess(true);
        } else {
            result.setMsg("用户名或密码不正确");
        }
        return result;
    }

    public User getByUserName(String userName) {
        return this.userDao.getByUserName(userName);
    }

}
