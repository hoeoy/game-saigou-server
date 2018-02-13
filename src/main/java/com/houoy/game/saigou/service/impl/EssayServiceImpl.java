package com.houoy.game.saigou.service.impl;


import com.houoy.game.saigou.dao.EssayMapper;
import com.houoy.game.saigou.service.EssayService;
import com.houoy.game.saigou.vo.EssayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017-02-21.
 */
@Service("essayService")
@Transactional
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayMapper essayMapper;

    @Override
    public List<EssayVO> retrieveAll() {
        return essayMapper.retrieveAll();
    }

    @Override
    public List<EssayVO> retrieveByPK(String pk) {
        return essayMapper.retrieveByPK(pk);
    }

    @Override
    public Long retrieveAllCount(EssayVO vo) throws RuntimeException {
        return essayMapper.retrieveAllCount(vo);
    }

    @Override
    public List<EssayVO> retrieveAllWithPage(EssayVO vo) throws RuntimeException {
        return essayMapper.retrieveAllWithPage(vo);
    }

    @Override
    public Integer saveByVO(EssayVO vo) {
        return essayMapper.saveByVO(vo);
    }

    @Override
    public Integer updateByVO(EssayVO vo) {
        return essayMapper.updateByVO(vo);
    }

    @Override
    public Integer deleteByPK(String pk) {
        return essayMapper.deleteByPK(pk);
    }

    @Override
    public Integer deleteByPK(List<String> pks) {
        return essayMapper.deleteByPKs(pks);
    }

    @Override
    public List<EssayVO> retrieveByParentPK(List<String> pks) {
        return null;
    }
}
