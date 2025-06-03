package com.ruanko.service;

import com.ruanko.pojo.entity.User;

import java.io.IOException;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  

 */
public interface UserService extends BaseService<User>{

    Boolean findByUsernameAndPassword(User user);

    User findByUsername(String username) throws Exception;

    boolean save(User user);


    boolean existsByUsername(String username);
}
