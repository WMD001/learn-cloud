package com.cloud.repository;

import com.cloud.repository.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PagingAndSortingRepository 扩展了 CurdRepository ,并添加了 findAll(Sort sort) 和 findAll(Pageable pageable)
 * 提供了在查询全部时候的分页和排序查询
 */
public interface SimpelPagingAndSortingRepository extends PagingAndSortingRepository<User, String> {



}
