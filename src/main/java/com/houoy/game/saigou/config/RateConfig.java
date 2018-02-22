package com.houoy.game.saigou.config;

import com.houoy.game.saigou.vo.RateVO;
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
@ConfigurationProperties(prefix = "rate")
@EnableConfigurationProperties
@Data
@NoArgsConstructor
public class RateConfig {
    private RateVO rateVO;
}
