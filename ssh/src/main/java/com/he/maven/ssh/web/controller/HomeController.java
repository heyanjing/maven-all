package com.he.maven.ssh.web.controller;

import com.he.maven.core.bean.PageObject;
import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;
import com.he.maven.ssh.web.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author heyanjing
 * @date 2017/12/16 14:29
 */
@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/temp")
    public String temp() {
        return "/temp";
    }
    //*********************************************************测试用途*******************************************************************************************************************************

    @RequestMapping("/findBySql")
    @ResponseBody
    public List<Product> findBySql() {
        return this.productService.findBySql();
    }

    @RequestMapping("/pageBySql")
    @ResponseBody
    public PageObject<Product> pageBySql(@RequestParam(defaultValue = "2") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageIndex) {
        return this.productService.pageBySql(pageSize, pageIndex);
    }

    @RequestMapping("/findEntityBySql")
    @ResponseBody
    public List<Person> findEntityBySql() {
        return this.productService.findEntityBySql();
    }

    @RequestMapping("/pageEntityBySql")
    @ResponseBody
    public PageObject<Person> pageEntityBySql(@RequestParam(defaultValue = "2") Integer pageSize, @RequestParam(defaultValue = "0") Integer pageIndex) {
        return this.productService.pageEntityBySql(pageSize, pageIndex);
    }
    //*********************************************************测试用途*******************************************************************************************************************************
}
