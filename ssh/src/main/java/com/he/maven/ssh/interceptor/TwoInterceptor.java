package com.he.maven.ssh.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by heyanjing on 2018/8/23 16:34.
 */
@Slf4j
public class TwoInterceptor implements HandlerInterceptor {

    public TwoInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("{}", "TwoInterceptor---preHandle");
        log.info("{}", handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.warn("{}", "TwoInterceptor---postHandle");
        log.info("{}", handler);
        log.info("{}", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.warn("{}", "TwoInterceptor---afterCompletion");
        log.info("{}", handler);
        log.info("{}", ex);
    }

}
