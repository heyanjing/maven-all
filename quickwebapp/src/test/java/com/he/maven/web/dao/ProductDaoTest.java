package com.he.maven.web.dao;

import com.he.maven.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author heyanjing
 * @date 2018/8/29 14:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-base*.xml"})
@Slf4j
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;

    @Test
    public void test() {
        List<Product> list = this.productDao.findBySql();
        log.info("{}", list);
    }

}