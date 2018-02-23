package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每日庄家收益状况总和
 */
@Data
@NoArgsConstructor
public class IncomeAggVO extends SuperVO {
    @ApiModelProperty(value = "玩家今日截止当前秒总下注", example = "10000", hidden = false)
    private String bet;
    @ApiModelProperty(value = "玩家今日截止当前秒总盈利", example = "100", hidden = false)
    private String win;
    @ApiModelProperty(value = "日期", example = "2018-02-18", hidden = false)
    private String date;

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
