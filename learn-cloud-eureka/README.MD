### eureka 服务注册中心——服务端

引入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

添加配置

```yaml
server:
  port: 8010
  servlet:
    context-path: /eureka
eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://127.0.0.1:8010/eureka
    fetch-registry: false
    healthcheck:
      enabled: false
  instance:
    hostname: localhost
spring:
  application:
    name: eureka-server
management:
  health:
    redis:
      enabled: false
```
