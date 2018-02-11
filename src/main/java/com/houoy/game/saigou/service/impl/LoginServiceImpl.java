package com.houoy.game.saigou.service.impl;

import com.houoy.common.utils.Encode;
import com.houoy.common.vo.SuperVO;
import com.houoy.common.vo.UserVO;
import com.houoy.game.saigou.dao.LoginMapper;
import com.houoy.game.saigou.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author andyzhao
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public List<SuperVO> retrieveAllWithPage(SuperVO vo) throws RuntimeException {
        // List<Map> datas = loginMapper.retrieveCountWithPage(vo);
        //List<RoleVO> result = BaseUtils.mapToBean(RoleVO.class, datas);
        return null;
    }

    public Integer retrieveAllCount() throws RuntimeException {
        return loginMapper.retrieveAllCount();
    }

    public List<UserVO> retrieveByCodeAndPwd(String code, String password) throws RuntimeException {
        String encrypted_password = Encode.encode(password);
        return loginMapper.retrieveByCodeAndPwd(code, encrypted_password);
    }

    public List<UserVO> retrieveByEmailAndPwd(String email, String password) throws RuntimeException {
        String encrypted_password = Encode.encode(password);
        return loginMapper.retrieveByEmailAndPwd(email, encrypted_password);
    }

    public List<UserVO> retrieveByMobileAndPwd(String mobile, String password) throws RuntimeException {
        String encrypted_password = Encode.encode(password);
        return loginMapper.retrieveByMobileAndPwd(mobile, encrypted_password);
    }

}
