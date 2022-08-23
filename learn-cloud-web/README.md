# learn-cloud-web

Spring Cloud 学习

## Web部分
对于应用的web请求的处理和返回

### Web 请求

**@Controller**
**@RestController**
**@ControllerAdvice**

**@ResponseBody**
**@ExceptionHandle**

**@RequestMapping**
**@GetMapping**
**@PostMapping**
**@PutMapping**
**@DeleteMapping**

**@RequestBody**
**@RequestParam**
**@PathVariable**

**@Validate**

### 文件上传/下载

upload

download

### 请求日志

### 配置

### 拦截器 HandleInterceptor

> com.cloud.interceptor

创建拦截器类
```java
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
```

注册拦截器

```java
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
```

### 过滤器 Filter

方式一： **@Component**

使用注解注入，由于默认匹配路径规则是 `*`,所以会匹配到所有的url路径

```java
package com.cloud.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 注解方式注册过滤器，适配所有路径地址
 *
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@Component
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String method = httpServletRequest.getMethod();
            String requestURI = httpServletRequest.getRequestURI();
            log.info("Filter: [doFilter] -- Method: " + method + " , URI: " + requestURI);
            chain.doFilter(request, response);
        }
    }

}
```

方式二：**FilterRegistrationBean**

普通类实现`Filter`

```java
package com.cloud.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * FilterRegistrationBean 方式添加过滤器
 * 在{@link WebFilterConfig}中添加，可设置url规则
 *
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
public class CustomConfigFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("CustomConfigFilter: [doFilter] ");
        chain.doFilter(request, response);
    }
}
```

配置filter

```java
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
```

方式三： **@WebFilter** + **@ServletComponentScan**

filter类添加注解 `@WebFilter`

```java
package com.cloud.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@WebFilter(urlPatterns = "/webFilter")
public class CustomWebFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("CustomWebFilter: [doFilter] -- webFilter");
        chain.doFilter(request, response);
    }
}
```

启动类添加注解 `@ServletComponentScan`

```java
package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @author Qiq
 */
@ServletComponentScan
@SpringBootApplication
public class LearnCloudWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnCloudWebApplication.class, args);
    }

}
```


### 监听器（事件发布/订阅） Listener

spring boot 启动事件
> ApplicationStartedEvent  
> ApplicationReadyEvent

使用**@EventListener**监听事件

```java
package com.cloud.listeners;

import com.cloud.listeners.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听类，监听 CustomEvent 事件触发
 *
 * @author Wang
 * @date 2022/8/23
 */
@Component
@Slf4j
public class CustomEventListener {

    @EventListener
    public void onApplicationStartedEvent(ApplicationStartedEvent event) {
        log.info("Get Application Started Event");
    }

    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        log.info("Get Application Ready Event");
    }

}
```

自定义事件

```java
package com.cloud.listeners.event;

import com.cloud.entity.CustomEventInfo;
import org.springframework.context.ApplicationEvent;


/**
 * @author Wang
 * @date 2022/8/23
 */
public class CustomEvent extends ApplicationEvent {

    /**
     * 事件携带的相关信息
     */
    private final CustomEventInfo eventInfo;

    public CustomEvent(CustomEventInfo source) {
        super(source);
        this.eventInfo = source;
    }

    public CustomEventInfo getInfo() {
        return this.eventInfo;
    }
}
```

添加自定义事件监听

```java
package com.cloud.listeners;

import com.cloud.listeners.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听类，监听 CustomEvent 事件触发
 *
 * @author Wang
 * @date 2022/8/23
 */
@Component
@Slf4j
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.info("Get Custom Event : " + event.getInfo().toString() );
    }
}
```

事件发布

```java
package com.cloud.web.controller;

import com.cloud.entity.CustomEventInfo;
import com.cloud.listeners.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@RestController
public class ApiController {

    private final ApplicationContext applicationContext;

    public ApiController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/publish")
    public void publishCustomEvent() {
        CustomEventInfo customEventInfo = new CustomEventInfo();
        customEventInfo.setTitle("自定义事件");
        customEventInfo.setMessage("自定义事件触发");
        applicationContext.publishEvent(new CustomEvent(customEventInfo));
    }

}

```

### 启动器 Runner

**ApplicationRunner**

```java
package com.cloud.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@Component
public class CustomApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner running");
    }

}
```

**CommandLineRunner**

```java
package com.cloud.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Wang
 * @date 2022/8/23
 */
@Slf4j
@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner running");
    }

}
```

两个runner接口都只有一个**run**方法，方法启动时机在**ApplicationStarted**事件之后，**ApplicationReady**事件之前，
如果runner执行发生异常，则不会执行**ApplicationReady**事件处理