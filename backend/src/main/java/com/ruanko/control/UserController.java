package com.ruanko.control;

import com.ruanko.pojo.entity.User;
import com.ruanko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Title: UserController
 * Description:
 * 用户控制层
 * Version:1.0.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(User user) {
        User user_result = userService.findByEntity(user);
        if (user_result != null) {
            return "redirect:/index";
        } else {
            return "user/login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) throws Exception {
        // 先判断用户名是否存在
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            // 用户名已存在，返回错误信息到页面
            model.addAttribute("error", "用户名已存在，请更换用户名");
            return "user/register";
        }

        // 不存在，继续注册
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        boolean saved = userService.save(user);
        if (saved) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "注册失败，请重试");
            return "user/register";
        }
    }

}