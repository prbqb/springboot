package com.prb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rt")
@Slf4j
public class RedisOpsController {


    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/like/{key}")
    public String getLikeKey(@PathVariable String key){

        log.info("get like key {}:" , key);


        return "";
    }

}
