package com.houoy.game.saigou.service.impl;

import com.houoy.game.saigou.dao.EssayMapper;
import com.houoy.game.saigou.service.LiveService;
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
    private EssayMapper essayMapper;

    @Override
    public Result retrieve() {
        //闭赛时间:返回本赛程还没有开始

        //开赛时间:
        //开盘等待下注时间段：返回倒计时  00:00:00---00:04:30

        //封盘时间段00:04:30-00:05:00,返回封盘，并且倒计时

        //直播时间段00:04:50-00:05:00,返回直播动画

        Result<String> result = new Result();
        result.setCode(ResultCode.SUCCESS);
        result.setContent("//闭赛时间:返回本赛程还没有开始\n" +
                "        \n" +
                "        //开赛时间:\n" +
                "        //开盘等待下注时间段：返回倒计时  00:00:00---00:04:30\n" +
                "        \n" +
                "        //封盘时间段00:04:30-00:05:00,返回封盘，并且倒计时\n" +
                "\n" +
                "        //直播时间段00:04:50-00:05:00,返回直播动画");

        return result;
    }
}
