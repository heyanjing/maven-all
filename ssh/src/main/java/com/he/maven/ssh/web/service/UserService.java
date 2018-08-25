package com.he.maven.ssh.web.service;

import com.he.maven.core.bean.Result;
import com.he.maven.ssh.entity.User;
import com.he.maven.ssh.web.dao.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by heyanjing on 2018/8/21 10:14.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Result login(String userName, String password, String sessionId) {
        Result result = Result.failure();
        User user = this.userDao.getByUserName(userName);
        if (user.getPassword().equals(password)) {
            String loginSessionId = user.getSessionId();
            Integer isLogined = user.getIsLogined();
            //未登录
            if (StringUtils.isBlank(loginSessionId) && isLogined == null) {
                // 修改用户的isLogined和sessionId
                user.setIsLogined(101);
                user.setSessionId(sessionId);
                //hibernate 查询出来的数据有粘性，不调用下面的方法一样能自动保存IsLogined 和 SessionId
                this.userDao.saveOrUpdate(user);
                //this.userDao.updateSessionIdAndIsLoginedById(user.getId(), sessionId, 101);
                result.setData(user);
                result.setSuccess(true);
            } else {
                result.setMsg("用户已登录");
            }
        } else {
            result.setMsg("用户名密码不正确");
        }
        return result;
    }

    public User getByUserName(String userName) {
        return this.userDao.getByUserName(userName);
    }

}
