package com.houoy.game.saigou.service.impl;

import com.houoy.common.vo.UserVO;
import com.houoy.game.saigou.core.CashFlowType;
import com.houoy.game.saigou.dao.UserMapper;
import com.houoy.game.saigou.service.CashFlowService;
import com.houoy.game.saigou.service.UserService;
import com.houoy.game.saigou.vo.CashFlowVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by andyzhao on 2018/2/14 0014.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private CashFlowService cashFlowService;

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

    @Transactional
    @Override
    public Integer updateUserByVO(UserVO vo) {
        UserVO last = retrieveByPk(vo.getPk_user());
        Long total_before = Long.parseLong(last.getDef1());
        Long total_after = Long.parseLong(vo.getDef1());
        Long money = total_after - total_before;
        Integer userResult = mapper.updateUserByVO(vo);
        //更改积分记录表
        //增加积分流水
        CashFlowVO cashFlowVO = new CashFlowVO();
        if(money>=0){
            cashFlowVO.setCash_type(CashFlowType.top_up);
        }else{
            cashFlowVO.setCash_type(CashFlowType.with_draw);
        }
        cashFlowVO.setPk_user(vo.getPk_user());
        cashFlowVO.setMoney(money.intValue());
        cashFlowVO.setTotal_money_before(total_before);
        cashFlowVO.setTotal_money_after(total_after);
        Integer cashResult = cashFlowService.saveByVO(cashFlowVO);
        return userResult;
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
