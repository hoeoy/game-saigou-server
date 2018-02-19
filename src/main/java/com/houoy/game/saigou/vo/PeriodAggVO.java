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
    @ApiModelProperty(value = "当前时间所在开奖期", example = "154", hidden = false)
    private Integer current_num;
    @ApiModelProperty(value = "今日总共有多少期", example = "216", hidden = false)
    private Integer total_num;
    @ApiModelProperty(value = "剩余期数", example = "62", hidden = false)
    private Integer rest_num;
    @ApiModelProperty(value = "当前时间所在开奖期倒计时", example = "102", hidden = false)
    private Integer rest_second;
    @ApiModelProperty(value = "当前时间所在开奖期的开盘时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_start_time;
    @ApiModelProperty(value = "当前时间所在开奖期的封盘时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_block_time;
    @ApiModelProperty(value = "当前时间所在开奖期的开奖时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_show_time;
    @ApiModelProperty(value = "当前时间所在开奖期的开奖时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_stop_time;
    @ApiModelProperty(value = "当前时间所在开奖期的编码", example = "B20180213120", hidden = false)
    private String period_code;

    //冗余字段
    @ApiModelProperty(value = "当前时间所在开奖期的pk", example = "123", hidden = false)
    private String pk_period;

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
