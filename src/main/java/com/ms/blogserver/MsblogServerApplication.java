package com.ms.blogserver;


import com.ms.blogserver.api.config.ServerConfig;
import com.ms.blogserver.config.LogInterceptorAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@SpringBootApplication
@Slf4j
@EnableCaching
@EnableScheduling
public class MsblogServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MsblogServerApplication.class);
    }

    public static void main(String[] args) {
        try{
            SpringApplication.run(MsblogServerApplication.class, args);
            log.info("http://localhost:{}/swagger-ui/index.html",ServerConfig.Api.getPort());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherRegistration() {
        return new ServletRegistrationBean<>(dispatcherServlet());
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        return new LogInterceptorAdapter();
    }

}
