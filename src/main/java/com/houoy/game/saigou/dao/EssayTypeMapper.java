package com.houoy.game.saigou.dao;


import com.houoy.common.mapper.BaseMapper;
import com.houoy.game.saigou.vo.EssayTypeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author andyzhao
 */
@Mapper
public interface EssayTypeMapper extends BaseMapper<EssayTypeVO> {
    List<EssayTypeVO> retrieveByParentPK(List<String> pks);
}
