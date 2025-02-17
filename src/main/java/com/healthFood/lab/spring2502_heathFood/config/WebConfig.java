
package com.healthFood.lab.spring2502_heathFood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/usr/diagnosis/DiagnosisResult"); // ✅ 보호할 URL 패턴 지정

        registry.addInterceptor(new Interceptor())
                .addPathPatterns("/usr/myPage/mypage");
    }
}

