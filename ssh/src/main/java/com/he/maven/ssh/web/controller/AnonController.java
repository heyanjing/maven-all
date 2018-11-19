package com.he.maven.ssh.web.controller;

import com.he.maven.core.bean.Result;
import com.he.maven.ssh.bean.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author heyanjing
 * @date 2017/12/16 14:29
 */
@Slf4j
@Controller
@RequestMapping("/anon")
public class AnonController {
    @RequestMapping("/test")
    @ResponseBody
    public TestBean test(TestBean bean) {
        return bean;
    }

    /**
     */
    @RequestMapping("/result")
    @ResponseBody
    public Result result() {
        return Result.success();
    }

}
