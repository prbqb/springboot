package com.prb.controller;

import com.prb.entity.User;
import com.prb.pojo.AppResult;
import com.prb.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    @Autowired
    CacheService cacheService;

    @PostMapping("/add")
    public AppResult addUser(User user){
        log.info("add user");
        return cacheService.addUser(user);
    }

    @DeleteMapping("/del/{userId}")
    public AppResult delUser(@PathVariable Long userId){

        return cacheService.delUser(userId);
    }

    @PutMapping("/up")
    public AppResult upUser(User user){

        return cacheService.upUser(user);
    }

    @GetMapping("/get/{userId}")
    public AppResult getUserById(@PathVariable Long userId){

        return cacheService.getUserById(userId);
    }

    @GetMapping("/get/first/{userId}")
    public AppResult getUserByIdFirstUserCache(@PathVariable Long userId){

        return cacheService.getUserByIdUserFirstCache(userId);

    }

    @GetMapping("/get/name/{userName}")
    public AppResult getUserByName(@PathVariable String userName){

        return cacheService.getUserByName(userName);
    }

    @GetMapping("/get/page")
    public AppResult getUserForPage(Long size,Long pages){
        log.info("controller get user page");
        return cacheService.getUserForPage(size,pages);
    }

}
