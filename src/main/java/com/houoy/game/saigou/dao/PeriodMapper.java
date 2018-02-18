package com.houoy.game.saigou.dao;

import com.houoy.common.mapper.BaseMapper;
import com.houoy.game.saigou.vo.PeriodRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface PeriodMapper extends BaseMapper<PeriodRecordVO> {
    List<PeriodRecordVO> retrieveByCode(@Param(value="period_code") String period_code) throws RuntimeException;
}
