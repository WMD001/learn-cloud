package top.wmd.service;

import top.wmd.entity.User;

/**
 * 用户服务接口
 *
 * @author WMD001
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    boolean register(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean deleteUser(Long id);

    /**
     * 根据用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Long id);

    /**
     * 根据用户邮箱查询用户
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    User getUserByEmail(String email);
}