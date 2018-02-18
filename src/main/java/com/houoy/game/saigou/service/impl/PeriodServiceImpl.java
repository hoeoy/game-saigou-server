package com.houoy.game.saigou.service.impl;

import com.houoy.common.service.BaseServiceImpl;
import com.houoy.game.saigou.dao.PeriodMapper;
import com.houoy.game.saigou.service.PeriodService;
import com.houoy.game.saigou.vo.PeriodRecordVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("periodService")
public class PeriodServiceImpl extends BaseServiceImpl<PeriodMapper, PeriodRecordVO> implements PeriodService<PeriodRecordVO> {

    @Override
    @Autowired
    protected void setService(PeriodMapper _mapper) {
        mapper = _mapper;
    }

    public PeriodRecordVO retrieveByCode(String code) {
        List<PeriodRecordVO> recordVOList = mapper.retrieveByCode(code);
        if (CollectionUtils.isNotEmpty(recordVOList)) {
            return recordVOList.get(0);
        }
        return null;
    }
}
