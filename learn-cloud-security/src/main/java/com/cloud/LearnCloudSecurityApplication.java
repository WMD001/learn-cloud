package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Qiq
 */
@SpringBootApplication
public class LearnCloudSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnCloudSecurityApplication.class, args);
    }

    /**
     * 密码加密规则 强散列哈希加密实现
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
