package com.healthFood.lab.spring2502_heathFood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/UploadImage/**")  // 웹에서 접근할 URL 경로
                .addResourceLocations("file:C:/UploadImage/");  // 실제 저장된 폴더 경로
    }
}

