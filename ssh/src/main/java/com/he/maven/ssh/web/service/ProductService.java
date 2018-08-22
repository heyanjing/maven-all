package com.he.maven.ssh.web.service;

import com.he.maven.ssh.bean.PageObject;
import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;
import com.he.maven.ssh.web.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by heyanjing on 2018/8/21 10:14.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> findBySql() {
        return this.productDao.findBySql();
    }

    public PageObject<Product> pageBySql(Integer pageSize, Integer pageIndex) {
        return this.productDao.pageBySql(pageSize, pageIndex);
    }

    public List<Person> findEntityBySql() {
        return this.productDao.findEntityBySql();
    }

    public PageObject<Person> pageEntityBySql(Integer pageSize, Integer pageIndex) {
        return this.productDao.pageEntityBySql(pageSize, pageIndex);
    }
}
