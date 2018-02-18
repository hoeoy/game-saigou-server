package com.houoy.game.saigou.service.rest;

import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.web.BaseController;
import com.houoy.game.saigou.core.Period;
import com.houoy.game.saigou.core.SaigouTimer;
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

    @ApiOperation(value = "今日赛事状态汇总")
    @GetMapping(value = "retrieveSummery")
    public Result<PeriodAggVO> retrieveSummery() {
        Period period = saigouTimer.getPeriod();
        PeriodAggVO periodAggVO = period.getPeriodAggVO();
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