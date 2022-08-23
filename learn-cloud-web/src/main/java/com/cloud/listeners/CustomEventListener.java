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
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.info("Get Custom Event : " + event.getInfo().toString() );
    }

    @EventListener
    public void onApplicationStartedEvent(ApplicationStartedEvent event) {
        log.info("Get Application Started Event");
    }

    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        log.info("Get Application Ready Event");
    }

}
