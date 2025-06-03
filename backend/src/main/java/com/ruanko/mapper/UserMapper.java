package com.ruanko.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ruanko.pojo.entity.User;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsernameAndPassword(User user);

    User findByUsername(String username);
    int insert(User user);
}
