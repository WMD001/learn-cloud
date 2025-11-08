package top.wmd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.wmd.entity.User;
import top.wmd.service.UserService;

/**
 * @author WMD001
 */
@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserService userService;

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * 根据用户邮箱获取用户信息
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    @PostMapping
    public boolean register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否删除成功
     */
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}