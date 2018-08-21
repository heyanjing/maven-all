package com.he.maven.ssh.web.service;

import com.he.maven.ssh.web.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by heyanjing on 2018/8/21 10:14.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;


}
