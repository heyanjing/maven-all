package com.he.maven.ssh.web.controller;

import com.he.maven.core.bean.Result;
import com.he.maven.core.web.Webs;
import com.he.maven.ssh.Constant;
import com.he.maven.ssh.web.service.ProductService;
import com.he.maven.ssh.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author heyanjing
 * @date 2017/12/16 14:29
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/"})
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(Constant.USER_SESSION_KEY) != null) {
            session.invalidate();
        }
        return "/index";
    }

    @PostMapping("/login")
    public String login(String userName, String password, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        HttpSession session = request.getSession();
        Result result = this.userService.login(userName, password, session.getId());
        if (result.isSuccess()) {
            session.setAttribute(Constant.USER_SESSION_KEY, result.getData());
            Cookie c = new Cookie("namex", "何彦静");
            //单位秒
            c.setMaxAge(10);
            response.addCookie(c);
            return Webs.redirect("/home/");
        }
        //if (result.getCode() == 409) {
        //    attributes.addAttribute("kickoutInfo", result.getMsg());
        //} else {
        //    attributes.addAttribute("msg", result.getMsg());
        //}
            attributes.addFlashAttribute("msg", result.getMsg());
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


}
