package com.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfigurerAdapter 过时后新写法
 *
 * @author Qiq
 */
@Configurable
@EnableWebSecurity
public class WebSecurityNewConfig {

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    /**
     * 配置httpSecurity, 加入到过滤链中
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception e
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .userDetailsService(customUserDetailsService)
                .formLogin()
                .and()
                .csrf()
                .disable()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/public");
    }

}
