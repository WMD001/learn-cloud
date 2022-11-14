package com.cloud.repository;

import com.cloud.repository.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 自定义抽象类实现 CrudRepository
 * User 是要操作的实体类表，String 是id的类型
 * CrudRepository 中有针对于单表的增删改查接口 save() delete() update() findById() findAll()
 */
public interface SimpleCrudRepository extends CrudRepository<User, String> {



}
