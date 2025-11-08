package top.wmd.entity;

import lombok.Data;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.*;

/**
 * @author WMD001
 */
@Data
@TableName("users")
public class User {

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户UUID
     */
    @TableField("uuid")
    private String uuid;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 密码哈希
     */
    @TableField("password_hash")
    private String passwordHash;

    /**
     * 密码盐值
     */
    @TableField("salt")
    private String salt;

    /**
     * 用户状态: 0-禁用, 1-正常, 2-未激活, 3-锁定
     */
    @TableField("status")
    private Integer status;

    /**
     * 邮箱验证状态
     */
    @TableField("email_verified")
    private Boolean emailVerified;

    /**
     * 手机验证状态
     */
    @TableField("phone_verified")
    private Boolean phoneVerified;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 头像URL
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 性别: 0-未知, 1-男, 2-女
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField("birthday")
    private Date birthday;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private Date updatedAt;

    /**
     * 最后登录时间
     */
    @TableField("last_login_at")
    private Date lastLoginAt;

    /**
     * 邮箱验证时间
     */
    @TableField("email_verified_at")
    private Date emailVerifiedAt;
}