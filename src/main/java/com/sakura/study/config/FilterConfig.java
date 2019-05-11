package com.sakura.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class FilterConfig  implements WebMvcConfigurer {
    @Autowired
    InterceptorHttp interceptorHttp;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(interceptorHttp).addPathPatterns("/**");
    }
}
