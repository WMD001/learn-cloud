package com.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 自定义加载用户信息
 *
 * @author Qiq
 */
@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 自定义用户查询方法，通过查询验证用户是否存在，并与表单输入的密码进行校验

        ///        User.builder().username("123")
        //                .password("123")
        //                .passwordEncoder(passwordEncoder::encode)
        //                .build();

        return new User(username, passwordEncoder.encode("123"), new ArrayList<>());
    }

}
