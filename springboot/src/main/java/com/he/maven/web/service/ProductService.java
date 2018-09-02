package com.he.maven.web.service;

import com.he.maven.core.bean.Result;
import com.he.maven.entity.Product;
import com.he.maven.web.dao.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author heyanjing
 * @date 2018/8/21 10:14
 */
@Slf4j
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getById(String id) {
        return this.productRepository.getById(id);
    }

    public Result save() {
        Result result = Result.success();
        for (int i = 0; i < 3; i++) {
            Product p = new Product();
            p.setName("springboot" + i);
            if (i == 2) {
                result.setSuccess(false);
                throw new RuntimeException("出错了");
            }
            this.productRepository.save(p);
        }
        return result;
    }
}