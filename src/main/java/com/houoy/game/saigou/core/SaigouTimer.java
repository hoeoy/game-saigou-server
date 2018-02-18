package com.houoy.game.saigou.core;

import com.houoy.common.utils.DateUtils;
import com.houoy.game.saigou.config.PeriodConfig;
import com.houoy.game.saigou.service.PeriodService;
import com.houoy.game.saigou.util.SaigouConstant;
import com.houoy.game.saigou.vo.PeriodAggVO;
import com.houoy.game.saigou.vo.PeriodRecordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SaigouTimer {

    private static final Logger logger = LoggerFactory.getLogger(SaigouTimer.class);

    @Autowired
    private PeriodConfig periodConfig;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private StringRedisTemplate template;

    private Period period;//本次的状态
    private Period last;//上次的状态

    public Period getPeriod() {
        return period;
    }

    @Scheduled(cron = "${period.saigou.timer}")
    public void timer() {
        try {
            period = getCurrentPeriod();
            if (last == null) {//刚刚启动程序,或者停业时间
                if (period.getPeriodAggVO() == null || period.getTimeType()==TimeType.sleep) {//停业时间
                    //什么也不做
                } else {
                    //判断是否有本期数据
                    String periodStr = getPeriodStr(period);
                    //数据库中按照code查找
                    PeriodRecordVO recordVO = periodService.retrieveByCode(SaigouConstant.preCode + periodStr);
                    if (recordVO == null) {
                        //新增
                        savePeriodRecord(period);
                    }
                }
            } else {
                if (last.getTimeType() == period.getTimeType()) {//两次状态相等，没有进行状态切换，不做业务处理

                } else {//两次状态不同，需要切换状态
                    switch (period.getTimeType()) {
                        case sleep://进入停止营业状态
                            break;
                        case run_bet://下注状态
                            //产生新的开奖期
                            savePeriodRecord(period);
                            break;
                        case run_stop://封盘
                            //产生本期的名次，动画参数 更新数据
                            String code = period.getPeriodAggVO().getPeriod_code();
                            PeriodRecordVO periodRecordVO = periodService.retrieveByCode(code);
                            if (periodRecordVO != null) {
                                //更新名次和动画属性
//                                LiveVO liveVO = new LiveVO();
//                                String animationJson = JSON.toJSONString(liveVO);
                                String animationJson = "{" +
                                        "\"m1\": [50, 100, 150, 200, 250, 300, 350, 400, 500, 645]," +
                                        "\"m2\": [60, 110, 130, 220, 250, 280, 360, 400, 520, 605]," +
                                        "\"m3\": [50, 100, 140, 200, 260, 270, 370, 420, 530, 750]," +
                                        "\"m4\": [30, 100, 150, 200, 270, 310, 380, 400, 540, 730]," +
                                        "\"m5\": [50, 100, 160, 190, 250, 300, 390, 400, 500, 620]," +
                                        "\"m6\": [90, 130, 170, 200, 280, 260, 320, 400, 560, 610]," +
                                        "\"m7\": [50, 100, 130, 170, 250, 310, 380, 400, 500, 600]," +
                                        "\"m8\": [60, 100, 150, 200, 250, 330, 370, 400, 580, 690]," +
                                        "\"m9\": [50, 100, 130, 180, 290, 330, 330, 400, 500, 625]," +
                                        "\"m10\": [50, 100, 150, 200, 250, 300, 320, 400, 500, 650]" +
                                        "}";
                                periodRecordVO.setF1(3);
                                periodRecordVO.setF2(4);
                                periodRecordVO.setF3(8);
                                periodRecordVO.setF4(10);
                                periodRecordVO.setF5(1);
                                periodRecordVO.setF6(9);
                                periodRecordVO.setF7(5);
                                periodRecordVO.setF8(6);
                                periodRecordVO.setF9(1);
                                periodRecordVO.setF10(7);
                                periodRecordVO.setAnimation(animationJson);
                                periodRecordVO.setOdd_even(1);
                                periodRecordVO.setLittle_big(1);
                                periodService.updateByVO(periodRecordVO);
                            }
                            break;
                        case run_show://播放动画
                            break;
                    }
                }
            }

            last = period;
        } catch (ParseException e) {
            logger.error("核心逻辑类产生严重错误，需要重启服务", e.getLocalizedMessage());
        }
    }

    public String savePeriodRecord(Period period) {
        PeriodAggVO currentPeriodAggVO = period.getPeriodAggVO();
        PeriodRecordVO periodRecordVO = new PeriodRecordVO();
        periodRecordVO.setPeriod_code(currentPeriodAggVO.getPeriod_code());
        periodRecordVO.setPeriod_desc(currentPeriodAggVO.getPeriod_code());
        periodRecordVO.setPeriod(currentPeriodAggVO.getPeriod_code().substring(1));
        periodRecordVO.setPeriod_start_time(currentPeriodAggVO.getPeriod_start_time());
        periodRecordVO.setPeriod_block_time(currentPeriodAggVO.getPeriod_block_time());
        periodRecordVO.setPeriod_show_time(currentPeriodAggVO.getPeriod_show_time());
        periodRecordVO.setPeriod_stop_time(currentPeriodAggVO.getPeriod_stop_time());
        Integer pk = periodService.saveByVO(periodRecordVO);
        return pk + "";
    }

    private String getPeriodStr(Period period) {
        if (period == null) {
            return null;
        }
        PeriodAggVO currentPeriodAggVO = period.getPeriodAggVO();

        if (currentPeriodAggVO == null) {
            return null;
        }

        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyyMMdd");
        String periodStr = datetimeFormat.format(new Date()) + currentPeriodAggVO.getCurrent_num();
        return periodStr;
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
            period.setTimeType(TimeType.sleep);
            period.setPeriodAggVO(null);
        } else {//营业时间
            PeriodAggVO currentPeriodAggVO = getCurrentPeriodAgg(start, end, now);
            period.setPeriodAggVO(currentPeriodAggVO);

            Integer stopSecond = periodConfig.getStopSecond();
            Integer showSecond = periodConfig.getShowSecond();
            Integer rest_second = currentPeriodAggVO.getRest_second();
            if (rest_second > stopSecond) {//剩余时间大于封盘倒计时
                period.setTimeType(TimeType.run_bet);
            } else if (rest_second <= stopSecond && rest_second > showSecond) {
                period.setTimeType(TimeType.run_stop);
            } else if (rest_second <= showSecond) {//剩余时间小于动画倒计时
                period.setTimeType(TimeType.run_show);
            } else {
                period.setTimeType(null);
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
        Long current_past = duration % durationMillSecond;//本期已经经历了多少毫秒
        Long current_rest = durationMillSecond - current_past;//本期还剩多少毫秒
        Long rest_second = current_rest / 1000;//本期还剩多少秒

        PeriodAggVO currentPeriodAggVO = new PeriodAggVO();
        currentPeriodAggVO.setCurrent_num(current_num.intValue());
        currentPeriodAggVO.setRest_second(rest_second.intValue());

        Long total_num = (endTime - startTime) / durationMillSecond;//今日总共有多少期
        currentPeriodAggVO.setTotal_num(total_num.intValue());
        Long rest_num = total_num - currentPeriodAggVO.getCurrent_num();
        currentPeriodAggVO.setRest_num(rest_num.intValue());

        Date period_start_time = new Date(current - current_past);//当前时间 减去 已经经历了多少毫秒
        Date period_stop_time = new Date(period_start_time.getTime() + durationMillSecond);//本期结束时间
        Date period_block_time = new Date(period_stop_time.getTime() - stopMillSecond);//本期封盘时间
        Date period_show_time = new Date(period_stop_time.getTime() - showMillSecond);//本期动画时间

        currentPeriodAggVO.setPeriod_start_time(DateUtils.formatDatetime(period_start_time));
        currentPeriodAggVO.setPeriod_stop_time(DateUtils.formatDatetime(period_stop_time));
        currentPeriodAggVO.setPeriod_block_time(DateUtils.formatDatetime(period_block_time));
        currentPeriodAggVO.setPeriod_show_time(DateUtils.formatDatetime(period_show_time));

        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyyMMdd");
        String periodStr = datetimeFormat.format(new Date()) + currentPeriodAggVO.getCurrent_num();
        currentPeriodAggVO.setPeriod_code(SaigouConstant.preCode + periodStr);
        return currentPeriodAggVO;
    }

    public static void main(String[] args) throws ParseException {
        new SaigouTimer().getCurrentPeriod();
    }
}
