package com.houoy.game.saigou.service;

import com.houoy.common.vo.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> retrieveAllWithPage(UserVO vo) throws RuntimeException;

    Long retrieveAllCount(UserVO vo) throws RuntimeException;

    UserVO retrieveByPk(String pk);

    Integer saveUserByVO(UserVO vo);

    Integer updateUserByVO(UserVO vo);

    Integer deleteUsers(List<String> pk_users);

    Boolean updateUserRole(UserVO vo);
}
