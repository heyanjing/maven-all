package com.he.maven.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author heyanjing
 * @date 2017/12/16 14:29
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping(value = {"", "/"})
    public String index(HttpServletRequest request) {
        return "/index";
    }

}
