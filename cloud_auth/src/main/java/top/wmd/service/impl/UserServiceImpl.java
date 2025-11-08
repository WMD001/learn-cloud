package top.wmd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.wmd.mapper.UserMapper;
import top.wmd.entity.User;
import top.wmd.service.UserService;

/**
 * 用户服务实现类
 *
 * @author WMD001
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean register(User user) {
        // 使用MyBatis Plus的save方法保存用户信息
        return save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        // 使用MyBatis Plus的removeById方法根据ID删除用户
        return removeById(id);
    }

    @Override
    public User getUserById(Long id) {
        // 使用MyBatis Plus的 getById 方法根据ID查询用户
        return getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        // 使用自定义的Mapper方法根据邮箱查询用户
        return userMapper.selectByEmail(email);
    }
}