package com.he.maven.ssh;

import com.he.maven.ssh.bean.PageObject;
import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;
import com.he.maven.ssh.web.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on0018/8/21 10:16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-base*.xml"})
@Slf4j
public class FrameTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void saveForBatch() {
        LocalDateTime now = LocalDateTime.now();

        List<Person> list2 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            //product.setName("name" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            Person person = new Person();
            person.setName("personName" + i);
            person.setAge(i);
            person.setAddr("地址" + i);
            person.setShowOrder(i);
            person.setState(i);
            person.setCreatDateTime(now.plusHours(i));
            list2.add(person);
        }
        List<String> personIdList = this.productDao.saveForBatch(list2);
        List<Product> list1 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            //product.setName("name" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            Product product = new Product();
            product.setName("productName" + i);
            product.setPrice(i + 1.0);
            product.setQuantity(i + 1L);
            product.setShowOrder(i);
            product.setState(i);
            product.setPersonId(personIdList.get(0));
            product.setCreatDateTime(now.plusHours(i));
            list1.add(product);
        }
        this.productDao.saveForBatch(list1);
    }

    @Test
    public void getById() {
        Product product = this.productDao.getById("c05e2d08-8d5a-4a48-9bc5-6b8abaa3c705");
        log.error("{}", product);
        Person person = this.productDao.getEntityById("c21f05d1-ddbd-4e59-90be-f63c56cfad5e", Person.class);
        log.error("{}", person);
    }

    @Test
    public void getBySql() {
        String sql = "select * from product where 1=1 and name='productName10'";

        Product list1 = this.productDao.getBySql(sql);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder =:showOrder";
        mapParams.put("showOrder", 10);
        Product list2 = this.productDao.getBySql(temp, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder = ?";
        listParams.add(10);
        Product list3 = this.productDao.getBySql(temp1, listParams);
        log.error("{}", list3);
        temp1 += " and state= ?";
        Product list4 = this.productDao.getBySql(temp1, 10, 10);
        log.error("{}", list4);
    }
    @Test
    public void pageBySql() {
        String sql = "select * from product where 1=1 ";
        Integer pageIndex = 0;
        Integer pageSize = 2;

        PageObject<Product> list1 = this.productDao.pageBySql(sql+" order by showOrder", pageSize, pageIndex);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        PageObject<Product> list2 = this.productDao.pageBySql(temp+" order by showOrder", pageSize, pageIndex, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        PageObject<Product> list3 = this.productDao.pageBySql(temp1+" order by showOrder", pageSize, pageIndex, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        PageObject<Product> list4 = this.productDao.pageBySql(temp1+" order by showOrder", pageSize, pageIndex, 10, 0);
        log.error("{}", list4);
    }
    @Test
    public void findBySql() {
        String sql = "select * from product where 1=1";

        List<Product> list1 = this.productDao.findBySql(sql+" order by showOrder");
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        List<Product> list2 = this.productDao.findBySql(temp+" order by showOrder", mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        List<Product> list3 = this.productDao.findBySql(temp1+" order by showOrder", listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        List<Product> list4 = this.productDao.findBySql(temp1+" order by showOrder", 10, 0);
        log.error("{}", list4);
    }

    @Test
    public void getMapBySql() {
        String sql = "select name,price from product where 1=1 and name like 'productName10%'";

        Map<String, Object> list1 = this.productDao.getMapBySql(sql+" order by showOrder");
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder = :showOrder";
        mapParams.put("showOrder", 10);
        Map<String, Object> list2 = this.productDao.getMapBySql(temp+" order by showOrder", mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder = ?";
        listParams.add(10);
        Map<String, Object> list3 = this.productDao.getMapBySql(temp1+" order by showOrder", listParams);
        log.error("{}", list3);
        temp1 += " and state=?";
        Map<String, Object> list4 = this.productDao.getMapBySql(temp1+" order by showOrder", 10, 10);
        log.error("{}", list4);
    }
    @Test
    public void pageMapBySql() {
        String sql = "select name,price from product where 1=1";
        Integer pageIndex = 0;
        Integer pageSize = 2;

        PageObject<Map<String, Object>> list1 = this.productDao.pageMapBySql(sql + " order by showOrder", pageSize, pageIndex);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        PageObject<Map<String, Object>> list2 = this.productDao.pageMapBySql(temp + " order by showOrder", pageSize, pageIndex, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        PageObject<Map<String, Object>> list3 = this.productDao.pageMapBySql(temp1 + " order by showOrder", pageSize, pageIndex, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        PageObject<Map<String, Object>> list4 = this.productDao.pageMapBySql(temp1 + " order by showOrder", pageSize, pageIndex, 10, 0);
        log.error("{}", list4);
    }
    @Test
    public void findMapBySql() {
        String sql = "select name,price from product where 1=1";

        List<Map<String, Object>> list1 = this.productDao.findMapBySql(sql+" order by showOrder");
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        List<Map<String, Object>> list2 = this.productDao.findMapBySql(temp+" order by showOrder", mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        List<Map<String, Object>> list3 = this.productDao.findMapBySql(temp1+" order by showOrder", listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        List<Map<String, Object>> list4 = this.productDao.findMapBySql(temp1+" order by showOrder", 10, 0);
        log.error("{}", list4);
    }
    @Test
    public void findStringBySql() {
        String sql = "select creatDateTime from product where 1=1";

        List<String> list1 = this.productDao.findStringBySql(sql+" order by showOrder");
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        List<String> list2 = this.productDao.findStringBySql(temp+" order by showOrder", mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        List<String> list3 = this.productDao.findStringBySql(temp1+" order by showOrder", listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        List<String> list4 = this.productDao.findStringBySql(temp1+" order by showOrder", 10, 0);
        log.error("{}", list4);
    }
    @Test
    public void pageEntityBySql() {
        String sql = "select * from person where 1=1";
        Integer pageIndex = 0;
        Integer pageSize = 2;
        PageObject<Person> list1 = this.productDao.pageEntityBySql(sql + " order by showOrder", Person.class, pageSize, pageIndex);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        PageObject<Person> list2 = this.productDao.pageEntityBySql(temp + " order by showOrder", Person.class, pageSize, pageIndex, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        PageObject<Person> list3 = this.productDao.pageEntityBySql(temp1 + " order by showOrder", Person.class, pageSize, pageIndex, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        PageObject<Person> list4 = this.productDao.pageEntityBySql(temp1 + " order by showOrder", Person.class, pageSize, pageIndex, 10, 0);
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
        mapParams.put("showOrder", 10);
        List<Person> list2 = this.productDao.findEntityBySql(temp, Person.class, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        List<Person> list3 = this.productDao.findEntityBySql(temp1, Person.class, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        List<Person> list4 = this.productDao.findEntityBySql(temp1, Person.class, 10, 0);
        log.error("{}", list4);
    }

    @Test
    public void getByHql() {
        String sql = "from Product where 1=1 and name like 'productName10%'";

        Product list1 = this.productDao.getByHql(sql);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder =:showOrder";
        mapParams.put("showOrder", 10);
        Product list2 = this.productDao.getByHql(temp, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder = ?1";
        listParams.add(10);
        Product list3 = this.productDao.getByHql(temp1, listParams);
        log.error("{}", list3);
        temp1 += " and state= ?2";
        Product list4 = this.productDao.getByHql(temp1, 10, 10);
        log.error("{}", list4);
    }

    @Test
    public void findByHql() {
        String sql = "from Product where 1=1";

        List<Product> list1 = this.productDao.findByHql(sql+ " order by showOrder");
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        List<Product> list2 = this.productDao.findByHql(temp+ " order by showOrder", mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?1";
        listParams.add(10);
        List<Product> list3 = this.productDao.findByHql(temp1+ " order by showOrder", listParams);
        log.error("{}", list3);
        temp1 += " and state> ?2";
        List<Product> list4 = this.productDao.findByHql(temp1+ " order by showOrder", 10, 0);
        log.error("{}", list4);
    }



    @Test
    public void count() {
        String sql = "select count(*) from product where 1=1";

        long list1 = this.productDao.count(sql);
        log.error("{}", list1);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        long list2 = this.productDao.count(temp, mapParams);
        log.error("{}", list2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        long list3 = this.productDao.count(temp1, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        long list4 = this.productDao.count(temp1, 10, 0);
        log.error("{}", list4);
    }

    @Test
    public void executeBySql() {
        String sql = "update product set state=state+1 where 1=1 ";
        int i = this.productDao.executeBySql(sql);
        log.error("{}", i);
        Map<String, Object> mapParams = new HashMap<>();
        String temp = sql;
        temp += " and showOrder < :showOrder";
        mapParams.put("showOrder", 10);
        int i2 = this.productDao.executeBySql(temp, mapParams);
        log.error("{}", i2);
        List<Object> listParams = new ArrayList<>();
        String temp1 = sql;
        temp1 += " and showOrder < ?";
        listParams.add(10);
        int list3 = this.productDao.executeBySql(temp1, listParams);
        log.error("{}", list3);
        temp1 += " and state> ?";
        int list4 = this.productDao.executeBySql(temp1, 10, 0);
        log.error("{}", list4);
    }







    @Test
    public void test() {
        this.productDao.temp();
    }
}