package com.ruanko.service.impl;


import com.ruanko.pojo.Result;
import com.ruanko.pojo.entity.User;
import com.ruanko.service.UserService;
import com.ruanko.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

//Spring 自动扫描组件// https://blog.csdn.net/u010002184/article/details/72870065
// @Component – 指示自动扫描组件。
//@Repository – 表示在持久层DAO组件。
//@Service – 表示在业务层服务组件。
//@Controller – 表示在表示层控制器组件。
@Service
public class LoginServiceImpl {

    @Autowired
    private UserService userService;

    private static final String SALT = "mszlu!@#";

    @Override
    public Result login(String username, String password) {
        String StringUtils;
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Result.fail(400, "用户名或密码不能为空");
        }

        // MD5 加密 + salt
        String pwd = DigestUtils.md5Hex(password + SALT);
        User user = userService.findByUsernameAndPassword(username, pwd);
        if (user == null) {
            return Result.fail(401, "用户名或密码错误");
        }

        // 登录成功，生成 JWT token
        String token = JWTUtils.createToken(user.getId(), user.getUsername());
        return Result.success(token);
    }
}
