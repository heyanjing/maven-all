package com.he.maven.web.controller;

import com.he.maven.core.bean.Result;
import com.he.maven.entity.Product;
import com.he.maven.web.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heyanjing on 2018/9/1 21:21.
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public Product index() {
        log.warn("xxxx");
        return this.productService.getById("f8d54433-ed24-46b2-8533-2f90cb500405");
    }
    @RequestMapping("/transactional")
    public Result transactional() {
        return this.productService.save();
    }
}
