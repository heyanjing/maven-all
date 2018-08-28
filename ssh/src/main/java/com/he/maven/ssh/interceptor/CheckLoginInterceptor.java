package com.he.maven.ssh.interceptor;

import com.he.maven.core.bean.Result;
import com.he.maven.core.time.Times;
import com.he.maven.core.web.SaveRequest;
import com.he.maven.core.web.Springs;
import com.he.maven.core.web.Webs;
import com.he.maven.ssh.Constant;
import com.he.maven.ssh.entity.LoginedInfo;
import com.he.maven.ssh.web.dao.LoginInfoDao;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by heyanjing on 2018/8/23 16:34.
 * 发出请求时检查用户登录与否， 一个账户允许登录的人数
 * HandlerInterceptor 接口中定义了三个方法，我们就是通过这三个方法来对用户的请求进行拦截处理的。
 * <p>
 * （1 ）preHandle (HttpServletRequest request, HttpServletResponse response, Object handle) 方法，顾名思义，该方法将在请求处理之前进行调用。SpringMVC 中的Interceptor 是链式的调用的，在一个应用中或者说是在一个请求中可以同时存在多个Interceptor 。每个Interceptor 的调用会依据它的声明顺序依次执行，而且最先执行的都是Interceptor 中的preHandle 方法，所以可以在这个方法中进行一些前置初始化操作或者是对当前请求的一个预处理，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去。该方法的返回值是布尔值Boolean 类型的，当它返回为false 时，表示请求结束，后续的Interceptor 和Controller 都不会再执行；当返回值为true 时就会继续调用下一个Interceptor 的preHandle 方法，如果已经是最后一个Interceptor 的时候就会是调用当前请求的Controller 方法。
 * <p>
 * （2 ）postHandle (HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView) 方法，由preHandle 方法的解释我们知道这个方法包括后面要说到的afterCompletion 方法都只能是在当前所属的Interceptor 的preHandle 方法的返回值为true 时才能被调用。postHandle 方法，顾名思义就是在当前请求进行处理之后，也就是Controller 方法调用之后执行，但是它会在DispatcherServlet 进行视图返回渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。postHandle 方法被调用的方向跟preHandle 是相反的，也就是说先声明的Interceptor 的postHandle 方法反而会后执行，这和Struts2 里面的Interceptor 的执行过程有点类型。Struts2 里面的Interceptor 的执行过程也是链式的，只是在Struts2 里面需要手动调用ActionInvocation 的invoke 方法来触发对下一个Interceptor 或者是Action 的调用，然后每一个Interceptor 中在invoke 方法调用之前的内容都是按照声明顺序执行的，而invoke 方法之后的内容就是反向的。
 * <p>
 * （3 ）afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception ex) 方法，该方法也是需要当前对应的Interceptor 的preHandle 方法的返回值为true 时才会执行。顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
 */
@Slf4j
@Setter
public class CheckLoginInterceptor implements HandlerInterceptor {

    /**
     * 默认的页面
     */
    private String index = "/";

    private static LoginInfoDao loginInfoDao = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //这个方法中进行一些前置初始化操作或者是对当前请求的一个预处理，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去
        //http://192.168.70.110:8080/ssh/
        //<editor-fold desc="Description">
        log.debug(" HTTP/1.1                            {}", request.getProtocol());
        log.debug(" 192.168.70.110                      {}", request.getRemoteAddr());
        log.debug(" 8080                                {}", request.getServerPort());
        log.debug(" /ssh                                {}", request.getContextPath());
        log.debug(" /ssh/findBySql                      {}", request.getRequestURI());
        log.debug(" http://localhost:8080/ssh/findBySql {}", request.getRequestURL());
        log.debug(" a=1&b=2                             {}", request.getQueryString());
        log.debug(" GET                                 {}", request.getMethod());
        log.debug(" UTF-8                               {}", request.getCharacterEncoding());
        //</editor-fold>

        log.debug("{}", "CheckLoginInterceptor---preHandle");
        log.debug("{}", handler);
        if (loginInfoDao == null) {
            loginInfoDao = Springs.getBean("loginInfoDaoImpl");
        }
        HttpSession session = request.getSession();
        Object userSession = session.getAttribute(Constant.USER_SESSION_KEY);
        if (userSession != null) {
            //已登录 查看是否存在保存的请求
            SaveRequest saveRequest = (SaveRequest) session.getAttribute(Webs.REQUEST_URL);
            if (saveRequest != null) {
                String requestUrl = saveRequest.getRequestUrl();
                session.removeAttribute(Webs.REQUEST_URL);
                Webs.redirect(requestUrl, response);
            }
            if (Webs.isAjaxRequest(request)) {
                LoginedInfo logined = loginInfoDao.getBySessionId(session.getId());
                if (logined != null && StringUtils.isNotBlank(logined.getKickout())) {
                    //前台js需要给出提示并返回首页
                    Webs.writeJsonData(response, Result.failure("该账号登录人数已达上线"), 409);
                    return false;
                }
            }
            return true;
        }
        //未登录 ajax请求 返回403
        if (Webs.isAjaxRequest(request)) {
            //前台js需要处理 返回首页
            Webs.writeJsonData(response, Result.failure("请重新登录后再操作"), 403);
            return false;
        }
        //未登录 普通请求 保存请求并重定向到首页
        Webs.saveRequest(request);
        Webs.redirect(request.getContextPath() + index, response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //主要操作modelAndView
        log.debug("{}", "CheckLoginInterceptor---postHandle");
        log.debug("{}", handler);
        log.debug("{}", modelAndView);
        if (modelAndView != null) {
            modelAndView.addObject("now", Times.nowMilli());
            LoginedInfo logined = loginInfoDao.getBySessionId(request.getSession().getId());
            if (logined != null && StringUtils.isNotBlank(logined.getKickout())) {
                //前台js需要给出提示
                //request.getSession().invalidate();
                modelAndView.addObject("kickoutInfo", "该账号登录人数已达上线");
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("{}", "CheckLoginInterceptor---afterCompletion");
        log.debug("{}", handler);
        log.debug("{}", ex);
    }
}
