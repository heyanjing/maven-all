package com.he.maven.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heyanjing on 2018/9/1 21:21.
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }
    @Value("${pocket.name}")
    private String name;

    @Value("${pocket.age}")
    private Integer age;

    @Value("${pocket.address}")
    private String address;

    @GetMapping("/hello")
    public String hello() {
        return "大家好，我的名字是" + name + "，我今年" + age + "岁了，我在" + address+"工作！";
    }
}
