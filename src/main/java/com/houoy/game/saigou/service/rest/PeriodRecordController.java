package com.houoy.game.saigou.service.rest;

import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.web.BaseController;
import com.houoy.game.saigou.core.Period;
import com.houoy.game.saigou.core.SaigouTimer;
import com.houoy.game.saigou.core.TimeType;
import com.houoy.game.saigou.service.PeriodService;
import com.houoy.game.saigou.vo.PeriodAggVO;
import com.houoy.game.saigou.vo.PeriodRecordVO;
import com.houoy.game.saigou.vo.Result;
import com.houoy.game.saigou.vo.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 开奖记录表
 *
 * @author andyzhao
 */
@Api(description = "开奖记录明细，每日开奖记录汇总")
@RestController
@RequestMapping("/api/period")
public class PeriodRecordController extends BaseController<PeriodRecordVO, PeriodService> {
    private static final Log logger = LogFactory.getLog(PeriodRecordController.class);

    @Autowired
    private SaigouTimer saigouTimer;

    @Override
    @Autowired
    protected void setService(PeriodService _service) {
        service = _service;
    }

    @ApiOperation(value = "设置本期开奖号码", hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "winNum", value = "开奖记录信息", required = true, paramType = "query", dataType = "int")
    })
    @PostMapping("/setWinNum")
    public Result setWinNum(Integer winNum) {
        Result result = new Result();
        if (winNum < 1 || winNum > 10) {
            result.setCode(ResultCode.ERROR_PARAMETER);
            result.setMsg("号码必须大于0小于10的整数");
            result.setContent("号码必须大于0小于10的整数");
            return result;
        }

        Period period = saigouTimer.getPeriod();
        PeriodAggVO periodAggVO = period.getPeriodAggVO();

        if (periodAggVO != null) {//晚上停止营业时间为null
            if (period.getTimeType().equals(TimeType.run_bet)) {//只有下注时间才可以更改号码
                saigouTimer.winByHandMap.put(periodAggVO.getPeriod_code(), winNum);
            } else {
                result.setCode(ResultCode.ERROR_PARAMETER);
                result.setMsg("本期已经封盘，无法修改开奖号码");
                result.setContent("本期已经封盘，无法修改开奖号码");
                return result;
            }
        }

        result.setCode(ResultCode.SUCCESS);
        result.setContent("设置成功");
        result.setMsg("succcess");
        return result;
    }

    @Override
    @ApiOperation(value = "增加开奖记录(保存)", hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "开奖记录信息", required = true, paramType = "body", dataType = "PeriodRecordVO")
    })
    @PostMapping("/save")
    public RequestResultVO add(@RequestBody PeriodRecordVO vo) {
        return super.add(vo);
    }

    @Override
    @ApiOperation(value = "分页查询开奖记录明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "开奖记录明细信息", required = true, paramType = "body", dataType = "PeriodRecordVO")
    })
    @PostMapping(value = "retrieve")
    public PageResultVO retrieve(@RequestBody PeriodRecordVO vo, HttpServletRequest request) {
        return super.retrieve(vo, request);
    }

    @ApiOperation(value = "今日赛事状态汇总，营业时间返回本期状态，非营业时间返回null")
    @GetMapping(value = "retrieveSummery")
    public Result<PeriodAggVO> retrieveSummery() {
        Period period = saigouTimer.getPeriod();
        PeriodAggVO periodAggVO = period.getPeriodAggVO();

        if (periodAggVO != null) {//晚上停止营业时间为null
            PeriodRecordVO vo = service.retrieveByCode(periodAggVO.getPeriod_code());
            if (vo != null) {
                periodAggVO.setPk_period(vo.getPk_period());//设置pk值
            }
        }

        Result<PeriodAggVO> result = new Result();
        result.setContent(periodAggVO);
        result.setCode(ResultCode.SUCCESS);
        result.setMsg("success");
        return result;
    }

    @ApiOperation(value = "根据Pk值删除", notes = "根据Pk值删除", hidden = true)
    @ApiImplicitParam(name = "pks", value = "用户的pk列表", required = true, dataType = "List", paramType = "body")
    @PostMapping("delete")
    @Override
    public RequestResultVO delete(@RequestBody List<String> pks) {
        return super.delete(pks);
    }
}