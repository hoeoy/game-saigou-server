package com.houoy.game.saigou.service.rest;


import com.houoy.common.vo.RequestResultVO;
import com.houoy.game.saigou.service.CacheService;
import com.houoy.game.saigou.vo.RateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "设置赔率参数", hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rateVO", value = "赔率信息", required = true, paramType = "body", dataType = "RateVO")
    })
    @PostMapping("/save")
    public RequestResultVO save(@RequestBody RateVO rateVO) {
        RequestResultVO resultVO = new RequestResultVO();
        if (rateVO != null) {
            Double odds = rateVO.getOdds();
            Double rateNum = rateVO.getRateNum();
            Double rateTwo = rateVO.getRateTwo();

            if (odds != null) {
                if (odds > 0 && odds <= 1) {
                    cacheService.set(RateVO.oddsName, odds + "");
                } else {
                    resultVO.setSuccess(false);
                    resultVO.setMsg("odds应大于0小于1");
                }
            }

            if (rateNum != null) {
                if (rateNum > 0 && rateNum < 10) {
                    cacheService.set(RateVO.rateNumName, rateNum + "");
                } else {
                    resultVO.setSuccess(false);
                    resultVO.setMsg("rateNum应大于0小于10");
                }
            }

            if (rateTwo != null) {
                if (rateTwo > 0 && rateTwo < 2) {
                    cacheService.set(RateVO.rateTwoName, rateTwo + "");
                } else {
                    resultVO.setSuccess(false);
                    resultVO.setMsg("rateTwo应大于0小于2");
                }
            }
        }
        resultVO.setSuccess(true);
        resultVO.setMsg("保存成功");
        resultVO.setResultData(1);

        return resultVO;
    }
}


