package top.wmd.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * feign 拦截器
 * @author WMD001
 */
@Slf4j
@Component
public class RetryInterceptor implements RequestInterceptor {

    private static final Map<String, AtomicInteger> RETRY_COUNT_MAP = new ConcurrentHashMap<>();

    @Override
    public void apply(RequestTemplate template) {
        String path = template.path();
        AtomicInteger retryCount = RETRY_COUNT_MAP.getOrDefault(path, new AtomicInteger(0));
        Objects.requireNonNull(retryCount).incrementAndGet();
        log.info("[{}]第{}次请求", path, retryCount.get());
        RETRY_COUNT_MAP.put(path, retryCount);
    }
}
