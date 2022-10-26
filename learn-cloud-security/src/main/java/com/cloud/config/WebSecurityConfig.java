package com.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * security 配置
 *
 * @author Qiq
 */
@Slf4j
@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/public/**").permitAll().anyRequest().hasRole("USER").and()
                // Possibly more configuration ...
                .formLogin() // enable form based log in
                .successHandler((request, response, authentication) -> log.info("登陆成功：" + authentication.getName()))
                .failureHandler(((request, response, exception) -> {
                    log.error("登陆失败：" + exception.getMessage());
                    throw exception;
                }))
                // set permitAll for all URLs associated with Form Login
                .permitAll();

        // http basic 校验
///        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // enable in memory based authentication with a user named "user" and "admin"
                .inMemoryAuthentication().passwordEncoder(bCryptPasswordEncoder)
                .withUser("user")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("USER", "ADMIN");
    }

}