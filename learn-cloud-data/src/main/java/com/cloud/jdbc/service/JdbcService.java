package com.cloud.jdbc.service;

import com.cloud.repository.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qiq
 */
@Service
public class JdbcService {

    private final JdbcTemplate jdbcTemplate;

    public JdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {
        String sql = "select * from db_user";
        // 返回实体类需要使用 BeanPropertyRowMapper ,queryForList 只能返回一个字段的list
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

}
