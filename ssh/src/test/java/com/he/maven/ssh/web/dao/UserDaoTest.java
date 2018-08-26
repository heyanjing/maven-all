package com.he.maven.ssh.web.dao;

import com.he.maven.ssh.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by heyanjing on 2018/8/25 11:03.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-base*.xml"})
@Slf4j
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
        User admin = new User("admin", "admin", null);
        User heyanjing = new User("heyanjing", "admin", null);
        this.userDao.save(admin);
        this.userDao.save(heyanjing);
    }
}