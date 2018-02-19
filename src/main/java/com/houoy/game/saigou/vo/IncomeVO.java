package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每期收益记录表
 */
@Data
@NoArgsConstructor
public class IncomeVO extends SuperVO {
    @ApiModelProperty(required = false, hidden = true)
    private String pk_income;
    @ApiModelProperty(value = "某期的pk", example = "23", hidden = false)
    private String pk_period;
    @ApiModelProperty(value = "单，下注积分总数", example = "2", hidden = false)
    private Long bet_odd;
    @ApiModelProperty(value = "双，下注积分总数", example = "2", hidden = false)
    private Long bet_even;
    @ApiModelProperty(value = "大，下注积分总数", example = "2", hidden = false)
    private Long bet_big;
    @ApiModelProperty(value = "小，下注积分总数", example = "2", hidden = false)
    private Long bet_little;
    @ApiModelProperty(value = "1，下注积分总数", example = "2", hidden = false)
    private Long bet_1;
    @ApiModelProperty(value = "2，下注积分总数", example = "2", hidden = false)
    private Long bet_2;
    @ApiModelProperty(value = "3，下注积分总数", example = "2", hidden = false)
    private Long bet_3;
    @ApiModelProperty(value = "4，下注积分总数", example = "2", hidden = false)
    private Long bet_4;
    @ApiModelProperty(value = "5，下注积分总数", example = "2", hidden = false)
    private Long bet_5;
    @ApiModelProperty(value = "6，下注积分总数", example = "2", hidden = false)
    private Long bet_6;
    @ApiModelProperty(value = "7，下注积分总数", example = "2", hidden = false)
    private Long bet_7;
    @ApiModelProperty(value = "8，下注积分总数", example = "2", hidden = false)
    private Long bet_8;
    @ApiModelProperty(value = "9，下注积分总数", example = "2", hidden = false)
    private Long bet_9;
    @ApiModelProperty(value = "10，下注积分总数", example = "2", hidden = false)
    private Long bet_10;

    @ApiModelProperty(value = "1狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_1;
    @ApiModelProperty(value = "2狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_2;
    @ApiModelProperty(value = "3狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_3;
    @ApiModelProperty(value = "4狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_4;
    @ApiModelProperty(value = "5狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_5;
    @ApiModelProperty(value = "6狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_6;
    @ApiModelProperty(value = "7狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_7;
    @ApiModelProperty(value = "8狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_8;
    @ApiModelProperty(value = "9狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_9;
    @ApiModelProperty(value = "10狗赢，庄家盈利", example = "2", hidden = false)
    private Long win_10;

    @ApiModelProperty(value = "庄家赔率", example = "2", hidden = false)
    private Double odds;
    @ApiModelProperty(value = "大小单双赔率", example = "2", hidden = false)
    private Double rateTwo;
    @ApiModelProperty(value = "第一名赔率", example = "2", hidden = false)
    private Double rateNum;
    @ApiModelProperty(value = "下注总金额", example = "3", hidden = false)
    private Long total_bet;
    @ApiModelProperty(value = "收益总金额", example = "3", hidden = false)
    private Long total_win;
    @ApiModelProperty(value = "最终开奖的号码", example = "3", hidden = false)
    private Integer win_num;

    @Override
    public String getPKField() {
        return "pk_income";
    }

    @Override
    public String getTableName() {
        return "game_saigou_income";
    }

    @Override
    public Object getPKValue() {
        return pk_income;
    }

    //计算下注总金额
    public Long calcTotal_bet() {
        return bet_odd + bet_even + bet_big + bet_little +
                bet_1 + bet_2 + bet_3 + bet_4 + bet_5 + bet_6 + bet_7 + bet_8 + bet_9 + bet_10;
    }
}
