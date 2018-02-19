package com.houoy.game.saigou.service;


import com.houoy.common.service.BaseService;
import com.houoy.game.saigou.vo.CashDayAggVO;
import com.houoy.game.saigou.vo.CashFlowVO;

import java.util.List;

public interface CashFlowService<T extends CashFlowVO> extends BaseService<T> {
    Long retrieveAllAggCount(CashDayAggVO vo) throws RuntimeException;

    List<CashDayAggVO> retrieveAllAggWithPage(CashDayAggVO vo) throws RuntimeException;
}
