package com.ruanko.service.impl;


import com.ruanko.pojo.entity.User;
import com.ruanko.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("用户名或密码不能为空");
        }

        // 加密密码
        String encryptedPwd = DigestUtils.md5Hex(password + SALT);

        // 查询用户
        User user = userService.findByUsernameAndPassword(username, encryptedPwd);
        if (user == null) {
            throw new IllegalArgumentException("用户名或密码错误");
        }

        // 登录成功，生成 JWT token
        return JWTUtils.createToken(user.getId(), user.getUsername());
    }
}
