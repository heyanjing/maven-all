package com.he.maven.ssh.interceptor;

import com.he.maven.core.web.Springs;
import com.he.maven.ssh.entity.UrlInfo;
import com.he.maven.ssh.web.dao.UrlInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author heyanjing
 * @date 2018/8/28 10:39
 * 记录每个请求的耗时
 * https://blog.csdn.net/u013087513/article/details/56835894
 * https://www.jianshu.com/p/521e0dc49051
 */
@Slf4j
public class TimesFilter implements Filter {
    private static final String EXCLUE = "exclue";
    private static final String CONSUMETIMES = "consumeTimes";
    private static final String COMMA = ",";
    public static String CONSUME_TIMES_KEY = "consume_times";
    private static String DEFALUT_EXCLUE = ".jsp,/static/";
    private static UrlInfoDao urlInfoDao;

    @Override
    public void init(FilterConfig filterConfig) {
        String exclue = filterConfig.getInitParameter(EXCLUE);
        if (exclue != null) {
            DEFALUT_EXCLUE = exclue;
        }
        String consume_times = filterConfig.getInitParameter(CONSUMETIMES);
        if (consume_times != null) {
            CONSUME_TIMES_KEY = consume_times;
        }
        if (urlInfoDao == null) {
            urlInfoDao = Springs.getBean("urlInfoDaoImpl");
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        String[] exclueArr = DEFALUT_EXCLUE.split(COMMA);
        //!StringUtils.endsWithAny(requestURI, exclueArr) &&
        if (!StringUtils.containsAny(requestURI, exclueArr)) {
            long begin = System.currentTimeMillis();
            chain.doFilter(httpServletRequest, response);
            long end = System.currentTimeMillis();
            urlInfoDao.save(new UrlInfo(requestURI, end - begin));
            httpServletRequest.setAttribute(CONSUME_TIMES_KEY, end - begin);
        } else {
            chain.doFilter(httpServletRequest, response);
        }
        log.debug("{}  {}", requestURI, httpServletRequest.getAttribute("consume_times"));
    }
}
