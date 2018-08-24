package com.he.maven.ssh.web.controller;

import com.he.maven.core.bean.PageObject;
import com.he.maven.core.web.Webs;
import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;
import com.he.maven.ssh.web.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by heyanjing on 2017/12/16 14:29.
 */
@Controller
@Slf4j
@SuppressWarnings({"unused"})
public class IndexController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"", "/"})
    public String index() {
        log.warn("{}", "访问index方法");
        return "/index";
    }

    @PostMapping("/login")
    public String login(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
        if ("admin".equals(userName) && "admin".equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", new Person("admin", 28, "重庆", 1));
            return Webs.redirect("/home/");
        }
        return Webs.redirect("/");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        log.warn("{}", session.getId());
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info("{}", cookie.getName());
            log.info("{}", cookie.getValue());
            log.info("{}", cookie.getComment());
            log.info("{}", cookie.getDomain());
            log.info("{}", cookie.getMaxAge());
            log.info("{}", cookie.getPath());
            log.info("{}", cookie.getSecure());
            log.info("{}", cookie.getVersion());
            if ("JSESSIONID".equals(cookie.getName())) {
                //删除 session Id
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        session.removeAttribute("user");
        return Webs.redirect("/");
    }

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

}
