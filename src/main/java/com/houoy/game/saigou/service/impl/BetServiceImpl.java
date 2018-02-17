package com.houoy.game.saigou.service.impl;

import com.houoy.common.service.BaseServiceImpl;
import com.houoy.game.saigou.dao.BetMapper;
import com.houoy.game.saigou.service.BetService;
import com.houoy.game.saigou.vo.BetDetailRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("betService")
public class BetServiceImpl extends BaseServiceImpl<BetMapper, BetDetailRecordVO> implements BetService {

    @Override
    @Autowired
    protected void setService(BetMapper _mapper) {
        mapper = _mapper;
    }
}
