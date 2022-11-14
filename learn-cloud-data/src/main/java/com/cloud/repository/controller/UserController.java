package com.cloud.repository.controller;

import com.cloud.repository.entity.User;
import com.cloud.repository.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JpaService jpaService;

    /**
     * 单表查询全部
     * @return list
     */
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return jpaService.findAll();
    }

    /**
     * 单表分页查询
     * @param page 页码
     * @param size 每页条数
     * @return page
     */
    @GetMapping("/getPageUser/{page}/{size}")
    public Page<User> getPageUser(@PathVariable int page, @PathVariable int size) {
        return jpaService.findPage(page, size);
    }

    /**
     * 模糊查询
     * @param fuzzyQuery q
     * @return list
     */
    @GetMapping("/getUser/fuzzy")
    public List<User> getUserFuzzy(String fuzzyQuery) {
        return jpaService.findByFuzzyQuery(fuzzyQuery);
    }

    /**
     * 排序模糊查询
     * @param fuzzyQuery q
     * @return list
     */
    @GetMapping("/getUser/fuzzyAndOrder")
    public List<User> fuzzyAndOrder(String fuzzyQuery, String sort, boolean ascending) {
        return jpaService.findByFuzzyQueryAndOrder(fuzzyQuery, sort, ascending);
    }

    /**
     * 分页模糊查询
     * @param fuzzyQuery q
     * @return list
     */
    @GetMapping("/getUser/fuzzyPage")
    public Page<User> fuzzyAndOrder(String fuzzyQuery, int page, int size) {
        return jpaService.pageByFuzzyQuery(fuzzyQuery, page, size);
    }

    /**
     * 分页排序模糊查询
     * @param fuzzyQuery q
     * @param sort sort
     * @param ascending 是否升序
     * @param page page
     * @param size size
     * @return page
     */
    @GetMapping("/getUser/findSortPage")
    public Page<User> findSortPage(String fuzzyQuery, String sort, boolean ascending, int page, int size) {
        return jpaService.findSortPage(fuzzyQuery, sort, ascending, page, size);
    }



}
