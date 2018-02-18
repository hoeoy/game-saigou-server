package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下注明细表
 */
@Data
@NoArgsConstructor
public class BetDetailRecordVO extends SuperVO {
    @ApiModelProperty(required = false, hidden = true)
    private String pk_bet;
    @ApiModelProperty(value = "编码", example = "name", hidden = false)
    private String bet_code;
    @ApiModelProperty(value = "描述", example = "name", hidden = false)
    private String bet_desc;
    @ApiModelProperty(value = "账号，用户pk", example = "name", hidden = false)
    private String pk_user;
    @ApiModelProperty(value = "期数pk", example = "name", hidden = false)
    private String pk_period;
    @ApiModelProperty(value = "下注项目", example = "大big小little单odd双even，数字1-10", hidden = false)
    private String bet_item;
    @ApiModelProperty(value = "下注数量,单位是分（人民币）", example = "1200,12元", hidden = false)
    private Integer bet_money;
    @ApiModelProperty(value = "是否中奖", example = "1为中奖,", hidden = false)
    private Integer win;

    //冗余字段
//    @ApiModelProperty(required = false, hidden = true)
    @ApiModelProperty(value = "用户名称", example = "name", hidden = false)
    private String user_name;
    @ApiModelProperty(value = "期数编码", example = "name", hidden = false)
    private String period_code;

    @Override
    public String getPKField() {
        return "pk_bet";
    }

    @Override
    public String getTableName() {
        return "game_saigou_bet";
    }

    @Override
    public Object getPKValue() {
        return pk_bet;
    }

}
