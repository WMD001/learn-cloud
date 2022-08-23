package com.cloud.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器，对rest请求进行拦截，三个方法可以只实现需要的
 *
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@Component
public class CustomInterceptor implements HandlerInterceptor {

    /**
     * 前置处理，如果返回true可以执行往下执行，返回false则会停止执行
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return boolean
     * @throws Exception e
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        int localPort = request.getLocalPort();
        log.info("HandlerInterceptor: [preHandle] -- contextPath: " + contextPath + " ,port: " + localPort);
        return true;
    }

    /**
     * 后置处理，在controller方法执行完毕后进行处理，视图渲染之前
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     * (can also be {@code null})
     * @throws Exception e
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("HandlerInterceptor: [postHandle] -- post method handle");
    }

    /**
     * 在请求完成且视图渲染完成之后
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param ex any exception thrown on handler execution, if any; this does not
     * include exceptions that have been handled through an exception resolver
     * @throws Exception e
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("HandlerInterceptor: [afterCompletion] -- after completion handel");
    }
}
