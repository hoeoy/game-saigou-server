package com.houoy.game.saigou.service.impl;

import com.houoy.common.vo.UserVO;
import com.houoy.game.saigou.dao.UserMapper;
import com.houoy.game.saigou.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andyzhao on 2018/2/14 0014.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserVO> retrieveAllWithPage(UserVO vo) throws RuntimeException {
        List<UserVO> result = mapper.retrieveAllWithPage(vo);
        return result;
    }

    @Override
    public Long retrieveAllCount(UserVO vo) throws RuntimeException {
        return mapper.retrieveAllCount(vo);
    }

    @Override
    public UserVO retrieveByPk(String pk) {
        List<UserVO> vos = mapper.retrieveByPK(pk);
        if (CollectionUtils.isNotEmpty(vos)) {
            return vos.get(0);
        }
        return null;
    }

    @Override
    public Integer saveUserByVO(UserVO vo) {
        vo.setUser_password(UserVO.Default_PassWord);
        return mapper.saveUserByVO(vo);
    }

    @Override
    public Integer updateUserByVO(UserVO vo) {
        return mapper.updateUserByVO(vo);
    }

    @Override
    public Integer deleteUsers(List<String> pk_users) {
        return mapper.deleteUsers(pk_users);
    }

    @Override
    public Boolean updateUserRole(UserVO vo) {

        int result = mapper.updateUserRole(vo);

        if (result < 1) {
            return false;
        }
        return true;
    }

}
