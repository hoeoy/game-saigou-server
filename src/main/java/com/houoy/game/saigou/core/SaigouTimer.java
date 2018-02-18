package com.houoy.game.saigou.core;

import com.houoy.common.utils.DateUtils;
import com.houoy.game.saigou.config.PeriodConfig;
import com.houoy.game.saigou.vo.PeriodAggVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class SaigouTimer {

    private static final Logger logger = LoggerFactory.getLogger(SaigouTimer.class);

    @Autowired
    private PeriodConfig periodConfig;
//
//    @Autowired
//    private CacheService cacheService;

    @Autowired
    private StringRedisTemplate template;

    private Period last;//上次的状态

    @Scheduled(cron = "${period.saigou.timer}")
    public void timer() {
        try {
            Period period = getCurrentPeriod();
            if (last == null) {
                //更新peiod
            } else {

            }

            last = period;
        } catch (ParseException e) {
            logger.error("核心逻辑类产生严重错误，需要重启服务", e.getLocalizedMessage());
        }
    }

    public Period getCurrentPeriod() throws ParseException {
        String today = DateUtils.currentDate();
        String startTime = periodConfig.getStartTime();
        String endTime = periodConfig.getEndTime();
        Long start = DateUtils.tsStrToLong(today + " " + startTime);
        Long end = DateUtils.tsStrToLong(today + " " + endTime);
        Long now = System.currentTimeMillis();

        Period period = new Period();
        if (now > end || now < start) {//停业时间
            period.timeType = TimeType.sleep;
            period.periodAggVO = null;
        } else {//营业时间
            PeriodAggVO periodAggVO = getCurrentPeriodAgg(start, end, now);
            period.periodAggVO = periodAggVO;

            Integer stopSecond = periodConfig.getStopSecond();
            Integer showSecond = periodConfig.getShowSecond();
            Integer rest_second = periodAggVO.getRest_second();
            if (rest_second > stopSecond) {
                period.timeType = TimeType.run_bet;
            } else if (rest_second <= stopSecond && rest_second >= showSecond) {
                period.timeType = TimeType.run_stop;
            } else if (rest_second >= showSecond) {
                period.timeType = TimeType.run_show;
            } else {
                period.timeType = null;
            }
        }

        return period;
    }

    public PeriodAggVO getCurrentPeriodAgg(Long startTime, Long endTime, Long current) throws ParseException {
        Integer durationMillSecond = periodConfig.getDurationSecond() * 1000;//每期持续多长时间,300
        Integer stopMillSecond = periodConfig.getStopSecond() * 1000;//每期剩余多长时间开始封盘,30
        Integer showMillSecond = periodConfig.getShowSecond() * 1000;//每期剩余多长时间开始播放动画 ,10

        Long duration = current - startTime;
        Long current_num = duration / durationMillSecond + 1;//经过了几个开奖周期
        Long rest_second = duration % durationMillSecond / 1000;//本期还剩多少秒

        PeriodAggVO periodAggVO = new PeriodAggVO();
        periodAggVO.setCurrent_num(current_num.intValue());
        periodAggVO.setRest_second(rest_second.intValue());

        Long total_num = (endTime - startTime) / durationMillSecond;//今日总共有多少期
        periodAggVO.setTotal_num(total_num.intValue());
        Long rest_num = total_num - periodAggVO.getCurrent_num();
        periodAggVO.setRest_num(rest_num.intValue());
        return periodAggVO;
    }

    enum TimeType {
        sleep,//停业时间
        run_bet,//下注时间段
        run_stop,//封盘时间段
        run_show//开奖（播放动画)时间段
    }

    class Period {
        public PeriodAggVO periodAggVO;
        public TimeType timeType;
    }

    public static void main(String[] args) throws ParseException {
        new SaigouTimer().getCurrentPeriod();
    }
}
