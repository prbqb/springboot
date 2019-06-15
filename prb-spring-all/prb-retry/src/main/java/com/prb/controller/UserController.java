package com.prb.controller;

import com.prb.entity.User;
import com.prb.pojo.AppResult;
import com.prb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/get/{userId}")
    @Cacheable(cacheNames = "user",key = "methodName + #p0")
    public AppResult getUserById(@PathVariable Long userId){

        log.info("调用 getUserById 方法获取用户信息");
        return userService.getUserById(userId);
    }

    @PutMapping("/up/retry")
    public AppResult upUserUseRetryable(User user){
        log.info("调用 up user use retryable 方法进行更新用户");
        return userService.upUserUseRetryable(user);
    }


    @GetMapping("/get/cache/{userId}")
    public AppResult getUserByIdFromCache(@PathVariable Long userId){

        return userService.getUserByIdFromCache(userId);
    }

    @GetMapping("/get/name")
    @Cacheable(cacheNames = "user",key = "methodName + #p0",condition = "#p0.equals('22')")
    public AppResult getUserByName(String userName){

        return userService.getUserByName(userName);
    }

}
