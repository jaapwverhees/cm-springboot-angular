package com.verhees.cm.controller;

import com.verhees.cm.model.user.User;
import com.verhees.cm.model.user.UserCredentials;
import com.verhees.cm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


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

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        register(new User(new UserCredentials("userOne", "Kermit88","ROLE_USER")));
        register(new User(new UserCredentials("userTwo", "Kermit88","ROLE_USER")));
        register(new User(new UserCredentials("userThree", "Kermit88","ROLE_USER")));
        registerAdmin(new User(new UserCredentials("admin", "Kermit88","ADMIN")));
        logger.info("post-construct event fired");
    }
}
