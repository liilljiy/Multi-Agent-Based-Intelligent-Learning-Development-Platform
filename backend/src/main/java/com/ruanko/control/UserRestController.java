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
import com.ruanko.utils.JWTUtils;

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
                // 这里改成调用 JwtUtil 生成 token
                String token = JWTUtils.generateToken(username);

                data.put("user", userInfo);
                data.put("token", token);
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

}
