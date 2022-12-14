package com.cloud.repository.service;

import com.cloud.repository.SimpleJpaRepository;
import com.cloud.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaService {

    @Autowired
    private SimpleJpaRepository simpleJpaRepository;

    /**
     * 单表全部不分页
     * @return list
     */
    public List<User> findAll() {
        return simpleJpaRepository.findAll();
    }

    /**
     * 分页查询
     * @param page page
     * @param size size
     * @return page
     */
    public Page<User> findPage(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return simpleJpaRepository.findAll(pageable);
    }


    /**
     * 模糊查询的方法
     * @param fuzzyQuery query
     * @return list
     */
    public List<User> findByFuzzyQuery(String fuzzyQuery) {

        // 第一种方法 使用like，参数左右添加 `%`
        List<User> users = simpleJpaRepository.findByUsernameLike("%" + fuzzyQuery + "%");

        // 第二种方法 使用自定义sql查询,自定义查询不能使用实体类查询，只能使用 Object 或者 Map 接收
        List<Object> usersByUsernameLike = simpleJpaRepository.findUsersByUsernameLike(fuzzyQuery);

        // 使用containing
        users = simpleJpaRepository.findByUsernameContaining(fuzzyQuery);

        return users;
    }

    public List<User> findByFuzzyQueryAndOrder(String fuzzyQuery, String sort, boolean ascending) {
        List<User> byUsernameContainingOrderByUsername = simpleJpaRepository.findByUsernameContainingOrderByUsername(fuzzyQuery);

        Order order = new Order(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        simpleJpaRepository.findByUsernameContaining(fuzzyQuery, Sort.by(order));

///        List<Object> byUsernameOrderByUsername = simpleJpaRepository.getByNativeQuery("%"+fuzzyQuery+"%", sort);

        return byUsernameContainingOrderByUsername;
    }

    /**
     * 分页模糊查询
     * @param fuzzyQuery 模糊查询
     * @param page 页码
     * @param size 每页条数
     * @return page p
     */
    public Page<User> pageByFuzzyQuery(String fuzzyQuery, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        // 模糊分页查询
        Page<User> byUsernameContaining = simpleJpaRepository.findByUsernameContaining(fuzzyQuery, pageable);

        // 模糊查询分页后的第一条
        Page<User> top1ByUsernameContaining = simpleJpaRepository.findTop1ByUsernameContaining(fuzzyQuery, pageable);

        // 模糊查询 Paging query needs to have a Pageable parameter
        List<User> top10ByUsernameContaining = simpleJpaRepository.findTop10ByUsernameContaining(fuzzyQuery);

        return byUsernameContaining;
    }

    /**
     * 分页排序模糊查询
     * Sort 和 Pageable 不能同时使用，如果分页也需要排序，使用pageable的排序功能
     *
     * @param fuzzyQuery 模糊查询
     * @param sort       排序
     * @param ascending  是否升序
     * @param pageNo     页码
     * @param pageSize   每页条数
     * @return page
     */
    public Page<User> findSortPage(String fuzzyQuery, String sort, boolean ascending, int pageNo, int pageSize) {

        Order order;
        if (ascending) {
            order = Order.asc(sort);
        } else {
            order = Order.desc(sort);
        }

        Sort orderBy = Sort.by(order);
        Pageable pageable = PageRequest.of(pageSize, pageNo, orderBy);


        return simpleJpaRepository.findByUsernameContaining(fuzzyQuery, pageable);
    }

    // TODO 连表查询

    // 使用 @Query 自定义查询




}
