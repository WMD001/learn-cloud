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
    # 是否注册到eureka 默认 true ，设置false不注册自身
    register-with-eureka: false
    service-url:
      defaultZone: http://127.0.0.1:8010/eureka
    # 获取注册到eureka的服务
    fetch-registry: false
    # 心跳检查
    healthcheck:
      enabled: false
  instance:
    hostname: localhost
spring:
  application:
    # 注册到eureka 的服务名称
    name: eureka-server
```

启动类添加注解 `@EnableEurekaServe` 表示开始eureka服务