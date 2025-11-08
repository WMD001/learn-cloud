package top.wmd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.wmd.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户 Mapper 接口
 *
 * @author WMD001
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱查询用户
     *
     * @param email 用户邮箱
     * @return 用户信息
     */
    User selectByEmail(@Param("email") String email);
}