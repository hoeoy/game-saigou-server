package com.houoy.game.saigou.service;


import com.houoy.common.service.BaseService;
import com.houoy.game.saigou.vo.EssayTypeVO;

import java.util.List;

/**
 * Created by andyzhao on 2017-02-20.
 */
public interface EssayTypeService<T extends EssayTypeVO> extends BaseService<T> {
    List<T> retrieveByParentPK(List<String> pks);
}
