package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.prb.entity.User;

public interface UserService extends IService<User> {

    User getUserById(Long userId);

    Integer addUser(User user);

    Integer delUser(Long userId);

    Integer upUser(User user);

    User getUserByName(String userName);


}
