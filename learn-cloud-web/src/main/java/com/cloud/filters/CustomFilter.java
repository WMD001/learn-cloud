package com.cloud.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 注解方式注册过滤器，适配所有路径地址
 *
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@Component
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String method = httpServletRequest.getMethod();
            String requestURI = httpServletRequest.getRequestURI();
            log.info("Filter: [doFilter] -- Method: " + method + " , URI: " + requestURI);
            chain.doFilter(request, response);
        }
    }

}
