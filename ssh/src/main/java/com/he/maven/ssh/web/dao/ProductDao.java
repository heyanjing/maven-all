package com.he.maven.ssh.web.dao;

import com.he.maven.core.bean.PageObject;
import com.he.maven.hibernate.dao.Dao;
import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;

import java.util.List;

/**
 *
 * @author heyanjing
 * @date 2018/8/21 10:02
 */

public interface ProductDao extends Dao<Product, String> {

    List<Product> findBySql();

    PageObject<Product> pageBySql(Integer pageSize, Integer pageIndex);

    List<Person> findEntityBySql();

    PageObject<Person> pageEntityBySql(Integer pageSize,Integer pageIndex);
}
