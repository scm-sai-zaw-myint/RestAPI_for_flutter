package com.scm.flutterdemoapi.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    HeaderHandlers handler;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handler);
    }
}
