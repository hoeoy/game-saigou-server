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
    @ApiModelProperty(value = "编码", example = "B20180213120", hidden = false)
    private String period_code;
    @ApiModelProperty(value = "描述", example = "B20180213120", hidden = false)
    private String period_desc;
    @ApiModelProperty(value = "期数", example = "20180213120", hidden = false)
    private String period;
    @ApiModelProperty(value = "开盘时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_start_time;
    @ApiModelProperty(value = "封盘时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_block_time;
    @ApiModelProperty(value = "开奖时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_show_time;
    @ApiModelProperty(value = "本期结束时间", example = "2018-02-14 13:35:00", hidden = false)
    private String period_stop_time;
    @ApiModelProperty(value = "第一名的号码是单数还是双数,1是单数，2是双数", example = "2", hidden = false)
    private Integer odd_even;
    @ApiModelProperty(value = "第一名的号码是小还是大,1是小，2是大", example = "2", hidden = false)
    private Integer little_big;
    //格式参考liveVO
    @ApiModelProperty(value = "描述动画的json", example = "{}", hidden = false)
    private String animation;
    @ApiModelProperty(value = "第1名的号码", example = "10", hidden = false)
    private Integer f1;
    @ApiModelProperty(value = "第2名的号码", example = "2", hidden = false)
    private Integer f2;
    @ApiModelProperty(value = "第3名的号码", example = "1", hidden = false)
    private Integer f3;
    @ApiModelProperty(value = "第4名的号码", example = "8", hidden = false)
    private Integer f4;
    @ApiModelProperty(value = "第5名的号码", example = "9", hidden = false)
    private Integer f5;
    @ApiModelProperty(value = "第6名的号码", example = "3", hidden = false)
    private Integer f6;
    @ApiModelProperty(value = "第7名的号码", example = "4", hidden = false)
    private Integer f7;
    @ApiModelProperty(value = "第8名的号码", example = "5", hidden = false)
    private Integer f8;
    @ApiModelProperty(value = "第9名的号码", example = "7", hidden = false)
    private Integer f9;
    @ApiModelProperty(value = "第10名的号码", example = "6", hidden = false)
    private Integer f10;

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
