package com.ms.blogserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {

    /**
     * 允许跨越的url
     */
    private final String[] origin = {
            "http://127.0.0.1:5478",
            "http://localhost:5478",
            "http://127.0.0.1:5477",
            "http://localhost:5477",
            "http://192.168.0.129:5477"
    };
    /**
     * 跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(origin)
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(36000L)
                .allowedHeaders("*");
    }

    /**
     * swagger-ui 404
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/public");
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
