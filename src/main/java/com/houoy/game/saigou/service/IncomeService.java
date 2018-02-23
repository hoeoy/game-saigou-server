package com.houoy.game.saigou.service;


import com.houoy.common.service.BaseService;
import com.houoy.game.saigou.vo.IncomeAggVO;
import com.houoy.game.saigou.vo.IncomeVO;

import java.util.List;

public interface IncomeService<T extends IncomeVO> extends BaseService<T> {
    Long retrieveAggAllCount(IncomeAggVO vo) ;

    List<IncomeAggVO> retrieveAggAllWithPage(IncomeAggVO vo) ;
}
