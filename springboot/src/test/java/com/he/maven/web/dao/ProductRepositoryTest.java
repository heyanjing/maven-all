package com.he.maven.web.dao;

import com.he.maven.Application;
import com.he.maven.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by heyanjing on 2018/9/2 17:02.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void test() {
        Product product = new Product();
        product.setName("springboot");
        productRepository.save(product);

        List<Product> all = productRepository.findAll();
    }
    @Test
    public void test2() {
        Product one = this.productRepository.getById("f8d54433-ed24-46b2-8533-2f90cb500405");
        log.warn("{}",one);
    }

}