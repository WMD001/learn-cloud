package com.cloud.filters;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author Wang
 * @date 2022/8/23
 */
@Configurable
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean<CustomConfigFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<CustomConfigFilter> customFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        customFilterFilterRegistrationBean.setFilter(new CustomConfigFilter());
        customFilterFilterRegistrationBean.addUrlPatterns("/filter");
        return customFilterFilterRegistrationBean;
    }

}
