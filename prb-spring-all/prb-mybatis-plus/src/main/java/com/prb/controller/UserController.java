package com.prb.controller;

import com.prb.entity.User;
import com.prb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "user控制层",tags = "prb-user")
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping("/add")
    @ApiOperation(value = "添加用户",notes = "添加用户")
    public Integer addUser(User user){

        return userService.addUser(user);
    }

    @DeleteMapping("/del/{userId}")
    public Integer delUser(@PathVariable Long userId){

        return userService.delUser(userId);
    }


    @PutMapping("/up")
    public Integer upUser(User user){

        log.info("user message is : {}",user);
        return userService.upUser(user);
    }

    @GetMapping("/get/{userId}")
    public User getUserById(@PathVariable Long userId){

        return userService.getUserById(userId);
    }

    @GetMapping("/get/name/{userName}")
    public User getUserByName(@PathVariable String userName){

        return userService.getUserByName(userName);
    }



}
