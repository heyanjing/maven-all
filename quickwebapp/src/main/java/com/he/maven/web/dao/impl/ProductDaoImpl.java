package com.he.maven.web.dao.impl;

import com.he.maven.core.bean.PageObject;
import com.he.maven.entity.Person;
import com.he.maven.entity.Product;
import com.he.maven.hibernate.dao.impl.DaoImpl;
import com.he.maven.web.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author heyanjing
 * @date 2018/8/21 10:02
 */

@Slf4j
@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductDaoImpl extends DaoImpl<Product, String> implements ProductDao {

    @Override
    public List<Product> findBySql() {
        String sql = "select * from product order by showorder";
        return super.findBySql(sql);
    }

    @Override
    public PageObject<Product> pageBySql(Integer pageSize, Integer pageIndex) {
        String sql = "select * from product order by showorder";
        return super.pageBySql(sql, pageSize, pageIndex);
    }

    @Override
    public List<Person> findEntityBySql() {
        String sql = "select * from person order by showorder";
        return super.findEntityBySql(sql, Person.class);
    }

    @Override
    public PageObject<Person> pageEntityBySql(Integer pageSize, Integer pageIndex) {
        String sql = "select * from person order by showorder";
        return super.pageEntityBySql(sql, Person.class, pageSize, pageIndex);
    }
}
