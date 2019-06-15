package com.prb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.prb.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserLockMapper extends BaseMapper<User> {

    User getUserByIdUsePessimistic(@Param("userId") Long userId);
}
