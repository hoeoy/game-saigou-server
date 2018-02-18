package com.houoy.game.saigou.service.impl;

import com.houoy.game.saigou.dao.EssayTypeMapper;
import com.houoy.game.saigou.service.EssayTypeService;
import com.houoy.game.saigou.vo.EssayTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andyzhao on 2017/2/14 0014.
 */
@Service("essayTypeService")
public class EssayTypeServiceImpl implements EssayTypeService<EssayTypeVO> {

    @Autowired
    private EssayTypeMapper essayTypeMapper;

    @Override
    public List<EssayTypeVO> retrieveAll() {
        return essayTypeMapper.retrieveAll();
    }

    @Override
    public List<EssayTypeVO> retrieveByPK(String pk) {
        return null;
    }

    @Override
    public Long retrieveAllCount(EssayTypeVO vo) throws RuntimeException {
        return essayTypeMapper.retrieveAllCount(vo);
    }

    @Override
    public List<EssayTypeVO> retrieveAllWithPage(EssayTypeVO vo) throws RuntimeException {
        return essayTypeMapper.retrieveAllWithPage(vo);
    }


    @Override
    public Integer saveByVO(EssayTypeVO vo) {
        return essayTypeMapper.saveByVO(vo);
    }

    @Override
    public Integer updateByVO(EssayTypeVO vo) {
        return essayTypeMapper.updateByVO(vo);
    }

    @Override
    public Integer deleteByPK(String pk) {
        return essayTypeMapper.deleteByPK(pk);
    }

    @Override
    public Integer deleteByPK(List<String> pks) {
        return essayTypeMapper.deleteByPKs(pks);
    }


    @Override
    public List<EssayTypeVO> retrieveByParentPK(List<String> pks) {
        return essayTypeMapper.retrieveByParentPK(pks);
    }
}
