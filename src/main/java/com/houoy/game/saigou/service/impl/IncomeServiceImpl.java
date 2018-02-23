package com.houoy.game.saigou.service.impl;

import com.houoy.common.service.BaseServiceImpl;
import com.houoy.game.saigou.dao.IncomeMapper;
import com.houoy.game.saigou.service.IncomeService;
import com.houoy.game.saigou.vo.IncomeAggVO;
import com.houoy.game.saigou.vo.IncomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("incomeService")
public class IncomeServiceImpl extends BaseServiceImpl<IncomeMapper, IncomeVO> implements IncomeService<IncomeVO> {

    @Override
    @Autowired
    protected void setService(IncomeMapper _mapper) {
        mapper = _mapper;
    }

    @Override
    public Long retrieveAggAllCount(IncomeAggVO vo) {
        return mapper.retrieveAggAllCount(vo);
    }

    @Override
    public List<IncomeAggVO> retrieveAggAllWithPage(IncomeAggVO vo) {
        return mapper.retrieveAggAllWithPage(vo);
    }
}
