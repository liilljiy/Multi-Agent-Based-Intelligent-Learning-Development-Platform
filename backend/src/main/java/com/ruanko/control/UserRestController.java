package com.ruanko.control;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.ruanko.pojo.Result;
import com.ruanko.pojo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ruanko.pojo.entity.User;
import com.ruanko.service.UserService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public Result<List<User>> findByUser() {
        System.out.println("开始查询...");
        return new Result<>(ResultCode.SUCCESS, userService.findAll());
    }

    @PostMapping(value = "/auth/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        System.out.println("用户尝试登录: " + username);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        Boolean loginSuccess = userService.findByUsernameAndPassword(user);

        Map<String, Object> data = new HashMap<>();
        if (loginSuccess) {
            try {
                User userInfo = userService.findByUsername(username);
                data.put("user", userInfo);
                data.put("token", "mock-jwt-token-" + System.currentTimeMillis());
                data.put("success", true);
                data.put("message", "登录成功");
                return new Result<>(ResultCode.SUCCESS, data);
            } catch (Exception e) {
                data.put("success", false);
                data.put("message", "获取用户信息失败");
                return new Result<>(ResultCode.FAILED, data);
            }
        } else {
            data.put("success", false);
            data.put("message", "用户名或密码错误");
            return new Result<>(ResultCode.USER_LOGIN_ERROR, data);
        }
    }

    @PostMapping(value = "/auth/register")
    public Result<Map<String, Object>> register(@RequestBody Map<String, String> registerData) {
        String username = registerData.get("username");
        String password = registerData.get("password");

        System.out.println("新用户注册: " + username);

        Map<String, Object> data = new HashMap<>();

        if (userService.existsByUsername(username)) {
            data.put("success", false);
            data.put("message", "用户名已存在");
            return new Result<>(ResultCode.USER_HAS_EXISTED, data);
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        boolean saveSuccess = userService.save(newUser);

        if (saveSuccess) {
            data.put("success", true);
            data.put("message", "注册成功");
            return new Result<>(ResultCode.SUCCESS, data);
        } else {
            data.put("success", false);
            data.put("message", "注册失败，请稍后再试");
            return new Result<>(ResultCode.FAILED, data);
        }
    }
}
