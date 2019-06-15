package com.prb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prb.annotation.StatisticsTimeLog;
import com.prb.entity.User;
import com.prb.mapper.UserMapper;
import com.prb.service.UserService;
import com.prb.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Boolean delUser(Long userId) {
        return userMapper.delUserById(userId);
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    @StatisticsTimeLog
    public Map<String, Integer> batchRandomGenUser(Integer count) {

        Map<String,Integer> result = new HashMap<>();
        Integer success = 0;
        Integer fail = 0;
        for (Integer i = 0; i < count; i++) {

            User user = genUser();
            Boolean flag = userMapper.addUser(user);
            if(flag){
                ++success;
            }else{
                ++fail;
            }
        }
        result.put("success",success);
        result.put("fail",fail);
        return result;
    }




    @Override
    @StatisticsTimeLog
    public Map<String, Integer> batchRandomGenUserPlus(Integer count) {

        Map<String , Integer> result = new HashMap<>();
        Integer success = 0;
        Integer fail = 0;
        List<User> users = new ArrayList<>();
        for (Integer i = 0; i < count; i++) {
            User user = genUser();
            users.add(user);
        }
        success = userMapper.batchAddUser(users);
        result.put("success",success);
        result.put("fail",count - success);

        return result;
    }

    @Override
    public User getUserByName(String userName){


        return userMapper.getUserByName(userName);
    }

    private User genUser() {

        System.out.println("老铁，我是一号机，收到吗");
        String userName = RandomUtil.generateStr(3);
        String email = RandomUtil.generateStr(10);
        Integer age = RandomUtil.genInteger(100);
        Integer sex = RandomUtil.genInteger(2);
        User user = new User();
        user.setUserName(userName);
        user.setAge(age);
        user.setSex(sex);
        user.setEmail(email);
        return user;
    }




}
