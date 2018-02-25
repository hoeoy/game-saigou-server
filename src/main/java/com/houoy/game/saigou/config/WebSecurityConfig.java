package com.houoy.game.saigou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 登录配置
 */
@Configuration
//@EnableWebMvc
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    //解决bugThe multi-part request contained parameter data (excluding uploaded files) that exceeded the limit for maxPostSize set on the associated connector
//    @Bean
//    EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
//        return (ConfigurableEmbeddedServletContainer container) -> {
//            if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
//                tomcat.addConnectorCustomizers((connector) -> {
//                    connector.setMaxPostSize(10000000); // 10 MB
//                });
//            }
//        };
//    }


    @Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getInterceptor() {
        return new URLInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        InterceptorRegistration addInterceptor = registry.addInterceptor(getInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/api/login/**");
        addInterceptor.excludePathPatterns("/swagger**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}