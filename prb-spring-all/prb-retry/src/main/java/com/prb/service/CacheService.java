package com.prb.service;

import com.prb.entity.User;
import com.prb.pojo.AppResult;

public interface CacheService {


    AppResult addUser(User user);

    AppResult delUser(Long userId);

    AppResult upUser(User user);

    AppResult getUserById(Long userId);

    AppResult getUserByName(String userName);

    AppResult getUserByIdUserFirstCache(Long userId);

    AppResult getUserForPage(Long size, Long page);


}
