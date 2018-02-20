package com.houoy.game.saigou.service.impl;

import com.houoy.game.saigou.config.PeriodConfig;
import com.houoy.game.saigou.core.Period;
import com.houoy.game.saigou.core.SaigouTimer;
import com.houoy.game.saigou.service.LiveService;
import com.houoy.game.saigou.service.PeriodService;
import com.houoy.game.saigou.vo.PeriodRecordVO;
import com.houoy.game.saigou.vo.Result;
import com.houoy.game.saigou.vo.ResultCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("liveService")
public class LiveServiceImpl implements LiveService {
    private static final Log logger = LogFactory.getLog(LiveServiceImpl.class);

    @Autowired
    private SaigouTimer saigouTimer;

    @Autowired
    private PeriodConfig periodConfig;

    @Autowired
    private PeriodService periodService;

    @Override
    public Result retrieve() {
        Result result = new Result();
        Period period = saigouTimer.getPeriod();
        if(period!=null){
            PeriodRecordVO animation = null;
            switch (period.getTimeType()) {
                case sleep://停业时间
                    result.setCode(ResultCode.SUCCESS);
                    result.setContent("休息时间。" + periodConfig.getStartTime() + "到" + periodConfig.getEndTime() + "为开盘时间。");
                    return result;
                case run_bet://下注时间段
                    result.setCode(ResultCode.SUCCESS);
                    result.setMsg("下注时间,返回倒计时等详细信息");
                    result.setContent(period.getPeriodAggVO());
                    return result;
                case run_stop://封盘时间段
                    result.setCode(ResultCode.SUCCESS);
                    result.setMsg("封盘");
                    animation = getCurrentAnamition(period);
                    result.setContent(animation);
                    return result;
                case run_show://开奖（播放动画)时间段
                    result.setCode(ResultCode.SUCCESS);
                    result.setMsg("动画");
                    animation = getCurrentAnamition(period);
                    result.setContent(animation);
                    return result;
            }
        }

        result.setCode(ResultCode.ERROR_DATA);
        result.setMsg("发生错误");
        result.setContent("发生错误");
        return result;
    }

    private PeriodRecordVO getCurrentAnamition(Period period) {
        //产生本期的名次，动画参数 更新数据
        String code = period.getPeriodAggVO().getPeriod_code();
        PeriodRecordVO periodRecordVO = periodService.retrieveByCode(code);
        if (periodRecordVO != null) {
            return periodRecordVO;
        }

        return null;
    }
}