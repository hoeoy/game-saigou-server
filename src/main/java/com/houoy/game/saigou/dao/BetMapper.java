package com.houoy.game.saigou.dao;

import com.houoy.common.mapper.BaseMapper;
import com.houoy.game.saigou.vo.BetDetailRecordVO;
import com.houoy.game.saigou.vo.IncomeVO;
import com.houoy.game.saigou.vo.SearchWinBetVO;
import com.houoy.game.saigou.vo.UserIncomeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface BetMapper extends BaseMapper<BetDetailRecordVO> {
    IncomeVO retrieveSumByPeriodPK(@Param(value = "pk_period") String pk_period) throws RuntimeException;

    List<UserIncomeVO> retrieveUserSumByPeriodAndUser(UserIncomeVO userIncomeVO) throws RuntimeException;

    Long retrieveUserSumByPeriodAndUserCount(UserIncomeVO userIncomeVO) throws RuntimeException;

    List<BetDetailRecordVO> retrieveAllByPeriodPkAndItem(SearchWinBetVO searchWinBetVO) throws RuntimeException;

    Integer updateWinByPeriodPkAndItem(SearchWinBetVO searchWinBetVO) throws RuntimeException;
}
