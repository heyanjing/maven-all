package com.he.maven.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heyanjing on 2018/9/1 21:21.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("host", "127.0.0.1");
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        model.addAttribute("list", list);
        return "index";
    }

    @Value("${pocket.name}")
    private String name;

    @Value("${pocket.age}")
    private Integer age;

    @Value("${pocket.address}")
    private String address;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "大家好，我的名字是" + name + "，我今年" + age + "岁了，我在" + address + "工作！";
    }
}
