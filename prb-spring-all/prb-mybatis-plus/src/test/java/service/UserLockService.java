package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.prb.entity.User;

public interface UserLockService extends IService<User> {

    Integer upUserByTx(User user);

    Integer upUserForOptimistic(Long userId);

    Integer upUserForPessimistic(Long userId);

    Integer upUserAgeForNormal(Long userId);

    Integer upUserDecrAgeNormal(Long userId);

    Integer upUserDecrAgeDelay(Long userId);

}
