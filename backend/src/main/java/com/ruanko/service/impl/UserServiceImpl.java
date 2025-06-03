package com.ruanko.service.impl;

import com.ruanko.mapper.BaseMapper;
import com.ruanko.mapper.UserMapper;
import com.ruanko.pojo.entity.User;
import com.ruanko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Title: UserServiceImpl
 * Description:
 * 用户操作实现类
 * Version:1.0.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseMapper<User> getMapper() {
        return this.userMapper;
    }

    @Override
    public Boolean findByUsernameAndPassword(User user) {
        try {
            System.out.println("findByUsernameAndPassword");
            User user1 = userMapper.findByUsernameAndPassword(user);
            return user1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByUsername(String username) throws Exception {
        try {
            return userMapper.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("查询用户名失败");
        }
    }

    @Override
    public boolean save(User user) {
        int rows = userMapper.insert(user);
        return rows > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        try {
            User user = userMapper.findByUsername(username);
            return user != null;
        } catch (Exception e) {
            System.out.println("检查用户名是否存在失败!");
            e.printStackTrace();
            return false;
        }
    }
}