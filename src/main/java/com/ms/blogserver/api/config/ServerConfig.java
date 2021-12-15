package com.ms.blogserver.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/15
 */
@Component
public class ServerConfig {

    private static String port;

    private static String name;

    public static ServerConfig Api = new ServerConfig();

    @Value(value="${test.name}")
    public void setName(String name) {
        ServerConfig.name = name;
    }
    @Value(value="${server.port}")
    public void setPort(String port) {
        ServerConfig.port = port;
    }

    public String getName() {
        return name;
    }

    public String getPort() {
        return port;
    }
}
