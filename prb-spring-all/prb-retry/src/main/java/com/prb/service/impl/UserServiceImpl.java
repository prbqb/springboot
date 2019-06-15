package com.prb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prb.entity.User;
import com.prb.exception.AppException;
import com.prb.exception.ExceptionEnum;
import com.prb.mapper.UserMapper;
import com.prb.pojo.AppResult;
import com.prb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    @Retryable(value = AppException.class,maxAttempts = 3,backoff = @Backoff(delay = 2000l,multiplier = 1.5))
    public AppResult upUserUseRetryable(User user){

        User findUser = userMapper.selectById(user.getUserId());
/*        try{
            Thread.sleep(1000);
        }catch(Exception e){}*/
        if(findUser.getAge() > 20){
            throw new AppException(ExceptionEnum.USER_AGE_IS_TOO_BIG);
        }
        Integer flag = userMapper.updateById(user);
        if(flag != 0){
            return AppResult.SUCCESS(null);
        }

        return AppResult.FAIL(null);
    }

    @Override
    @Retryable(value = AppException.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public AppResult getUserById(Long userId){

        log.info("user id is : {}"  , userId);
        if(userId > 10){
            userId = userId - 2;
            throw new AppException(ExceptionEnum.SYSTEM_SUCCESS);
        }
        User user = new User();
        user.setUserId(userId);
        user.setUserName("我叫王大锤");
        user.setAge(12);
        user.setSex(1);
        user.setEmail("646538285");
        AppResult ar = new AppResult();

        return  AppResult.SUCCESS(user);
    }


    @Override
    @Cacheable(cacheNames = "user",key = "methodName + #p0",condition = "#p0 == 22")
    public AppResult getUserByIdFromCache(Long userId){

        log.info("controller invoke getUserByIdFromCache method");
        return AppResult.SUCCESS(userMapper.selectById(userId));
    }

    @Override
    @Retryable(value = Exception.class,maxAttempts = 2,backoff = @Backoff(delay = 1000,multiplier = 1.4))
    public AppResult getUserByName(String userName){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        User result = userMapper.selectOne(wrapper);
        return AppResult.SUCCESS(result);
    }

}
