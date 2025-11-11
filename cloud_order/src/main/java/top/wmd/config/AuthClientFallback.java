package top.wmd.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.wmd.entity.User;
import top.wmd.feign.AuthFeignClient;

/**
 * feign 降级处理
 * @author WMD001
 */
@Slf4j
@Component
public class AuthClientFallback implements AuthFeignClient {

    @Override
    public User getUserById(Long id) {
        log.info("getUserById fallback");
        User user = new User();
        user.setId(id);
        user.setNickname("fallback");
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
