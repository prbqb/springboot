package com.prb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.prb.entity.User;
import com.prb.pojo.AppResult;

public interface UserService extends IService<User> {

    AppResult getUserById(Long userId);

    AppResult upUserUseRetryable(User user);

    AppResult getUserByIdFromCache(Long userId);

    AppResult getUserByName(String userName);

}
