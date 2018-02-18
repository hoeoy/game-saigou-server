package com.houoy.game.saigou.core;

import org.springframework.util.StringUtils;

/**
 * Created by andyzhao on 2/18/2018.
 */
public class BetType {
    public final static String big = "big";
    public final static String little = "little";
    public final static String odd = "odd";
    public final static String even = "even";
    public final static String n1 = "n1";
    public final static String n2 = "n2";
    public final static String n3 = "n3";
    public final static String n4 = "n4";
    public final static String n5 = "n5";
    public final static String n6 = "n6";
    public final static String n7 = "n7";
    public final static String n8 = "n8";
    public final static String n9 = "n9";
    public final static String n10 = "n10";

    public static String getDesc(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }

        switch (type) {
            case big:
                return "大";

            case little:
                return "小";

            case odd:
                return "单";

            case even:
                return "双";
            default:
                type = "n" + type;
                switch (type) {
                    case n1:
                        return "1号狗";
                    case n2:
                        return "2号狗";
                    case n3:
                        return "3号狗";
                    case n4:
                        return "4号狗";
                    case n5:
                        return "5号狗";
                    case n6:
                        return "6号狗";
                    case n7:
                        return "7号狗";
                    case n8:
                        return "8号狗";
                    case n9:
                        return "9号狗";
                    case n10:
                        return "10号狗";
                }
                break;
        }
        return null;
    }
}
