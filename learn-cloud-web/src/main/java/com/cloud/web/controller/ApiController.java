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

    @GetMapping("/interceptor")
    public void interceptor() {
        log.info("enter interceptor method and do something");
    }

    @GetMapping("/filter")
    public void filter() {
        log.info("enter filter method and do something");
    }

    @GetMapping("/webFilter")
    public void webFilter() {
        log.info("enter web filter method and do something");
    }

}
