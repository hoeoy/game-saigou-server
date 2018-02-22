package com.houoy.game.saigou.vo;

import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户每期下注概况
 */
@Data
@NoArgsConstructor
public class UserIncomeVO extends IncomeVO {
    @ApiModelProperty(required = false, hidden = true)
    private String pk_user;

    @ApiModelProperty(required = false, hidden = true)
    private String user_code;
}
