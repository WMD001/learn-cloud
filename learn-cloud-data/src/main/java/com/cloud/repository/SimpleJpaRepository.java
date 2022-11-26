package com.cloud.repository;

import com.cloud.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * JpaRepository 扩展了PagingAndSortingRepository
 * 添加了基于jpa的特有方法
 */
public interface SimpleJpaRepository extends JpaRepository<User, String> {


    /**
     * 使用like根据用户名查询模糊查询，需要在参数左右添加 `%`
     *
     * @param username username
     * @return list
     */
    List<User> findByUsernameLike(String username);

    /**
     * 使用自定义sql模糊查询，自定义sql查询不能用类接受，需要用Object 或者 Map
     *
     * @param username username
     * @return list
     */
    @Query(value = "select id, password, username from User where username like %?1%")
    List<Object> findUsersByUsernameLike(String username);

    /**
     * 使用containing 不需要多余处理
     *
     * @param username username
     * @return list
     */
    List<User> findByUsernameContaining(String username);

    /**
     * 根据用户名模糊查询并排序
     * @param username username
     * @return list
     */
    List<User> findByUsernameContainingOrderByUsername(String username);

    /**
     * 根据用户名模糊查询并排序，使用Sort
     * @param username username
     * @param sort sort
     * @return list
     */
    List<User> findByUsernameContaining(String username, Sort sort);

    /**
     * 本地查询中不支持排序查询
     * <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query">
     *     Spring Data JPA does not currently support dynamic sorting for native queries,
     *     because it would have to manipulate the actual query declared, which it cannot do reliably for native SQL.
     * </a>
     *
     * @param username username
     * @param field field
     * @return list
     */
    @Query(value = "select id,username,password from db_user where username like ?1 order by ?2", nativeQuery = true)
    List<Object> getByNativeQuery(@Param("username") String username, @Param("field") String field);

    Page<User> findByUsernameContaining(String username, Pageable pageable);

    List<User> findTop10ByUsernameContaining(String username);

    Page<User> findTop1ByUsernameContaining(String username, Pageable pageable);
}
