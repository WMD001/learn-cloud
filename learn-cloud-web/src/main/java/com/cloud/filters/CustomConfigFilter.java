package com.cloud.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * FilterRegistrationBean 方式添加过滤器
 * 在{@link WebFilterConfig}中添加，可设置url规则
 *
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
public class CustomConfigFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("CustomConfigFilter: [doFilter] ");
        chain.doFilter(request, response);
    }
}
