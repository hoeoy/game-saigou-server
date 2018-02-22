package com.houoy.game.saigou.service.rest;


import com.houoy.game.saigou.service.CacheService;
import com.houoy.game.saigou.vo.RateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by andyzhao on 2017-02-20.
 */
@Api(description = "设置系统参数")
//@Slf4j
@RestController
@RequestMapping("/api/setting")
public class SettingController {

    @Autowired
    private CacheService cacheService;

    @ApiOperation(value = "查询赔率参数")
    @GetMapping(value = "/retrieve")
    public RateVO retrieve() {
        RateVO rateVO = new RateVO();
        rateVO.setRateNum(Double.parseDouble(cacheService.get(RateVO.rateNumName)));
        rateVO.setRateTwo(Double.parseDouble(cacheService.get(RateVO.rateTwoName)));
        return rateVO;
    }
}


