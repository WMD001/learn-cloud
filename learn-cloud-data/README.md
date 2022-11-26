# 工程简介
spring boot 完成数据查询和持久化功能

- JDBCTemplate
- Spring boot JPA
- Mybatis
- Mybatis-Plus

# 延伸阅读

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.7.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.7.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.7.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JDBC](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [MyBatis Plus Official](https://baomidou.com/en/guide/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)

#### JPA 方法中的关键词用法

| **Keyword**        | **Sample**                                              | **JPQL snippet**                                               |
|--------------------|---------------------------------------------------------|----------------------------------------------------------------|
| Distinct           | findDistinctByLastnameAndFirstname                      | select distinct … where x.lastname = ?1 and x.firstname = ?2   |
| And                | findByLastnameAndFirstname                              | … where x.lastname = ?1 and x.firstname = ?2                   |
| Or                 | findByLastnameOrFirstname                               | … where x.lastname = ?1 or x.firstname = ?2                    |
| Is, Equals         | findByFirstname,findByFirstnameIs,findByFirstnameEquals | … where x.firstname = ?1                                       |
| Between            | findByStartDateBetween                                  | … where x.startDate between ?1 and ?2                          |
| LessThan           | findByAgeLessThan                                       | … where x.age < ?1                                             |
| LessThanEqual      | findByAgeLessThanEqual                                  | … where x.age <= ?1                                            |
| GreaterThan        | findByAgeGreaterThan                                    | … where x.age > ?1                                             |
| GreaterThanEqual   | findByAgeGreaterThanEqual                               | … where x.age >= ?1                                            |
| After              | findByStartDateAfter                                    | … where x.startDate > ?1                                       |
| Before             | findByStartDateBefore                                   | … where x.startDate < ?1                                       |
| IsNull, Null       | findByAge(Is)Null                                       | … where x.age is null                                          |
| IsNotNull, NotNull | findByAge(Is)NotNull                                    | … where x.age not null                                         |
| Like               | findByFirstnameLike                                     | … where x.firstname like ?1                                    |
| NotLike            | findByFirstnameNotLike                                  | … where x.firstname not like ?1                                |
| StartingWith       | findByFirstnameStartingWith                             | … where x.firstname like ?1 (parameter bound with appended %)  |
| EndingWith         | findByFirstnameEndingWith                               | … where x.firstname like ?1 (parameter bound with prepended %) |
| Containing         | findByFirstnameContaining                               | … where x.firstname like ?1 (parameter bound wrapped in %)     |
| OrderBy            | findByAgeOrderByLastnameDesc                            | … where x.age = ?1 order by x.lastname desc                    |
| Not                | findByLastnameNot                                       | … where x.lastname <> ?1                                       |
| In                 | findByAgeIn(Collection<Age> ages)                       | … where x.age in ?1                                            |
| NotIn              | findByAgeNotIn(Collection<Age> ages)                    | … where x.age not in ?1                                        |
| True               | findByActiveTrue()                                      | … where x.active = true                                        |
| False              | findByActiveFalse()                                     | … where x.active = false                                       |
| IgnoreCase         | findByFirstnameIgnoreCase                               | … where UPPER(x.firstname) = UPPER(?1)                         |