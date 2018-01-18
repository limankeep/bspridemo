package com.example.springbootdemo.domain;

import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Map;


/**
 * 
 * <p>Title UserMapper</p>
 * <p>Description TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright Copyright (c) 2018</p>
 * @author Mozart
 * @version 1.0
 * @createtime 2018年1月11日 上午11:08:11
 *
 */
@Mapper
public interface UserMapper {

    
    
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    
    @Select("SELECT name, age FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

    @Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
    int insertByUser(User user);

    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

}