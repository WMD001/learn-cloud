package com.cloud.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@WebFilter(urlPatterns = "/webFilter")
public class CustomWebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("CustomWebFilter: [doFilter] -- webFilter");
        chain.doFilter(request, response);
    }
}
