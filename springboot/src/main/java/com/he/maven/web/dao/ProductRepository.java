package com.he.maven.web.dao;

import com.he.maven.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by heyanjing on 2018/9/2 17:02.
 */
public interface ProductRepository extends JpaRepository<Product, String> {

    Product getById(String id);

}
