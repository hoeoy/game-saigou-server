package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 积分明细表
 */
@Data
@NoArgsConstructor
public class CashFlowVO extends SuperVO {
    @ApiModelProperty(required = false, hidden = true)
    private String pk_cash;
    @ApiModelProperty(value = "积分记录类型，充值(top_up)，结账(with_draw)，下注（bet),中奖(win)", example = "win", hidden = false)
    private String cash_type;
    @ApiModelProperty(value = "账号，用户pk", example = "1", hidden = false)
    private String pk_user;
    @ApiModelProperty(value = "期数pk", example = "3", hidden = false)
    private String pk_period;
    @ApiModelProperty(value = "下注项目pk", example = "主键", hidden = false)
    private String pk_bet;
    @ApiModelProperty(value = "流水金额，单位：分（人民币）", example = "12", hidden = false)
    private Integer money;
    @ApiModelProperty(value = "处理流水金额前总额", example = "1234", hidden = false)
    private Integer total_money_before;
    @ApiModelProperty(value = "处理流水金额后总额", example = "1300", hidden = false)
    private Integer total_money_after;

    //冗余字段
//    @ApiModelProperty(required = false, hidden = true)
    @ApiModelProperty(value = "用户名称", example = "name", hidden = false)
    private String user_code;
    @ApiModelProperty(value = "期数编码", example = "name", hidden = false)
    private String period_code;
    @ApiModelProperty(value = "下注项目", example = "大big小little单odd双even，数字1-10", hidden = false)
    private String bet_item;


    @Override
    public String getPKField() {
        return "pk_cash";
    }

    @Override
    public String getTableName() {
        return "game_saigou_cash";
    }

    @Override
    public Object getPKValue() {
        return pk_cash;
    }

}
