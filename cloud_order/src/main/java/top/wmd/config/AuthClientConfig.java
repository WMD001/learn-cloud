package top.wmd.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * openfeign 配置类
 * @author WMD001
 */
@Configuration
public class AuthClientConfig {


//    @Bean
    public Retryer authRetryer() {
        /*
         * 1000 初始重试间隔（毫秒），即第一次重试前等待的时间
         * 最大重试间隔（毫秒），即使指数增长也不能超过此值
         * 最大尝试次数（包括首次请求）
         */
        return new Retryer.Default(1000, 10000, 3);
    }


}
