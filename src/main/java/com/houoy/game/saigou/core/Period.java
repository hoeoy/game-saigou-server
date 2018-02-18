package com.houoy.game.saigou.core;

import com.houoy.game.saigou.vo.PeriodAggVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by andyzhao on 2/18/2018.
 */
@Data
@NoArgsConstructor
public class Period {
    private PeriodAggVO periodAggVO;
    private TimeType timeType;
}