package com.he.maven.web.controller;

import com.he.maven.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by heyanjing on 2018/9/1 21:21.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestBean testBean;

    @RequestMapping("/")
    public TestBean index() {
        return testBean;
    }
}
