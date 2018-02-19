package com.houoy.game.saigou.service;


import com.houoy.common.service.BaseService;
import com.houoy.game.saigou.vo.BetDetailRecordVO;
import com.houoy.game.saigou.vo.IncomeVO;

public interface BetService<T extends BetDetailRecordVO> extends BaseService<T> {
    IncomeVO retrieveSumByPeriodPK(String pk_period);
}
