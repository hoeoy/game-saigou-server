package com.houoy.game.saigou.core;

/**
 * Created by andyzhao on 2/18/2018.
 */
public class CashFlowType {
    public final static String top_up = "top_up";//充值 +
    public final static String with_draw = "with_draw";//结账 -
    public final static String bet = "bet";//下注 -
    public final static String win = "win";//中奖 +

    public static String getDesc(String type) {
        switch (type) {
            case top_up:
                return "充值";

            case with_draw:
                return "结账";

            case bet:
                return "下注";

            case win:
                return "中奖";

        }
        return null;
    }
}
