package com.houoy.game.saigou.service.rest;

import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.web.BaseController;
import com.houoy.game.saigou.service.BetService;
import com.houoy.game.saigou.vo.BetDetailRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author andyzhao
 */
@RestController
@RequestMapping("/api/bet")
@Api(description = "下注，获取下注记录，获取中奖记录")
public class BetController extends BaseController<BetDetailRecordVO, BetService> {
    private static final Log logger = LogFactory.getLog(BetController.class);

    @Override
    @Autowired
    protected void setService(BetService _service) {
        service = _service;
    }

    @Override
    @ApiOperation(value = "下注(保存)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "下注信息", required = true, paramType = "body", dataType = "BetDetailRecordVO")
    })
    @PostMapping("/save")
    public RequestResultVO add(@RequestBody BetDetailRecordVO vo) {
        RequestResultVO requestResultVO = saveOne(vo);
        return requestResultVO;
    }

    private RequestResultVO saveOne(BetDetailRecordVO vo) {
        RequestResultVO resultVO = new RequestResultVO();
        if (vo != null) {
            if (vo.getBet_money() == null || vo.getBet_money() < 0) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败，请输入下注金额");
                return resultVO;
            }

            if (vo.getBet_item() == null) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败,请输入下注项目");
                return resultVO;
            }

            if (vo.getPk_period() == null) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败,请输入属于哪一开奖周期pk_period");
                return resultVO;
            }

            if (vo.getPk_user() == null) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败,请输入用户pk_user");
                return resultVO;
            }

            Integer num = service.saveByVO(vo);
            if (num == -2) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败，用户积分不足，请充值");
                return resultVO;
            } else if (num == -3) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败，找不到此用户");
                return resultVO;
            } else if (num == -4) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败，当前时间不可以下注");
                return resultVO;
            } else if (num == -5) {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败，只能下注本期，请检查pk_period");
                return resultVO;
            } else if (num.intValue() >= 1) {
                resultVO.setSuccess(Boolean.valueOf(true));
                resultVO.setMsg("保存成功");
                resultVO.setResultData(num);
                return resultVO;
            } else {
                resultVO.setSuccess(Boolean.valueOf(false));
                resultVO.setMsg("保存失败");
                return resultVO;
            }
        } else {
            resultVO.setSuccess(Boolean.valueOf(false));
            resultVO.setMsg("参数不可为null");
            return resultVO;
        }
    }

    @ApiOperation(value = "批量下注(保存)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vos", value = "下注信息", required = true, paramType = "body", dataType = "List<BetDetailRecordVO>")
    })
    @PostMapping("/saveBatch")
    public RequestResultVO adds(@RequestBody List<BetDetailRecordVO> vos) {
        RequestResultVO resultVO = new RequestResultVO();
        if (CollectionUtils.isNotEmpty(vos)) {
            for (int i = 0; i < vos.size(); i++) {
                BetDetailRecordVO vo = vos.get(0);
                resultVO = saveOne(vo);
                if (!resultVO.getSuccess()) {
                    return resultVO;
                }
            }
        }

        return resultVO;
    }

    @Override
    @ApiOperation(value = "分页查询下注明细,如果查询中奖win=1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "下注明细信息", required = true, paramType = "body", dataType = "BetDetailRecordVO")
    })
    @PostMapping(value = "retrieve")
    public PageResultVO retrieve(@RequestBody BetDetailRecordVO vo, HttpServletRequest request) {
        return super.retrieve(vo, request);
    }

//    @ApiOperation(value = "分页查询中奖明细")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "vo", value = "中奖明细信息", required = true, paramType = "body", dataType = "BetDetailRecordVO")
//    })
//    @PostMapping(value = "retrieveWin")
//    public PageResultVO retrieveWin(@RequestBody BetDetailRecordVO vo, HttpServletRequest request) {
//        vo.setWin(1);
//        return super.retrieve(vo, request);
//    }

    @ApiOperation(value = "根据Pk值删除", notes = "根据Pk值删除", hidden = true)
    @ApiImplicitParam(name = "pks", value = "用户的pk列表", required = true, dataType = "List", paramType = "body")
    @PostMapping("delete")
    @Override
    public RequestResultVO delete(@RequestBody List<String> pks) {
        return super.delete(pks);
    }
}