package com.houoy.game.saigou.service.rest;

import com.houoy.game.saigou.service.LiveService;
import com.houoy.game.saigou.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 游戏直播相关接口
 *
 * @author andyzhao
 */
@Api(description = "直播接口")
@RestController
@RequestMapping("/api/live")
public class LiveController {
    private static final Log logger = LogFactory.getLog(LiveController.class);

    @Autowired
    private LiveService liveService;

    @ApiOperation(value = "查询live动画数组。不同的时间段返回不同的数据。 " +
            " //闭赛时间:返回本赛程还没有开始。" +
            " //开赛时间:。" +
            " //开盘等待下注时间段：返回倒计时  00:00:00---00:04:30。" +
            " //封盘时间段00:04:30-00:05:00,返回封盘，并且倒计时。" +
            " //直播时间段00:04:50-00:05:00,返回直播动画。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "时间戳", required = true, paramType = "body", dataType = "string")
    })
    @GetMapping(value = "retrieve")
    public Result retrieve(String time) {
        Result<String> result = liveService.retrieve();
        return result;
    }
}