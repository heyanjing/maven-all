package com.he.maven.ssh.web.dao.impl;

import com.he.maven.ssh.entity.Product;
import com.he.maven.ssh.web.dao.ProductDao;
import com.he.maven.ssh.web.dao.base.impl.DaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by heyanjing on 2018/8/21 10:02.
 */

@Repository
@Transactional
@Slf4j
public class ProductDaoImpl extends DaoImpl<Product,String> implements ProductDao {
}
