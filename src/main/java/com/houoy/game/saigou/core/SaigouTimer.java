package com.houoy.game.saigou.core;

import com.houoy.common.utils.DateUtils;
import com.houoy.common.vo.UserVO;
import com.houoy.game.saigou.config.PeriodConfig;
import com.houoy.game.saigou.dao.UserMapper;
import com.houoy.game.saigou.service.*;
import com.houoy.game.saigou.util.SaigouConstant;
import com.houoy.game.saigou.vo.*;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class SaigouTimer {

    private static final Logger logger = LoggerFactory.getLogger(SaigouTimer.class);

    @Autowired
    private PeriodConfig periodConfig;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BetService betService;

    @Autowired
    private CashFlowService cashFlowService;

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
                if (period.getPeriodAggVO() == null || period.getTimeType() == TimeType.sleep) {//停业时间
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
                            int win_num = -1;//第一名号码
                            if (periodRecordVO != null && periodRecordVO.getF1() == null) {//不能重复开奖，只要一开奖，f1就不为null
                                //获得本期所有下注的积分概况
                                IncomeVO incomeVO = betService.retrieveSumByPeriodPK(periodRecordVO.getPk_period());
                                if (incomeVO != null) {
                                    //设置总下注金额
                                    incomeVO.setTotal_bet(incomeVO.calcTotal_bet());
                                    //设置当前相关赔率
                                    incomeVO.setRateNum(periodConfig.getRateNum());
                                    incomeVO.setRateTwo(periodConfig.getRateTwo());
                                    incomeVO.setOdds(periodConfig.getOdds());

                                    //数字
                                    double recentWin = incomeVO.getTotal_bet() * incomeVO.getOdds();//需要盈利多少钱
                                    double nearest = 100000000;//每个号盈利与目标盈利的差值，取差值最近的开奖
                                    //设置每个号码庄家佩服金额，遍历十个号码，计算庄家的收益,
                                    for (int i = 1; i <= 10; i++) {
                                        Double oddEvenOut = 0.0;//单双赔多少
                                        Double bigLittleOut = 0.0;//大小赔多少
                                        Double oneOut = 0.0;//数字赔多少
                                        if (i % 2 == 0) {//双数
                                            if (incomeVO.getBet_even() != null) {
                                                oddEvenOut = incomeVO.getBet_even() * periodConfig.getRateTwo();//双数赔多少钱
                                            }
                                        } else {//单数
                                            if (incomeVO.getBet_odd() != null) {
                                                oddEvenOut = incomeVO.getBet_odd() * periodConfig.getRateTwo();//单数数赔多少钱
                                            }
                                        }

                                        if (i > 5) {//大
                                            if (incomeVO.getBet_big() != null) {
                                                bigLittleOut = incomeVO.getBet_big() * periodConfig.getRateTwo();//大赔多少钱
                                            }
                                        } else {//小
                                            if (incomeVO.getBet_little() != null) {
                                                bigLittleOut = incomeVO.getBet_little() * periodConfig.getRateTwo();//小赔多少钱
                                            }
                                        }

                                        Object numBet = MethodUtils.invokeMethod(incomeVO, "getBet_" + i, null);
                                        if (numBet != null) {
                                            Long bet = (Long) numBet;
                                            oneOut = bet * periodConfig.getRateNum();//数字赔多少钱
                                        }

                                        double pei = oneOut + oddEvenOut + bigLittleOut;//此数字开奖，共赔多少
                                        double win = incomeVO.getTotal_bet() - pei;//此数字开奖，共盈利多少，负数：庄家赔钱
                                        MethodUtils.invokeMethod(incomeVO, "setWin_" + i, (long) win);
                                        if (win >= 0) {//盈利不能为负数。
                                            if (Math.abs(win - recentWin) < nearest) {//实际盈利与目标盈利差值小于最小差值
                                                nearest = Math.abs(win - recentWin);
                                                //设置开哪个号码,庄家本期收益
                                                incomeVO.setTotal_win((long) win);
                                                incomeVO.setWin_num(i);
                                                win_num = i;
                                            }
                                        }
                                    }//end of for

                                    //增加income记录
                                    Integer incomeResult = incomeService.saveByVO(incomeVO);
                                    //查找所有中奖的下注记录
                                    SearchWinBetVO searchWinBetVO = new SearchWinBetVO();
                                    searchWinBetVO.initBetItemArray(incomeVO.getWin_num());
                                    searchWinBetVO.setPk_period(periodRecordVO.getPk_period());
                                    List<BetDetailRecordVO> winBetRecords = betService.retrieveAllByPeriodPkAndItem(searchWinBetVO);

                                    Map<String, UserVO> users = new HashedMap();//key:pk_user
                                    //增加cash_flow表的流水
                                    for (BetDetailRecordVO betVO : winBetRecords) {
                                        String pk_user = betVO.getPk_user();
                                        CashFlowVO cashFlowVO = new CashFlowVO();
                                        cashFlowVO.setCash_type(CashFlowType.win);
                                        cashFlowVO.setPk_user(pk_user);
                                        cashFlowVO.setPk_period(betVO.getPk_period());
                                        cashFlowVO.setPk_bet(betVO.getPk_bet());
                                        Long cashMoney = (long) betVO.calcWinMoney(periodConfig);
                                        cashFlowVO.setMoney(cashMoney);

                                        UserVO userVO = users.get(pk_user);
                                        if (userVO == null) {
                                            userVO = userService.retrieveByPk(pk_user);
                                            userVO.setUser_code(null);
                                            userVO.setUser_name(null);
                                            users.put(pk_user, userVO);
                                        }
                                        Long tatalBefore = Long.parseLong(userVO.getDef1());
                                        Long totalAfter = tatalBefore + cashMoney;
                                        userVO.setDef1(totalAfter + "");

                                        cashFlowVO.setTotal_money_before(tatalBefore);
                                        cashFlowVO.setTotal_money_after(totalAfter);
                                        Integer cashResult = cashFlowService.saveByVO(cashFlowVO);
                                    }

                                    //增加用户表的积分
                                    for (String key : users.keySet()) {
                                        Integer userResult = userMapper.updateUserByVO(users.get(key));
//                                        Integer userResult = userService.updateUserByVO(users.get(key));
                                    }

                                    //更新bet表的win状态
                                    betService.updateWinByPeriodPkAndItem(searchWinBetVO);
                                } else {
                                    //TODO 本期没有下注记录,任意号码开奖
                                    win_num = new Random().nextInt(10) + 1;
                                }

                                //更新名次和动画属性
                                periodRecordVO.calcRankAndAnimation(win_num);
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
        } catch (NoSuchMethodException e) {
            logger.error("核心逻辑类产生严重错误，需要重启服务", e.getLocalizedMessage());
        } catch (IllegalAccessException e) {
            logger.error("核心逻辑类产生严重错误，需要重启服务", e.getLocalizedMessage());
        } catch (InvocationTargetException e) {
            logger.error("核心逻辑类产生严重错误，需要重启服务", e.getLocalizedMessage());
        }
    }

    private String savePeriodRecord(Period period) {
        PeriodAggVO currentPeriodAggVO = period.getPeriodAggVO();

        PeriodRecordVO periodRecordVO = new PeriodRecordVO();
        periodRecordVO.setPeriod_code(currentPeriodAggVO.getPeriod_code());
        periodRecordVO.setPeriod_desc(currentPeriodAggVO.getPeriod_code());
        periodRecordVO.setPeriod(currentPeriodAggVO.getPeriod_code().substring(1));
        periodRecordVO.setPeriod_start_time(currentPeriodAggVO.getPeriod_start_time());
        periodRecordVO.setPeriod_block_time(currentPeriodAggVO.getPeriod_block_time());
        periodRecordVO.setPeriod_show_time(currentPeriodAggVO.getPeriod_show_time());
        periodRecordVO.setPeriod_stop_time(currentPeriodAggVO.getPeriod_stop_time());

        PeriodRecordVO recordVO = periodService.retrieveByCode(periodRecordVO.getPeriod_code());
        if (recordVO == null) {
            //新增
            Integer pk = periodService.saveByVO(periodRecordVO);
        }
        return "1";
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

    private Period getCurrentPeriod() throws ParseException {
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

    private PeriodAggVO getCurrentPeriodAgg(Long startTime, Long endTime, Long current) throws ParseException {
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
