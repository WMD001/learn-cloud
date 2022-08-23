package com.cloud.listeners.event;

import com.cloud.entity.CustomEventInfo;
import org.springframework.context.ApplicationEvent;


/**
 * @author Wang
 * @date 2022/8/23
 */
public class CustomEvent extends ApplicationEvent {

    private final CustomEventInfo eventInfo;

    public CustomEvent(CustomEventInfo source) {
        super(source);
        this.eventInfo = source;
    }

    public CustomEventInfo getInfo() {
        return this.eventInfo;
    }
}
