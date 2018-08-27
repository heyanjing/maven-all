package com.he.maven.ssh;

import com.he.maven.core.time.Times;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author heyanjing
 * @date 2017/12/18 10:45
 * <p>
 * 注入的context、this.servletContext=servletContext;和获取的webApplicationContext.getServletContext()是同一个对象
 * ContextLoader.getCurrentWebApplicationContext().getServletContext()
 * 该方法需要spring容器初始化完成才能使用
 */
@Component
@Slf4j
public class Init {
    private static final int CHAR_START = 51;
    private static final int CHAR_END = 90;
    private static final int NUM_START = 48;
    private static final int NUM_END = 57;
    /**
     * 生成用户名的30位字符
     */
    public static List<String> charList = new ArrayList<>();
    /**
     * 短信验证码的10位数字
     */
    public static List<String> numList = new ArrayList<>();

    @Autowired
    private ServletContext servletContext;

    @PostConstruct
    public void init() {
        //加入全局常量
        if (servletContext != null) {
            String ctx = servletContext.getContextPath();
            String staticc = ctx + "/static";
            servletContext.setAttribute("CTX", ctx);
            servletContext.setAttribute("STATIC", staticc);
            servletContext.setAttribute("IMG", staticc + "/img");
            servletContext.setAttribute("JS", staticc + "/js");
            servletContext.setAttribute("CSS", staticc + "/css");
            servletContext.setAttribute("LIB", staticc + "/lib");
            servletContext.setAttribute("V", Times.nowVersion());
        }
        //生成30位的基础字符
        for (int i = CHAR_START; i < CHAR_END; i++) {
            boolean b = (i <= 57 || i >= 65) && i != 73 && i != 79;
            if (b) {
                charList.add(String.valueOf((char) i).toLowerCase());
            }
        }
        //生成10数字验证码
        for (int i = NUM_START; i <= NUM_END; i++) {
            numList.add(String.valueOf((char) i));
        }
    }

    @PreDestroy
    public void close() {
        //移除全局常量
        if (servletContext != null) {
            servletContext.removeAttribute("CTX");
            servletContext.removeAttribute("STATIC");
            servletContext.removeAttribute("IMG");
            servletContext.removeAttribute("JS");
            servletContext.removeAttribute("CSS");
            servletContext.removeAttribute("LIB");
            servletContext.removeAttribute("V");
        }
    }

}
