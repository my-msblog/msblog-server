package com.ms.blogserver.constant.contexts;

import com.ms.blogserver.api.config.LogConfig;

import java.io.File;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/9
 */
public interface UrlContexts {

    /**
     * 文件路径斜杠"/"
     */
    String SLASH = File.separator;

    /**
     * 日志存储的相对路径，用于linux正式环境
     */
    String LOG_RELATIVE_URL = "spring-log"+ SLASH +"ms-server";

    /**
     * 日志存储绝对路径，开发环境（windows环境）
     */
    String LOG_ABSOLUTE_URL = "D:"+ SLASH +"myself"+ SLASH +"IdeaProject"+ SLASH +"spring-log"+ SLASH +"ms-server";

    String LOG_URL = LogConfig.INSTANCE.getUrl();

    String HTTPS = "https://";

    String WEBSOCKET = "/websocket";
}
