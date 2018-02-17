package com.houoy.game.saigou.service.impl;

import com.houoy.common.service.BaseServiceImpl;
import com.houoy.game.saigou.dao.CashFlowMapper;
import com.houoy.game.saigou.service.CashFlowService;
import com.houoy.game.saigou.vo.CashFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cashFlowService")
public class CashFlowServiceImpl extends BaseServiceImpl<CashFlowMapper, CashFlowVO> implements CashFlowService {

    @Override
    @Autowired
    protected void setService(CashFlowMapper _mapper) {
        mapper = _mapper;
    }
}
