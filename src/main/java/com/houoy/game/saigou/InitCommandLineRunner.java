package com.houoy.game.saigou;

import com.houoy.game.saigou.config.RateConfig;
import com.houoy.game.saigou.service.CacheService;
import com.houoy.game.saigou.vo.RateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * initialization environment
 * Created by andyzhao on 2017/9/8.
 */
@Component
public class InitCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(InitCommandLineRunner.class);

    @Autowired
    private RateConfig rateConfig;

    @Autowired
    private CacheService cacheService;

    @Override
    public void run(String... strings) throws Exception {
        //初始化赔率到redis
        if (StringUtils.isEmpty(cacheService.get(RateVO.oddsName))) {
            cacheService.set(RateVO.oddsName, rateConfig.getRateVO().getOdds() + "");
        }

        if (StringUtils.isEmpty(cacheService.get(RateVO.rateTwoName))) {
            cacheService.set(RateVO.rateTwoName, rateConfig.getRateVO().getRateTwo() + "");
        }

        if (StringUtils.isEmpty(cacheService.get(RateVO.rateNumName))) {
            cacheService.set(RateVO.rateNumName, rateConfig.getRateVO().getRateNum() + "");
        }
    }
}
