package com.prb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.prb.entity.User;
import com.prb.vo.addVo.UserAddVo;

public interface ResultService extends IService<User> {

    Integer addUser(String userName, Integer age, Integer sex, String email);

    Integer delUser(Long userId);

    Integer upUser(UserAddVo user);

    User getUserById(Long userId);

    User getUserByName(String userName);

    IPage<User> pageUserForSex(Integer sex, Integer page, Integer size);

}
