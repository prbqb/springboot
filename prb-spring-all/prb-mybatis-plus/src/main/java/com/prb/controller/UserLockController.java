package com.prb.controller;

import com.prb.entity.User;
import com.prb.service.UserLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lock")
@Slf4j
public class UserLockController {

    @Autowired
    UserLockService userLockService;


    @PutMapping("/up/tx")
    public Integer upUserByTx(User user){
        log.info("update user by tx way");
        return userLockService.upUserByTx(user);
    }

    @PutMapping("/up/optimistic")
    public Integer upUserForOptimistic(Long userId){

        log.info("up user age find before..............");
        return userLockService.upUserForOptimistic(userId);
    }

    @PutMapping("/up/pessimistic")
    public Integer upUserForPessimistic(Long userId){

        return userLockService.upUserForPessimistic(userId);
    }

    @PutMapping("/up/normal")
    public Integer upUserAgeForNormal(Long userId){

        return userLockService.upUserAgeForNormal(userId);
    }
}
