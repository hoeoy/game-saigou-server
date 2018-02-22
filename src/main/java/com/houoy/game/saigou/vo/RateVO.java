package com.houoy.game.saigou.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示倍率的vo
 */
@Data
@NoArgsConstructor
public class RateVO {
    public static final String oddsName = "odds";
    public static final String rateTwoName = "rateTwo";
    public static final String rateNumName = "rateNum";

    private Double odds;
    @ApiModelProperty(value = "大小单双的赔率", hidden = false)
    private Double rateTwo;//大小单双的赔率
    @ApiModelProperty(value = "单独号码的赔率", hidden = false)
    private Double rateNum;//单独号码的赔率
}
