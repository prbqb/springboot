package com.prb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @PostMapping("/save")
    public String saveString(String key, String value){

        log.info("key is {} , value is {}",key,value);
        redisTemplate.opsForValue().set(key,value);
        return "success?";
    }

    @PostMapping("/save/ttl/{key}")
    public String saveTll(@PathVariable String key, String value, Long t){
        log.info("template ops save ttl");
        redisTemplate.opsForValue().set(key,value,t);

        return "";
    }

    @PutMapping("/append/{key}")
    public String appendString(@PathVariable String key , String append){
        log.info("template ops append");
        redisTemplate.opsForValue().append(key,append);
        return "append string is ok?";
    }

    @PutMapping("/delete/{key}")
    public String removeKey(@PathVariable String key){
        log.info("remove key {}",key);
        Boolean flag = redisTemplate.delete(key);

        return "remove is ok? " + flag;
    }

    @PutMapping("/flush")
    public String flushDb(){
        redisTemplate.getConnectionFactory().getConnection().flushDb();

        return "flush db is ok?";
    }


    @GetMapping("/get/{key}")
    @Caching
    public String getValueByKey(@PathVariable String key){
        log.info("template ops get string");
        String value = redisTemplate.opsForValue().get(key);
        log.info("key {} value is :{}" , key , value);
        return value;
    }





}
