package com.houoy.game.saigou.dao;

import com.houoy.common.mapper.BaseMapper;
import com.houoy.game.saigou.vo.CashDayAggVO;
import com.houoy.game.saigou.vo.CashFlowVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface CashFlowMapper extends BaseMapper<CashFlowVO> {
    Long retrieveAllAggCount(CashDayAggVO vo) throws RuntimeException;

    List<CashDayAggVO> retrieveAllAggWithPage(CashDayAggVO vo) throws RuntimeException;
}
