package com.he.maven.ssh.web.controller;

import com.he.maven.core.bean.PageObject;
import com.he.maven.core.bean.Result;
import com.he.maven.core.web.Webs;
import com.he.maven.ssh.Constant;
import com.he.maven.ssh.entity.Person;
import com.he.maven.ssh.entity.Product;
import com.he.maven.ssh.web.service.ProductService;
import com.he.maven.ssh.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/"})
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(Constant.USER_SESSION) != null) {
            session.invalidate();
        }
        return "/index";
    }

    @PostMapping("/login")
    public String login(String userName, String password, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        HttpSession session = request.getSession();
        Result result = this.userService.login(userName, password, session.getId());
        if (result.isSuccess()) {
            session.setAttribute(Constant.USER_SESSION, result.getData());
            Cookie c = new Cookie("namex", "何彦静");
            //单位秒
            c.setMaxAge(10);
            response.addCookie(c);
            return Webs.redirect("/home/");
        }
        attributes.addAttribute("msg", result.getMsg());
        return Webs.redirect("/");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        //Cookie[] cookies = request.getCookies();
        //for (Cookie cookie : cookies) {
        //    log.debug("{}", cookie.getName());
        //    log.debug("{}", cookie.getValue());
        //    log.debug("{}", cookie.getComment());
        //    log.debug("{}", cookie.getDomain());
        //    log.debug("{}", cookie.getMaxAge());
        //    log.debug("{}", cookie.getPath());
        //    log.debug("{}", cookie.getSecure());
        //    log.debug("{}", cookie.getVersion());
        //    if ("JSESSIONID".equals(cookie.getName())) {
        //        //删除 session Id
        //        cookie.setMaxAge(0);
        //        response.addCookie(cookie);
        //    }
        //    //测试用途  删除所有cookie
        //    cookie.setMaxAge(0);
        //    response.addCookie(cookie);
        //}
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
