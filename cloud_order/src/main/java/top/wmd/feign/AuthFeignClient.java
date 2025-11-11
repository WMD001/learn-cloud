package top.wmd.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.wmd.config.AuthClientConfig;
import top.wmd.config.AuthClientFallback;
import top.wmd.entity.User;

/**
 * path 参数设置公共前缀
 * configuration 指定配置类
 * fallback 指定降级处理类
 * @author WMD001
 */
@FeignClient(name = "cloud-auth", path = "/auth", configuration=AuthClientConfig.class, fallback = AuthClientFallback.class)
public interface AuthFeignClient {

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id);

    /**
     * 根据用户邮箱获取用户信息
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    @GetMapping("/users/email/{email}")
    User getUserByEmail(@PathVariable("email") String email);
}