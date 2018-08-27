package com.he.maven.ssh.web.controller;

import com.he.maven.core.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author heyanjing
 * @date 2017/12/16 14:29
 */
@Slf4j
@Controller
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

    /**
     * @return
     */
    @RequestMapping("/result")
    @ResponseBody
    public Result result() {
        return Result.success();
    }

}
