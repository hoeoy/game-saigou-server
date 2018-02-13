package com.houoy.game.saigou.service.rest;

import com.houoy.common.vo.PageResultVO;
import com.houoy.common.vo.RequestResultVO;
import com.houoy.common.vo.TreeVO;
import com.houoy.game.saigou.service.EssayTypeService;
import com.houoy.game.saigou.vo.EssayTypeVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章类型目录管理
 * Created by andyzhao on 2017/5/2.
 */
@RestController
@RequestMapping("/api/essaytype")
public class EssayTypeController {

    @Resource
    private EssayTypeService essayTypeService;

    @PostMapping("/save")
    public RequestResultVO add(@RequestBody EssayTypeVO vo) {
        Integer num = 0;
        RequestResultVO resultVO = new RequestResultVO();
        if (vo != null) {
            if (vo.getPk_type() != null) {//如果前端传递过来pk,则判断为更新操作
                num = essayTypeService.updateByVO(vo);
            } else {
                num = essayTypeService.saveByVO(vo);
            }

            if (num >= 1) {
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
    public RequestResultVO delete(@RequestBody List<String> pks) {
        RequestResultVO resultVO = new RequestResultVO();
        List<EssayTypeVO> essayTypeVOs = essayTypeService.retrieveByParentPK(pks);
        if (essayTypeVOs != null && essayTypeVOs.size() > 0) {
            resultVO.setSuccess(false);
            resultVO.setMsg("此目录有子节点无法删除.");
        } else {
            //TODO 有图片无法删除
            Integer num = essayTypeService.deleteByPK(pks);
            if (num >= 1) {
                resultVO.setSuccess(true);
                resultVO.setMsg("查询成功");
                resultVO.setResultData(num);
            } else {
                resultVO.setSuccess(false);
                resultVO.setMsg("删除失败");
            }
        }
        return resultVO;
    }


    @GetMapping(value = "retrieve")
    public RequestResultVO retrieve() throws Exception {
        List<EssayTypeVO> essayTypeVOs = essayTypeService.retrieveAll();
        TreeVO tree = TreeVO.listToTreeNode(essayTypeVOs);
        RequestResultVO resultVO = new RequestResultVO();
        if (tree != null) {
            resultVO.setSuccess(true);
            resultVO.setMsg("查询成功");
            resultVO.setResultData(tree);
        } else {
            resultVO.setSuccess(false);
            resultVO.setMsg("数据为空");
        }

        return resultVO;
    }

    @ApiOperation(value = "移动端接口，分页获取设备类型列表")
    @GetMapping("/retrieveMobile")
    //返回带有图片路径的datatable数据
    public PageResultVO retrieveMobile(EssayTypeVO essayTypeVO) {
        List<EssayTypeVO> result = essayTypeService.retrieveAllWithPage(essayTypeVO);
        Long count = essayTypeService.retrieveAllCount(essayTypeVO);
        PageResultVO pageResultVO = new PageResultVO();
        pageResultVO.setSuccess(true);
        pageResultVO.setMsg("查询成功");
        pageResultVO.setResultData(result);
        pageResultVO.setStart(essayTypeVO.getStart());
        pageResultVO.setLength(essayTypeVO.getLength());
        pageResultVO.setOrderColumnName(essayTypeVO.getOrderColumnName());
        pageResultVO.setOrderDir(essayTypeVO.getOrderDir());
        pageResultVO.setTotal(count + "");
        return pageResultVO;
    }
}


