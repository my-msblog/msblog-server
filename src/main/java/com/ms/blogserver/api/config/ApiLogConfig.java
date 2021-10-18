package com.ms.blogserver.api.config;

import com.ms.blogserver.constant.contexts.ErrorContexts;
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
public class ApiLogConfig {

    private String url;

    public static ApiLogConfig INSTANCE = new ApiLogConfig();

}
