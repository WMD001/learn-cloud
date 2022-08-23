package com.cloud.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wang
 * @date 2022/8/23
 */
@Component
public class InterceptorConfig implements WebMvcConfigurer {

    private final CustomInterceptor customInterceptor;

    public InterceptorConfig(CustomInterceptor customInterceptor) {
        this.customInterceptor = customInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor)
                .addPathPatterns("/interceptor");

    }
}
