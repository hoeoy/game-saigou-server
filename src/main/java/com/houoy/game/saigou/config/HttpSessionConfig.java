package com.houoy.game.saigou.config;//package com.iandtop.jxy.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;
//
///**
// * Created by andyzhao on 2017-03-03.
// * 容器扫描到EnableRedisHttpSession注解，会创建一个springSessionRepositoryFilter。
// * springSessionRepositoryFilter管理HttpSession
// */
//
//@Configuration
////@EnableRedisHttpSession
//public class HttpSessionConfig {
//    //将sessionid存入http头中，代替cookie的方式
//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        return new HeaderHttpSessionStrategy();
//    }
//}
//
//
//
//
//
//
