package com.houoy.game.saigou.service.impl;

import com.houoy.common.vo.UserVO;
import com.houoy.game.saigou.dao.UserMapper;
import com.houoy.game.saigou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andyzhao on 2018/2/14 0014.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVO> retrieveAllWithPage(UserVO vo) throws RuntimeException {
        List<UserVO> result = userMapper.retrieveAllWithPage(vo);
        return result;
    }

    @Override
    public Long retrieveAllCount(UserVO vo) throws RuntimeException {
        return userMapper.retrieveAllCount(vo);
    }

    @Override
    public Integer saveUserByVO(UserVO vo) {
        vo.setUser_password(UserVO.Default_PassWord);
        return userMapper.saveUserByVO(vo);
    }

    @Override
    public Integer updateUserByVO(UserVO vo) {
        return userMapper.updateUserByVO(vo);
    }

    @Override
    public Integer deleteUsers(List<String> pk_users) {
        return userMapper.deleteUsers(pk_users);
    }

    @Override
    public Boolean updateUserRole(UserVO vo) {

        int result = userMapper.updateUserRole(vo);

        if (result < 1) {
            return false;
        }
        return true;
    }

}
