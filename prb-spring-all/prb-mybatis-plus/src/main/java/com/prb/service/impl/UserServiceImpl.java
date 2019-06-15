package com.prb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prb.entity.User;
import com.prb.mapper.UserMapper;
import com.prb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public Integer addUser(User user) {

        return userMapper.insert(user);
    }

    @Override
    public Integer delUser(Long userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    public Integer upUser(User user) {
        return userMapper.updateById(user);
    }




    @Override
    public User getUserByName(String userName) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        System.out.println("我更新了这个方法");
        System.out.println("我的github环境已经搭建好了，现在更新本地代码到github上面");

        wrapper.eq("user_name",userName);
        return userMapper.selectOne(wrapper);
    }



}
