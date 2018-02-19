package com.houoy.game.saigou.service.impl;

import com.houoy.common.service.BaseServiceImpl;
import com.houoy.common.utils.DateUtils;
import com.houoy.game.saigou.dao.CashFlowMapper;
import com.houoy.game.saigou.service.CashFlowService;
import com.houoy.game.saigou.vo.CashDayAggVO;
import com.houoy.game.saigou.vo.CashFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cashFlowService")
public class CashFlowServiceImpl extends BaseServiceImpl<CashFlowMapper, CashFlowVO> implements CashFlowService<CashFlowVO> {

    @Override
    @Autowired
    protected void setService(CashFlowMapper _mapper) {
        mapper = _mapper;
    }

    @Override
    public Integer saveByVO(CashFlowVO vo) {
        vo.setDate(DateUtils.currentDate());
        return super.saveByVO(vo);
    }

    public Long retrieveAllAggCount(CashDayAggVO vo) throws RuntimeException {
        return this.mapper.retrieveAllAggCount(vo);
    }

    public List<CashDayAggVO> retrieveAllAggWithPage(CashDayAggVO vo) throws RuntimeException {
        return this.mapper.retrieveAllAggWithPage(vo);
    }
}
