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
```
必须要有一个密码规则器，使用 `BCryptPasswordEncoder`。  
`passwordEncoder(bCryptPasswordEncoder)` 表示密码使用 `BCryptPasswordEncoder` 规则
在内存中配置密码的时候也需要使用加密后的密码，代码中配置时，yaml中的配置失效