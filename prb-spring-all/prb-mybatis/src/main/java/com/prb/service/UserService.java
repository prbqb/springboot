package com.prb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.prb.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {

    Boolean addUser(User user);

    Boolean delUser(Long userId);

    User getUserByName(String userName);

    Boolean updateUser(User user);

    User getUserById(Long userId);


    Map<String,Integer> batchRandomGenUser(Integer count);

    Map<String, Integer > batchRandomGenUserPlus(Integer count);

}
