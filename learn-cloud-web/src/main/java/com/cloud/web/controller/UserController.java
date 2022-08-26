package com.cloud.web.controller;

import com.cloud.validate.User;
import com.cloud.validate.annotation.HasKey;
import com.cloud.validate.groups.Info;
import com.cloud.validate.groups.SignIn;
import com.cloud.validate.groups.SignUp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.Map;

/**
 * @author Wang
 * @date 2022/8/25
 */
@Validated
@RestController
public class UserController {

    /**
     * 模拟登录
     *
     * @param user user
     * @return true
     */
    @PostMapping("/singIn")
    public boolean singIn(@RequestBody @Validated({Default.class, SignIn.class}) User user) {
        return true;
    }

    @PostMapping("/signUp")
    public boolean signUp(@RequestBody @Validated({Default.class, SignUp.class}) User user) {
        return true;
    }

    @PostMapping("/info")
    public boolean info(@RequestBody @Validated({Info.class}) User user) {
        return true;
    }

    @PostMapping("/mapInfo")
    public boolean info(@RequestBody @HasKey(values = {"age", "bothDay"}, groups = Info.class) Map<String, Object> map) {
        return true;
    }

}
