package com.houoy.game.saigou.dao;

import com.houoy.common.mapper.BaseMapper;
import com.houoy.game.saigou.vo.IncomeAggVO;
import com.houoy.game.saigou.vo.IncomeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface IncomeMapper extends BaseMapper<IncomeVO> {
    @Override
    List<IncomeVO> retrieveByPK(@Param("pk") String pk) throws RuntimeException;

    Long retrieveAggAllCount(IncomeAggVO vo) throws RuntimeException;

    List<IncomeAggVO> retrieveAggAllWithPage(IncomeAggVO vo) throws RuntimeException;
}
