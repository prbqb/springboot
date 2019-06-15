package service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prb.entity.User;
import com.prb.mapper.UserLockMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserLockService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserLockServiceImpl extends ServiceImpl<UserLockMapper, User> implements UserLockService {

    @Override
    protected SqlSession sqlSessionBatch() {
//
        return super.sqlSessionBatch();
    }

    @Override
    protected void closeSqlSession(SqlSession sqlSession) {
        super.closeSqlSession(sqlSession);
    }

    @Override
    protected String sqlStatement(SqlMethod sqlMethod) {
        return super.sqlStatement(sqlMethod);
    }

    public User getUserByIdUsePessimistic(Long userId) {
        return userLockMapper.getUserByIdUsePessimistic(userId);
    }

    public int insert(User entity) {
        return userLockMapper.insert(entity);
    }

    public int deleteById(Serializable id) {
        return userLockMapper.deleteById(id);
    }

    public int deleteByMap(Map<String, Object> columnMap) {
        return userLockMapper.deleteByMap(columnMap);
    }

    public int delete(Wrapper<User> wrapper) {
        return userLockMapper.delete(wrapper);
    }

    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return userLockMapper.deleteBatchIds(idList);
    }

    public User selectById(Serializable id) {
        return userLockMapper.selectById(id);
    }

    public List<User> selectBatchIds(Collection<? extends Serializable> idList) {
        return userLockMapper.selectBatchIds(idList);
    }

    public List<User> selectByMap(Map<String, Object> columnMap) {
        return userLockMapper.selectByMap(columnMap);
    }

    public User selectOne(Wrapper<User> queryWrapper) {
        return userLockMapper.selectOne(queryWrapper);
    }

    public Integer selectCount(Wrapper<User> queryWrapper) {
        return userLockMapper.selectCount(queryWrapper);
    }

    public List<User> selectList(Wrapper<User> queryWrapper) {
        return userLockMapper.selectList(queryWrapper);
    }

    public List<Map<String, Object>> selectMaps(Wrapper<User> queryWrapper) {
        return userLockMapper.selectMaps(queryWrapper);
    }

    public List<Object> selectObjs(Wrapper<User> queryWrapper) {
        return userLockMapper.selectObjs(queryWrapper);
    }

    public IPage<User> selectPage(IPage<User> page, Wrapper<User> queryWrapper) {
        return userLockMapper.selectPage(page, queryWrapper);
    }

    public IPage<Map<String, Object>> selectMapsPage(IPage<User> page, Wrapper<User> queryWrapper) {
        return userLockMapper.selectMapsPage(page, queryWrapper);
    }

    @Autowired
    UserLockMapper userLockMapper;


    @Override
    @Transactional
    public Integer upUserByTx(User user){

        userLockMapper.updateById(user);
        int i = 1/user.getAge();
        return 1;
    }

    @Override
    public Integer upUserDecrAgeDelay(Long userId){
        Integer flag = 0;
        User findUser = userLockMapper.selectById(userId);
        if(findUser.getAge() > 0){
            flag = upUserForOptimistic(userId);
            if(flag == 0){
                log.info("进行补偿重试ing。。。。。。。。。。。。。。");
                throw new RuntimeException("年龄减小异常");
            }
        }
        return 1;
    }

    @Override
    public Integer upUserDecrAgeNormal(Long userId){

        return upUserAgeForNormal(userId);
    }

    @Override
    public Integer upUserAgeForNormal(Long userId){

        log.info("正常 进行更新用户的年龄==============start");
        User findUser = userLockMapper.selectById(userId);
        log.info("用正常的方式获取user 信息： {}",findUser);
        Integer flag = updateUserAgeForOptimistic(findUser.getUserId(),findUser.getAge() + 1,findUser.getVersion());
        log.info("正常 进行更新用户的年龄==============end flag is {}",flag);
        return flag;
    }

    @Override
    @Transactional
    public Integer upUserForPessimistic(Long userId){
        log.info("使用悲观说 进行更新用户的年龄==============start");
        User findUser = userLockMapper.getUserByIdUsePessimistic(userId);
        Integer flag = 0;
        flag = updateUserAgeForOptimistic(findUser.getUserId(),findUser.getAge() + 1,findUser.getVersion());
        log.info("使用悲观说 进行更新用户的年龄==============end");
        try{
            Thread.sleep(15000);
        }catch(Exception e){
            //log.info("更新发生异常了");
            //return 0;
        }

        int i = 1/findUser.getSex();
        return flag;
    }

    @Override
    public Integer upUserForOptimistic(Long userId){
        log.info("使用乐观说 进行更新用户的年龄==============start");
        Integer flag = 0;
        User findUser = userLockMapper.selectById(userId);
        try{
            flag = updateUserAgeForOptimistic(findUser.getUserId(),findUser.getAge() - 1,findUser.getVersion());
            Thread.sleep(3000);
        }catch(Exception e){
            log.info("更新失败，sorry");
        }
        log.info("使用乐观说 进行更新用户的年龄==============end");
        return flag;
    }

    private Integer updateUserAgeForOptimistic(Long userId,Integer age,Integer version){
        User user = new User();
        user.setUserId(userId);
        user.setAge(age);
        user.setVersion(version);
        return userLockMapper.updateById(user);
    }

    private Integer updateUserAge(Long userId,Integer age){
        User user = new User();
        user.setUserId(userId);
        user.setAge(age);
        return userLockMapper.updateById(user);
    }
}
