package com.prb.controller;

import com.prb.entity.User;
import com.prb.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get/{userId}")
    public User getUserByUserId(@PathVariable Long userId){
        log.info("invoke getUser method and userId is {}:",userId);

        return userService.getUserById(userId);
    }

    @GetMapping("/get/name/{userName}")
    public User getUserByName(@PathVariable String userName){

        return userService.getUserByName(userName);
    }

    @PostMapping("/add")
    public Boolean addUser(User user){

        log.info("insert before user value is :{}",user);
        Boolean flag = userService.addUser(user);
        log.info("insert database userId value is : {}",user.getUserId());
        return flag;
    }


    @GetMapping("/batch/gen")
    @ApiOperation(value = "批量添加用户",notes = "一次性添加多个用户，根据传入参数来确定添加的个数")
    public Map<String,Integer> batchGenUser(Integer count){

        log.info("批量生成：{} 个user",count);

        return userService.batchRandomGenUser(count);
    }

    @GetMapping("/batch/gen/plus")
    public Map<String, Integer> batchGenUserPlus(Integer count){

        log.info("batch gen user plus , count is {}" , count);
        return userService.batchRandomGenUserPlus(count);
    }

    @DeleteMapping("/del/{userId}")
    public Boolean delUserById(@PathVariable Long userId){

        return userService.delUser(userId);
    }

    @PutMapping("/up")
    public Boolean updateUser(User user){

        log.info("update user is begin  and user message is {}:" , user);

        return userService.updateUser(user);


    }

}

