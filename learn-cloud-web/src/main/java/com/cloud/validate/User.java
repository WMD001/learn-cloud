package com.cloud.validate;

import com.cloud.validate.annotation.HasKey;
import com.cloud.validate.groups.Info;
import com.cloud.validate.groups.SignIn;
import com.cloud.validate.groups.SignUp;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author Wang
 * @date 2022/8/25
 */
@Data
public class User {

    @NotEmpty(message = "用户名不能为空", groups = {SignIn.class, SignUp.class})
    @Size(min = 3, message = "最少需要3个字符")
    private String userName;

    @NotEmpty(message = "密码不能为空", groups = {SignIn.class, SignUp.class})
    @Size(min = 8, max = 16, message = "密码为8-16位的字符串", groups = {SignIn.class, SignUp.class})
    private String password;

    @NotEmpty(groups = {SignUp.class})
    @Size(min = 11, max = 11, message = "手机号格式错误")
    private String phoneNumber;

    @NotEmpty(groups = {SignUp.class})
    private String email;

    @HasKey(values = {"sex", "age"}, message = "参数不能为空，且必须含有sex，age字段", groups = {Info.class})
    private Map<String, Object> info;

}
