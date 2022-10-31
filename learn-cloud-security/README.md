# 工程简介

spring boot 集成 spring security 完成对单机项目的权限认证和管理。

## 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
版本由父级控制

## 使用

引入依赖后，security默认使用表单登陆方式启动，所有访问地址需要先通过表单验证之后才可以访问  
默认用户名`user`，默认密码输出在控制台，每次启动输出不一样  

## 配置用户

### yml配置

```yaml
spring:
  security:
    user:
      name: admin
      password: $2a$10$mftS9iuLiCqEvufy6p6XAOk7O7UpNk7QE6dzqlh0VVzUyyXhKkseK
```

### 代码配置

```java
package com.cloud.config;

import com.cloud.CustomUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        auth
                // enable in memory based authentication with a user named "user" and "admin"
                .inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("password"))
                .roles("USER", "ADMIN");


    }

}
```
必须要有一个密码规则器，使用 `BCryptPasswordEncoder`。  
`passwordEncoder(bCryptPasswordEncoder)` 表示密码使用 `BCryptPasswordEncoder` 规则
在内存中配置密码的时候也需要使用加密后的密码，代码中配置时，yaml中的配置失效