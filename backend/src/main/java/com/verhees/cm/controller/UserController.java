package com.verhees.cm.controller;

import com.verhees.cm.model.user.User;
import com.verhees.cm.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User register(@RequestBody User user) {
        return userService.registerDefaultUser(user);
    }
    @PostMapping("/admin")
    public User registerAdmin(@RequestBody User user) {
        return userService.registerAdmin(user);
    }
}
