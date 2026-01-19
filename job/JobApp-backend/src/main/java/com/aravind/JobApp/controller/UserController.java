package com.aravind.JobApp.controller;

import com.aravind.JobApp.model.User;
import com.aravind.JobApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
