package com.ms.blogserver.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhh
 * @time: 2021/10/18
 */
@Data
@Component
@ConfigurationProperties(prefix = "data.log")
public class LogConfig {

    private String url;

    public static LogConfig INSTANCE = new LogConfig();

}
