package com.houoy.game.saigou.service.impl;

import com.houoy.common.service.BaseServiceImpl;
import com.houoy.game.saigou.dao.PeriodMapper;
import com.houoy.game.saigou.service.PeriodService;
import com.houoy.game.saigou.vo.PeriodRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("periodService")
public class PeriodServiceImpl extends BaseServiceImpl<PeriodMapper, PeriodRecordVO> implements PeriodService {

    @Override
    @Autowired
    protected void setService(PeriodMapper _mapper) {
        mapper = _mapper;
    }
}
