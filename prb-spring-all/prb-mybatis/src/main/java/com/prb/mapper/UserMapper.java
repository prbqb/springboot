package com.prb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.prb.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    User getUserById(@Param("userId") Long userId);

    User getUserByName(@Param("userName") String userName);

    Boolean addUser(@Param("user") User user);

    Boolean delUserById(@Param("userId") Long userId);

    Integer batchAddUser(@Param("users") List<User> users);

    Boolean updateUser(@Param("user") User user);
}
