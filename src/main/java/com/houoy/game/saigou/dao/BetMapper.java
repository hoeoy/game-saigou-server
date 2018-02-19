package com.houoy.game.saigou.dao;

import com.houoy.common.mapper.BaseMapper;
import com.houoy.game.saigou.vo.BetDetailRecordVO;
import com.houoy.game.saigou.vo.IncomeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface BetMapper extends BaseMapper<BetDetailRecordVO> {
    IncomeVO retrieveSumByPeriodPK(@Param(value = "pk_period") String pk_period) throws RuntimeException;
}
