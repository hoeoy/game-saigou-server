package com.houoy.game.saigou.service.rest;

import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.web.BaseController;
import com.houoy.game.saigou.service.CashFlowService;
import com.houoy.game.saigou.vo.CashDayAggVO;
import com.houoy.game.saigou.vo.CashFlowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 积分记录表
 *
 * @author andyzhao
 */
@Api(description = "积分记录明细，积分统计")
@RestController
@RequestMapping("/api/cash")
public class CashFlowController extends BaseController<CashFlowVO, CashFlowService> {
    private static final Log logger = LogFactory.getLog(CashFlowController.class);

    @Override
    @Autowired
    protected void setService(CashFlowService _service) {
        service = _service;
    }

    @Override
    @ApiOperation(value = "增加积分(保存)", hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "积分信息", required = true, paramType = "body", dataType = "CashFlowVO")
    })
    @PostMapping("/save")
    public RequestResultVO add(@RequestBody CashFlowVO vo) {
        return super.add(vo);
    }

    @Override
    @ApiOperation(value = "分页查询积分明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "积分明细信息", required = true, paramType = "body", dataType = "CashFlowVO")
    })
    @PostMapping(value = "retrieve")
    public PageResultVO retrieve(@RequestBody CashFlowVO vo, HttpServletRequest request) {
        return super.retrieve(vo, request);
    }

    @ApiOperation(value = "按照每天分页查询积分统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "积分明细信息", required = true, paramType = "body", dataType = "CashDayAggVO")
    })
    @PostMapping(value = "retrieveAgg")
    public PageResultVO retrieveAgg(@RequestBody CashDayAggVO vo) {
        PageResultVO pageResultVO = new PageResultVO();
        if (vo == null) {
            pageResultVO.setSuccess(false);
            pageResultVO.setMsg("查询失败，参数不能为null");
            return pageResultVO;
        }

        if (StringUtils.isEmpty(vo.getPk_user())) {
            pageResultVO.setSuccess(false);
            pageResultVO.setMsg("查询失败，pk_user不能为null");
            return pageResultVO;
        }

        List result = this.service.retrieveAllAggWithPage(vo);
        Long count = this.service.retrieveAllAggCount(vo);

        pageResultVO.setSuccess(Boolean.valueOf(true));
        pageResultVO.setMsg("查询成功");
        pageResultVO.setResultData(result);
        pageResultVO.setStart(vo.getStart());
        pageResultVO.setLength(vo.getLength());
        pageResultVO.setOrderColumnName(vo.getOrderColumnName());
        pageResultVO.setOrderDir(vo.getOrderDir());
        pageResultVO.setTotal(count);
        return pageResultVO;
    }

    @ApiOperation(value = "根据Pk值删除", notes = "根据Pk值删除", hidden = true)
    @ApiImplicitParam(name = "pks", value = "用户的pk列表", required = true, dataType = "List", paramType = "body")
    @PostMapping("delete")
    @Override
    public RequestResultVO delete(@RequestBody List<String> pks) {
        return super.delete(pks);
    }
}