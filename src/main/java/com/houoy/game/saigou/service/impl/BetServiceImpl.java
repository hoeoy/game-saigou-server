package com.houoy.game.saigou.service.impl;

import com.houoy.common.service.BaseServiceImpl;
import com.houoy.common.vo.UserVO;
import com.houoy.game.saigou.core.*;
import com.houoy.game.saigou.dao.BetMapper;
import com.houoy.game.saigou.service.BetService;
import com.houoy.game.saigou.service.CashFlowService;
import com.houoy.game.saigou.service.PeriodService;
import com.houoy.game.saigou.service.UserService;
import com.houoy.game.saigou.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("betService")
public class BetServiceImpl extends BaseServiceImpl<BetMapper, BetDetailRecordVO> implements BetService<BetDetailRecordVO> {

    @Autowired
    private UserService userService;

    @Autowired
    private CashFlowService cashFlowService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private SaigouTimer saigouTimer;

    @Override
    @Autowired
    protected void setService(BetMapper _mapper) {
        mapper = _mapper;
    }

    private void prepareVO(BetDetailRecordVO vo) {
        String desc = BetType.getDesc(vo.getBet_item());
        vo.setBet_desc(desc);
    }

    @Transactional
    @Override
    public Integer saveByVO(BetDetailRecordVO vo) {
        prepareVO(vo);
        //获得总积分
        UserVO userVO = userService.retrieveByPk(vo.getPk_user());
        if (userVO != null) {
            //判断积分
            Long total = Long.parseLong(userVO.getDef1());
            if (total < vo.getBet_money()) {
                return -2;//积分不足
            }
            //判断当前时间是否在可下注期
            Period period = saigouTimer.getPeriod();
            TimeType timeType = period.getTimeType();
            if (timeType != TimeType.run_bet) {
                return -4;//当前时间不可以下注
            }
            PeriodAggVO periodAggVO = period.getPeriodAggVO();
            PeriodRecordVO periodRecordVO = null;
            List<PeriodRecordVO> periodRecordVOs = periodService.retrieveByPK(vo.getPk_period());
            if (CollectionUtils.isNotEmpty(periodRecordVOs)) {
                periodRecordVO = periodRecordVOs.get(0);
            }
            if (periodRecordVO == null || !periodRecordVO.getPeriod_code().equals(periodAggVO.getPeriod_code())) {
                return -5;//当前时间不可以下注
            }

            //增加下注记录
            Integer betResult = this.mapper.saveByVO(vo);
            String pk_bet = vo.getPk_bet();

            //增加积分流水
            CashFlowVO cashFlowVO = new CashFlowVO();
            cashFlowVO.setCash_type(CashFlowType.bet);
            cashFlowVO.setPk_user(vo.getPk_user());
            cashFlowVO.setPk_period(vo.getPk_period());
            cashFlowVO.setPk_bet(pk_bet);
            cashFlowVO.setMoney(-vo.getBet_money());
            cashFlowVO.setTotal_money_before(total);
            Long totalAfter = total - vo.getBet_money();
            cashFlowVO.setTotal_money_after(totalAfter);
            Integer cashResult = cashFlowService.saveByVO(cashFlowVO);

            //增加用户总积分
            userVO.setDef1(totalAfter + "");
            Integer userResult = userService.updateUserByVO(userVO);
            return betResult;
        } else {
            return -3;//找不到此用户
        }
    }

    @Override
    public IncomeVO retrieveSumByPeriodPK(String pk_period) {
        return mapper.retrieveSumByPeriodPK(pk_period);
    }

    @Override
    public List<BetDetailRecordVO> retrieveAllByPeriodPkAndItem(SearchWinBetVO searchWinBetVO) {
        return mapper.retrieveAllByPeriodPkAndItem(searchWinBetVO);
    }

    @Override
    public Integer updateWinByPeriodPkAndItem(SearchWinBetVO searchWinBetVO) {
        return mapper.updateWinByPeriodPkAndItem(searchWinBetVO);
    }
}
