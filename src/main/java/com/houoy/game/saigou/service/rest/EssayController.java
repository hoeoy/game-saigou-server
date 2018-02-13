package com.houoy.game.saigou.service.rest;


import com.houoy.common.utils.JqueryDataTablesUtil;
import com.houoy.common.vo.JquryDataTablesVO;
import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.game.saigou.service.EssayService;
import com.houoy.game.saigou.vo.EssayVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by andyzhao on 2017-02-20.
 */
@RestController
@RequestMapping("/api/essay")
public class EssayController {

    @Autowired
    private EssayService essayService;


    @PostMapping("/save")
    public RequestResultVO essayAdd(@RequestBody EssayVO essayVO) {
        Integer num = null;
        RequestResultVO resultVO = new RequestResultVO();
        if (essayVO != null) {
            if (essayVO.getPk_essay() != null) {//如果前端传递过来pk,则判断为更新操作
                num = essayService.updateByVO(essayVO);
            } else {
                num = essayService.saveByVO(essayVO);
            }
            if (num != null) {
                resultVO.setSuccess(true);
                resultVO.setMsg("保存成功");
                resultVO.setResultData(num);
            } else {
                resultVO.setSuccess(false);
                resultVO.setMsg("保存失败");
            }
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("参数不可为null");
        }
        return resultVO;
    }


    @PostMapping("/delete")
    public RequestResultVO deletes(@RequestBody List<String> _ids) {
        RequestResultVO resultVO = new RequestResultVO();
        if (_ids != null) {
            essayService.deleteByPK(_ids);
            resultVO.setSuccess(true);
            resultVO.setMsg("删除");
            resultVO.setResultData(_ids.size());

        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("参数不可为null");
        }
        return resultVO;
    }


    @GetMapping(value = "/retrieve")
    public JquryDataTablesVO<EssayVO> retrieve(EssayVO vo, HttpServletRequest request) {
        vo = (EssayVO) JqueryDataTablesUtil.filterParam(vo, request);
//        List<EssayVO> result = essayService.findAll();
//        Long count = essayService.count();
        List<EssayVO> result = essayService.retrieveAllWithPage(vo);
        Long count = essayService.retrieveAllCount(vo);

        JquryDataTablesVO rtv = JqueryDataTablesUtil.madeJqueryDatatablesVO(count, result);
        return rtv;
    }


    @GetMapping(value = "/retrieveByPk")
    public RequestResultVO retrieve(String pk_essay) {
        List<EssayVO> result = essayService.retrieveByPK(pk_essay);
        RequestResultVO resultVO = new RequestResultVO();
        resultVO.setSuccess(true);
        resultVO.setMsg("获取");
        resultVO.setResultData(result.get(0));
        return resultVO;
    }

    @ApiOperation(value = "移动端接口，分页获取设备类型列表")
    @GetMapping("/retrieveMobile")
    //返回带有图片路径的datatable数据
    public PageResultVO retrieveMobile(EssayVO essayVO) {
        List<EssayVO> result = essayService.retrieveAllWithPage(essayVO);
        Long count = essayService.retrieveAllCount(essayVO);
        PageResultVO pageResultVO = new PageResultVO();
        pageResultVO.setSuccess(true);
        pageResultVO.setMsg("查询成功");
        pageResultVO.setResultData(result);
        pageResultVO.setStart(essayVO.getStart());
        pageResultVO.setLength(essayVO.getLength());
        pageResultVO.setOrderColumnName(essayVO.getOrderColumnName());
        pageResultVO.setOrderDir(essayVO.getOrderDir());
        pageResultVO.setTotal(count + "");
        return pageResultVO;
    }
}


