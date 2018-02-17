package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 期数记录表
 */
@Data
@NoArgsConstructor
public class PeriodRecordVO extends SuperVO {
    @ApiModelProperty(required = false, hidden = true)
    private String pk_period;
    @ApiModelProperty(value = "编码", example = "name", hidden = false)
    private String period_code;
    @ApiModelProperty(value = "名称", example = "name", hidden = false)
    private String period_name;
    @ApiModelProperty(value = "描述", example = "name", hidden = false)
    private String period_desc;
    @ApiModelProperty(value = "期数", example = "20180213120", hidden = false)
    private String period;
    @ApiModelProperty(value = "开盘时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_start_time;
    @ApiModelProperty(value = "封盘时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_block_time;
    @ApiModelProperty(value = "开奖时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_stop_time;
    @ApiModelProperty(value = "第一名的号码是单数还是双数", example = "1", hidden = false)
    private String odd_even;
    @ApiModelProperty(value = "第一名的号码是小还是大", example = "1", hidden = false)
    private String little_big;
    @ApiModelProperty(value = "第1名", example = "", hidden = false)
    private String f1;
    @ApiModelProperty(value = "第2名", example = "", hidden = false)
    private String f2;
    @ApiModelProperty(value = "第3名", example = "", hidden = false)
    private String f3;
    @ApiModelProperty(value = "第4名", example = "", hidden = false)
    private String f4;
    @ApiModelProperty(value = "第5名", example = "", hidden = false)
    private String f5;
    @ApiModelProperty(value = "第6名", example = "", hidden = false)
    private String f6;
    @ApiModelProperty(value = "第7名", example = "", hidden = false)
    private String f7;
    @ApiModelProperty(value = "第8名", example = "", hidden = false)
    private String f8;
    @ApiModelProperty(value = "第9名", example = "", hidden = false)
    private String f9;
    @ApiModelProperty(value = "第10名", example = "", hidden = false)
    private String f10;

    @Override
    public String getPKField() {
        return "pk_period";
    }

    @Override
    public String getTableName() {
        return "game_saigou_period";
    }

    @Override
    public Object getPKValue() {
        return pk_period;
    }

}
