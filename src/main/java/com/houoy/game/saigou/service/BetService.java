package com.houoy.game.saigou.service;


import com.houoy.common.service.BaseService;
import com.houoy.game.saigou.vo.BetDetailRecordVO;
import com.houoy.game.saigou.vo.IncomeVO;
import com.houoy.game.saigou.vo.SearchWinBetVO;

import java.util.List;

public interface BetService<T extends BetDetailRecordVO> extends BaseService<T> {
    IncomeVO retrieveSumByPeriodPK(String pk_period);
    List<BetDetailRecordVO> retrieveAllByPeriodPkAndItem(SearchWinBetVO searchWinBetVO) ;
    Integer updateWinByPeriodPkAndItem(SearchWinBetVO searchWinBetVO) ;
}
