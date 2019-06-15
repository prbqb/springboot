package com.prb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prb.entity.User;
import com.prb.mapper.ResultMapper;
import com.prb.service.ResultService;
import com.prb.vo.addVo.UserAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")
public class ResultServiceImpl extends ServiceImpl<ResultMapper, User> implements ResultService {

    @Autowired
    ResultMapper resultMapper;

    @Override
    public Integer addUser(String userName,Integer age,Integer sex,String email) {
        User user = new User(null,userName,age,sex,email,0);
        return resultMapper.insert(user);
    }

    @Override
    @CacheEvict(key = "targetClass.name + #p0")
    public Integer delUser(Long userId) {
        return resultMapper.deleteById(userId);
    }

    @Override
    @CacheEvict(key = "targetClass.name + #p0.userId")
    public Integer upUser(UserAddVo user) {
        User upUser = transUser(user);

        return resultMapper.updateById(upUser);
    }

    @Override
    @Cacheable(key = "targetClass.name + #p0")
    public User getUserById(Long userId) {
        return resultMapper.selectById(userId);
    }

    @Override
    @Cacheable(key = "#p0 + result.userId")
    public User getUserByName(String userName) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);

        return resultMapper.selectOne(wrapper);
    }

    @Override
    @Cacheable(cacheNames = "page.user",key = "targetClass.name + #p0 + #p1 + #p2")
    public IPage<User> pageUserForSex(Integer sex,Integer page,Integer size) {

        IPage<User> pageMsg = new Page<>();
        pageMsg.setCurrent(page);
        pageMsg.setSize(size);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("sex",sex);
        return resultMapper.selectPage(pageMsg, wrapper);
    }

    private User transUser(UserAddVo userAddVo){

        User user = new User();
        user.setUserId(userAddVo.getUserId());
        user.setUserName(userAddVo.getUserName());
        user.setSex(userAddVo.getSex());
        user.setAge(userAddVo.getAge());
        user.setEmail(userAddVo.getEmail());

        return user;
    }

}
