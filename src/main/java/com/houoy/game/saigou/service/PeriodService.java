package com.houoy.game.saigou.service;


import com.houoy.common.service.BaseService;
import com.houoy.game.saigou.vo.PeriodRecordVO;

public interface PeriodService<T extends PeriodRecordVO> extends BaseService<T> {
    PeriodRecordVO retrieveByCode(String code);
}
