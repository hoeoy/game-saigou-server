package com.houoy.game.saigou.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by andyzhao on 2017/12/3.
 */
@Component
@Configuration
@ConfigurationProperties(prefix = "period")
@EnableConfigurationProperties
@Data
@NoArgsConstructor
public class PeriodConfig {
    private Double odds;//庄家的赔率，确定庄家每盘赢钱的百分比
    private Double rateTwo;//大小单双的赔率
    private Double rateNum;//单独号码的赔率
    private String startTime;//每天几点开始开场
    private String endTime;//每天几点结束关门
    private Integer durationSecond;//每期持续多长时间
    private Integer stopSecond;//每期剩余多长时间开始封盘
    private Integer showSecond;//每期剩余多长时间开始播放动画
}
