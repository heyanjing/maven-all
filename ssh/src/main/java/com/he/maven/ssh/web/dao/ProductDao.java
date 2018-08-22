package com.he.maven.ssh.web.dao;

import com.he.maven.ssh.bean.PageObject;
import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;
import com.he.maven.ssh.web.dao.base.Dao;

import java.util.List;

/**
 * Created by heyanjing on 2018/8/21 10:02.
 */

public interface ProductDao extends Dao<Product, String> {

    List<Product> findBySql();

    PageObject<Product> pageBySql(Integer pageSize,Integer pageIndex);

    List<Person> findEntityBySql();

    PageObject<Person> pageEntityBySql(Integer pageSize,Integer pageIndex);
}
