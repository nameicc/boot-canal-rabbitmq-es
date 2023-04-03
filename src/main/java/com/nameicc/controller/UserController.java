package com.nameicc.controller;

import com.nameicc.entity.UserEntity;
import com.nameicc.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("query/{username}")
    public UserEntity queryByName(@PathVariable("username") String name) {
        return userService.findUserByName(name);
    }

}
