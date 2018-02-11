package com.houoy.game.saigou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录配置
 */
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {

    public static final String Default_Session_Key = "user";

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

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/api/login/**");
        addInterceptor.excludePathPatterns("/**");

        // addInterceptor.excludePathPatterns("/index**");

        // 拦截配置
        //addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            String sessionID = session.getId();
            Object mes = session.getAttribute(Default_Session_Key);

            if (session.getAttribute(Default_Session_Key) != null)
                return true;

            // 跳转登录
            // String url = "/login";
            //  response.sendRedirect(url);
            return false;
        }
    }
}