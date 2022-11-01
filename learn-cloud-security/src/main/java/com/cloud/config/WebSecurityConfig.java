package com.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

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
                .permitAll()
                .and()
                .userDetailsService(customUserDetailsService);

        // http basic 校验
///        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
///        auth
//                // enable in memory based authentication with a user named "user" and "admin"
//                .inMemoryAuthentication().passwordEncoder(passwordEncoder)
//                .withUser("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .authorities("USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER", "ADMIN")
//                .authorities("ADMIN");

        // 基于jdbc的用户存储
///        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password from users where username = ?")
//                .authoritiesByUsernameQuery("select * from user_authorities where username = ?")
//                .passwordEncoder(passwordEncoder);

        // 使用自定义用户存储
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);

    }

}
