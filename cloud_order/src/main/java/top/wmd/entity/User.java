package top.wmd.entity;

import lombok.Data;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author WMD001
 */
@Data
public class User {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户UUID
     */
    private String uuid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码哈希
     */
    private String passwordHash;

    /**
     * 密码盐值
     */
    private String salt;

    /**
     * 用户状态: 0-禁用, 1-正常, 2-未激活, 3-锁定
     */
    private Integer status;

    /**
     * 邮箱验证状态
     */
    private Boolean emailVerified;

    /**
     * 手机验证状态
     */
    private Boolean phoneVerified;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别: 0-未知, 1-男, 2-女
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 最后登录时间
     */
    private Date lastLoginAt;

    /**
     * 邮箱验证时间
     */
    private Date emailVerifiedAt;
}