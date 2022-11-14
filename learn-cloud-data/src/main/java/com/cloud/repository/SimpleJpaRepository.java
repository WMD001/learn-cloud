package com.cloud.repository;

import com.cloud.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * JpaRepository 扩展了PagingAndSortingRepository
 * 添加了基于jpa的特有方法
 */
public interface SimpleJpaRepository extends JpaRepository<User, String> {


}
