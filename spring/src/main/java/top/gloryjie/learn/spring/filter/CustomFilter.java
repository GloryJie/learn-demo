package top.gloryjie.learn.spring.filter;

import org.springframework.beans.factory.annotation.Autowired;
import top.gloryjie.learn.spring.controller.HelloController;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器是在DispatchServlet之前调用的，也就是Filter是在Servlet之前
 * 自定义过滤器，断点查看线程栈，查看调用情况
 * @author Jie
 * @since 2020/8/2
 */
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("开始执行自定义过滤器");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
