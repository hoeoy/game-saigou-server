package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 今日赛程概况
 */
@Data
@NoArgsConstructor
public class PeriodAggVO extends SuperVO {
    @ApiModelProperty(value = "当前开奖期数", example = "154", hidden = false)
    private Integer current_num;
    @ApiModelProperty(value = "今日总共有多少期", example = "216", hidden = false)
    private Integer total_num;
    @ApiModelProperty(value = "剩余期数", example = "62", hidden = false)
    private Integer rest_num;
    @ApiModelProperty(value = "本期倒计时", example = "102", hidden = false)
    private Integer rest_second;
    @Override
    public String getPKField() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public Object getPKValue() {
        return null;
    }
}
