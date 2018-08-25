package com.he.maven.ssh.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by heyanjing on 2017/12/16 14:29.
 */
@Controller
@Slf4j
@SuppressWarnings({"unused"})
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "/home";
    }
    @GetMapping("/temp")
    public String temp() {
        return "/temp";
    }

}
