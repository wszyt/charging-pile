package com.zyt.charging.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    ChargingWebInterceptor chargingWebInterceptor() {
        return new ChargingWebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(chargingWebInterceptor())
                .addPathPatterns ("/**")
                .excludePathPatterns ("/static/**")
                .excludePathPatterns ("/login")
                .excludePathPatterns("/web/login")
                .excludePathPatterns ("/assets/**");
    }
}
