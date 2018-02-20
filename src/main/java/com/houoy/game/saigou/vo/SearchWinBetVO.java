package com.houoy.game.saigou.vo;

import com.houoy.game.saigou.core.BetType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询VO,获取某一期所有中奖的下注记录
 */
@Data
@NoArgsConstructor
public class SearchWinBetVO {
    @ApiModelProperty(value = "期主键", example = "8", hidden = false)
    private String pk_period;//期主键
    @ApiModelProperty(value = "[\"big\",\"odd\",\"7\"]", example = "", hidden = false)
    private List<String> bet_item_array;//"big","odd","7"

    public void initBetItemArray(int i) {
        if (i < 0 || i > 10) {

        } else {
            bet_item_array = new ArrayList();
            if (i % 2 == 0) {//双数
                bet_item_array.add(BetType.even);
            } else {//单数
                bet_item_array.add(BetType.odd);
            }

            if (i > 5) {//大
                bet_item_array.add(BetType.big);
            } else {//小
                bet_item_array.add(BetType.little);
            }

            bet_item_array.add(i + "");
        }
    }
}
