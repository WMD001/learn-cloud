package top.wmd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.wmd.entity.User;
import top.wmd.feign.AuthFeignClient;

/**
 * @author WMD001
 */
@RestController
public class TestFeignApi {


    @Autowired
    private AuthFeignClient authFeignClient;


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return authFeignClient.getUserById(id);
    }

    @GetMapping("/users/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return authFeignClient.getUserByEmail(email);
    }

}
