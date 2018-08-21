package com.he.maven.ssh.web.dao;

import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2018/8/21 10:16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-base*.xml"})
@Slf4j
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void saveForBatch() {
        List<Product> list1 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            //product.setName("name" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            Product product = new Product();
            product.setName("name" + i);
            product.setPrice(i + 1.0);
            product.setQuantity(i + 1L);
            product.setShowOrder(i);
            product.setState(i);
            list1.add(product);
        }
        List<Person> list2 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            //product.setName("name" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            Person person = new Person();
            person.setName("name" + i);
            person.setAge(i);
            person.setAddr("地址" + i);
            person.setShowOrder(i);
            person.setState(i);
            list2.add(person);
        }
        this.productDao.saveForBatch(list1);
        this.productDao.saveForBatch(list2);
    }

    @Test
    public void getById() {
        Product product = this.productDao.getById("7b2d86e2-8235-4f36-9d68-199135dff017");
        log.error("{}",product);
        Person person = this.productDao.getEntityById("da4a7dc2-fe92-4ed1-99ef-9f52e5c70ede",Person.class);
        log.error("{}",person);
    }
    @Test
    public void findBySql() {
        String sql = "select * from product where 1=1";

        List<Product> list1 = this.productDao.findBySql(sql);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 5);
        List<Product> list2 = this.productDao.findBySql(temp, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(5);
        List<Product> list3 = this.productDao.findBySql(temp1, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        List<Product> list4 = this.productDao.findBySql(temp1, 5,2);
        log.error("{}", list4);
    }

    @Test
    public void findEntityBySql() {
        String sql = "select * from person where 1=1";

        List<Person> list1 = this.productDao.findEntityBySql(sql, Person.class);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 5);
        List<Person> list2 = this.productDao.findEntityBySql(temp, Person.class, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(5);
        List<Person> list3 = this.productDao.findEntityBySql(temp1, Person.class, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        List<Person> list4 = this.productDao.findEntityBySql(temp1, Person.class, 5,2);
        log.error("{}", list4);
    }

    @Test
    public void findByHql() {
        String sql = "from Product where 1=1";

        List<Product> list1 = this.productDao.findByHql(sql);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 5);
        List<Product> list2 = this.productDao.findByHql(temp, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?1";
        listParams.add(5);
        List<Product> list3 = this.productDao.findByHql(temp1, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?2";
        List<Product> list4 = this.productDao.findByHql(temp1, 5,2);
        log.error("{}", list4);
    }
}