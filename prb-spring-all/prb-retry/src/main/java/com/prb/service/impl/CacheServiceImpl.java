package com.prb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prb.entity.User;
import com.prb.mapper.CacheMapper;
import com.prb.pojo.AppResult;
import com.prb.service.CacheService;
import com.prb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")
@Slf4j
public class CacheServiceImpl extends ServiceImpl<CacheMapper, User> implements CacheService {

    @Autowired
    CacheMapper cacheMapper;

    @Override
    public AppResult addUser(User user) {
        return AppResult.SUCCESS(cacheMapper.insert(user));
    }

    @Override
    @CacheEvict(cacheNames = "user")
    public AppResult delUser(Long userId) {
        return AppResult.SUCCESS(cacheMapper.deleteById(userId));
    }

    @Override
    @CacheEvict(key = "#p0.userId")
    public AppResult upUser(User user) {
        return AppResult.SUCCESS(cacheMapper.updateById(user));
    }

    @Override
    @Cacheable(key = "#p0")
    public AppResult getUserById(Long userId) {

        return AppResult.SUCCESS(cacheMapper.selectById(userId));
    }

    @Override
    @Cacheable(key = "#p0")
    public AppResult getUserByIdUserFirstCache(Long userId){
        return AppResult.SUCCESS(cacheMapper.selectById(userId));
    }

    @Override
    public AppResult getUserByName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);

        return AppResult.SUCCESS(cacheMapper.selectOne(wrapper));
    }

    @Override
    @Cacheable(key = "methodName + #p0 + #p1")
    public AppResult getUserForPage(Long size,Long page){

        log.info("query sex = 0 user ");
        IPage<User> pageMsg = new Page<>();
        pageMsg.setSize(size);
        pageMsg.setCurrent(page);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex",0);
        IPage pageResult = cacheMapper.selectPage(pageMsg, queryWrapper);
        log.info("query user result is : {}",pageResult);
        return AppResult.SUCCESS(pageResult);
    }

}
