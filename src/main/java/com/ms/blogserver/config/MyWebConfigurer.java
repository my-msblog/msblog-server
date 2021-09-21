package com.ms.blogserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {
    /**
     * 跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:5478")
                .allowedOrigins("http://localhost:5477")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(36000L)
                .allowedHeaders("*");
    }

}
