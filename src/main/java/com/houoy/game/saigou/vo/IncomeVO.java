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
    @ApiModelProperty(value = "单，下注积分总数", example = "200000", hidden = false)
    private Long bet_odd;
    @ApiModelProperty(value = "双，下注积分总数", example = "200000", hidden = false)
    private Long bet_even;
    @ApiModelProperty(value = "大，下注积分总数", example = "200000", hidden = false)
    private Long bet_big;
    @ApiModelProperty(value = "小，下注积分总数", example = "200000", hidden = false)
    private Long bet_little;
    @ApiModelProperty(value = "1，下注积分总数", example = "200000", hidden = false)
    private Long bet_1;
    @ApiModelProperty(value = "2，下注积分总数", example = "200000", hidden = false)
    private Long bet_2;
    @ApiModelProperty(value = "3，下注积分总数", example = "200000", hidden = false)
    private Long bet_3;
    @ApiModelProperty(value = "4，下注积分总数", example = "200000", hidden = false)
    private Long bet_4;
    @ApiModelProperty(value = "5，下注积分总数", example = "200000", hidden = false)
    private Long bet_5;
    @ApiModelProperty(value = "6，下注积分总数", example = "200000", hidden = false)
    private Long bet_6;
    @ApiModelProperty(value = "7，下注积分总数", example = "200000", hidden = false)
    private Long bet_7;
    @ApiModelProperty(value = "8，下注积分总数", example = "200000", hidden = false)
    private Long bet_8;
    @ApiModelProperty(value = "9，下注积分总数", example = "200000", hidden = false)
    private Long bet_9;
    @ApiModelProperty(value = "10，下注积分总数", example = "200000", hidden = false)
    private Long bet_10;

    @ApiModelProperty(value = "1狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_1;
    @ApiModelProperty(value = "2狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_2;
    @ApiModelProperty(value = "3狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_3;
    @ApiModelProperty(value = "4狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_4;
    @ApiModelProperty(value = "5狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_5;
    @ApiModelProperty(value = "6狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_6;
    @ApiModelProperty(value = "7狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_7;
    @ApiModelProperty(value = "8狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_8;
    @ApiModelProperty(value = "9狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_9;
    @ApiModelProperty(value = "10狗赢，庄家盈利", example = "200000", hidden = false)
    private Long win_10;

    @ApiModelProperty(value = "庄家赔率", example = "0.8", hidden = false)
    private Double odds;
    @ApiModelProperty(value = "大小单双赔率", example = "1.9", hidden = false)
    private Double rateTwo;
    @ApiModelProperty(value = "第一名赔率", example = "8", hidden = false)
    private Double rateNum;
    @ApiModelProperty(value = "下注总金额", example = "300000", hidden = false)
    private Long total_bet;
    @ApiModelProperty(value = "收益总金额", example = "90000", hidden = false)
    private Long total_win;
    @ApiModelProperty(value = "最终开奖的号码", example = "3", hidden = false)
    private Integer win_num;
    @ApiModelProperty(value = "开奖方式，0 或者 null 系统自动开奖  1 管理员手动开奖", example = "1", hidden = false)
    private Integer win_type;
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
        Long result = 0l;
        if (bet_odd != null) {
            result = result + bet_odd;
        }
        if (bet_even != null) {
            result = result + bet_even;
        }
        if (bet_big != null) {
            result = result + bet_big;
        }
        if (bet_little != null) {
            result = result + bet_little;
        }

        if (bet_1 != null) {
            result = result + bet_1;
        }
        if (bet_2 != null) {
            result = result + bet_2;
        }
        if (bet_3 != null) {
            result = result + bet_3;
        }
        if (bet_4 != null) {
            result = result + bet_4;
        }
        if (bet_5 != null) {
            result = result + bet_5;
        }
        if (bet_6 != null) {
            result = result + bet_6;
        }
        if (bet_7 != null) {
            result = result + bet_7;
        }
        if (bet_8 != null) {
            result = result + bet_8;
        }
        if (bet_9 != null) {
            result = result + bet_9;
        }
        if (bet_10 != null) {
            result = result + bet_10;
        }
        return result;
    }
}
