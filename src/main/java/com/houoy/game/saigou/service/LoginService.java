package com.houoy.game.saigou.service;

import com.houoy.common.vo.SuperVO;
import com.houoy.common.vo.UserVO;

import java.util.List;

public interface LoginService {
    List<SuperVO> retrieveAllWithPage(SuperVO vo) throws RuntimeException;

    Integer retrieveAllCount() throws RuntimeException;

    List<UserVO> retrieveByCodeAndPwd(String code, String password) throws RuntimeException;

    List<UserVO> retrieveByEmailAndPwd(String email, String password) throws RuntimeException;

    List<UserVO> retrieveByMobileAndPwd(String mobile, String password) throws RuntimeException;
}
