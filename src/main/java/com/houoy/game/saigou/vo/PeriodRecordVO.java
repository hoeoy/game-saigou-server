package com.houoy.game.saigou.vo;

import com.alibaba.fastjson.JSON;
import com.houoy.common.vo.SuperVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 期数记录表
 */
@Data
@NoArgsConstructor
public class PeriodRecordVO extends SuperVO {
    private static final Log logger = LogFactory.getLog(PeriodRecordVO.class);

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

    //生成名次和动画
    public Boolean calcRankAndAnimation(int winNum) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (winNum < 1 || winNum > 10) {
            logger.error("winNum数值异常,winNum=" + winNum);
        } else {
            LiveVO liveVO = new LiveVO();
            //1-8 秒  每秒每组数加50px左右
            int interval = 50;//每秒间隔
            int range = 10;//上下10px摆动
            for (int i = 1; i <= 8; i++) {
                liveVO.getM1().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM2().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM3().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM4().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM5().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM6().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM7().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM8().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM9().add(i * interval - range + new Random().nextInt(range * 2));
                liveVO.getM10().add(i * interval - range + new Random().nextInt(range * 2));
            }

            //生成名次List
            ArrayList<Integer> sequence = new ArrayList();
            for (int i = 1; i <= range; i++) {
                sequence.add(i);
            }
            sequence.remove(winNum - 1);//取出第一名
            randomSequence(sequence);//乱序
            sequence.add(winNum);//第一名加到最后
            Collections.reverse(sequence);//翻转，第一名在第一个位置

            //9-11秒  每秒每组数加200px左右
            interval = 200;
            for (int i = 1; i <= 3; i++) {
                for (int m = 1; m <= 10; m++) {
                    Integer order = sequence.indexOf(m);//取得名次

                    ((List<Integer>) MethodUtils.invokeMethod(liveVO, "getM" + (m), null))
                            .add(400 + i * interval + (10 - order) * 10);
                }
            }


//        String animationJson = "{" +
//                "\"m1\": [50, 100, 150, 200, 250, 300, 350, 400, 500, 645]," +
//                "\"m2\": [60, 110, 130, 220, 250, 280, 360, 400, 520, 605]," +
//                "\"m3\": [50, 100, 140, 200, 260, 270, 370, 420, 530, 750]," +
//                "\"m4\": [30, 100, 150, 200, 270, 310, 380, 400, 540, 730]," +
//                "\"m5\": [50, 100, 160, 190, 250, 300, 390, 400, 500, 620]," +
//                "\"m6\": [90, 130, 170, 200, 280, 260, 320, 400, 560, 610]," +
//                "\"m7\": [50, 100, 130, 170, 250, 310, 380, 400, 500, 600]," +
//                "\"m8\": [60, 100, 150, 200, 250, 330, 370, 400, 580, 690]," +
//                "\"m9\": [50, 100, 130, 180, 290, 330, 330, 400, 500, 625]," +
//                "\"m10\": [50, 100, 150, 200, 250, 300, 320, 400, 500, 650]" +
//                "}";
            setF1(sequence.get(0));
            setF2(sequence.get(1));
            setF3(sequence.get(2));
            setF4(sequence.get(3));
            setF5(sequence.get(4));
            setF6(sequence.get(5));
            setF7(sequence.get(6));
            setF8(sequence.get(7));
            setF9(sequence.get(8));
            setF10(sequence.get(9));

            String animationJson = JSON.toJSONString(liveVO);
            setAnimation(animationJson);
            if (winNum % 2 == 0) {
                setOdd_even(2);
            } else {
                setOdd_even(1);
            }
            if (winNum > 5) {
                setLittle_big(2);
            } else {
                setLittle_big(1);
            }
        }

        return true;
    }

    /**
     * 对给定数目的自0开始步长为1的数字序列进行乱序
     */
    public void randomSequence(List<Integer> list) {
        Integer no = list.size();
        Random random = new Random();
        for (int i = 0; i < no; i++) {
            int p = random.nextInt(no);
            int tmp = list.get(i);
            list.set(i, list.get(p));
            list.set(p, tmp);
        }
        random = null;
    }

    public static void main(String[] args) {
//        List<Integer> integers = new ArrayList();
//        for (int i = 1; i <= 10; i++) {
//            integers.add(i);
//        }
//        System.out.println(integers);
//        new PeriodRecordVO().randomSequence(integers);
//        System.out.println(integers);

        try {
            PeriodRecordVO p = new PeriodRecordVO();
            p.calcRankAndAnimation(7);
            System.out.println(p);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
