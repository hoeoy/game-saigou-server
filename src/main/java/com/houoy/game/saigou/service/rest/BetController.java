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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return super.add(vo);
    }

    @ApiOperation(value = "批量下注(保存)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vos", value = "下注信息", required = true, paramType = "body", dataType = "List<BetDetailRecordVO>")
    })
    @PostMapping("/saveBatch")
    public RequestResultVO adds(@RequestBody List<BetDetailRecordVO> vos) {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
//        if (userVO != null) {
//            if (userVO.getPk_user() != null) {//如果前端传递过来pk,则判断为更新操作
//                num = userService.updateUserByVO(userVO);
//            } else {
//                num = userService.saveUserByVO(userVO);
//            }
//
//            if (num >= 1) {
//                resultVO.setSuccess(true);
//                resultVO.setMsg("保存成功");
//                resultVO.setResultData(num);
//            } else {
//                resultVO.setSuccess(false);
//                resultVO.setMsg("保存失败");
//            }
//        } else {
//            resultVO.setSuccess(false);
//            resultVO.setMsg("参数不可为null");
//        }
        resultVO.setSuccess(true);
        resultVO.setMsg("下注成功");
        resultVO.setResultData(num);
        return resultVO;
    }

    @Override
    @ApiOperation(value = "分页查询下注明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "下注明细信息", required = true, paramType = "body", dataType = "BetDetailRecordVO")
    })
    @PostMapping(value = "retrieve")
    public PageResultVO retrieve(@RequestBody BetDetailRecordVO vo, HttpServletRequest request) {
        return super.retrieve(vo, request);
    }

    @ApiOperation(value = "分页查询中奖明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "中奖明细信息", required = true, paramType = "body", dataType = "BetDetailRecordVO")
    })
    @PostMapping(value = "retrieveWin")
    public PageResultVO retrieveWin(@RequestBody BetDetailRecordVO vo, HttpServletRequest request) {
        vo.setWin(1);
        return super.retrieve(vo, request);
    }

    @ApiOperation(value = "根据Pk值删除", notes = "根据Pk值删除")
    @ApiImplicitParam(name = "pks", value = "用户的pk列表", required = true, dataType = "List", paramType = "body")
    @PostMapping("delete")
    @Override
    public RequestResultVO delete(@RequestBody List<String> pks) {
        return super.delete(pks);
    }
}