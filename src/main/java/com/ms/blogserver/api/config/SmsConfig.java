package com.ms.blogserver.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/28
 */
@Component
public class SmsConfig {

    public static SmsConfig Api = new SmsConfig();

    private static String host;
    private static String path;
    private static String method;
    private static String appcode;
    private static Integer codeLength;

    @Value(value = "${sms.host}")
    public void setHost(String host) {
        SmsConfig.host = host;
    }

    @Value(value = "${sms.path}")
    public void setPath(String path) {
        SmsConfig.path = path;
    }

    @Value(value = "${sms.method}")
    public void setMethod(String method) {
        SmsConfig.method = method;
    }

    @Value(value = "${sms.appcode}")
    public void setAppcode(String appcode) {
        SmsConfig.appcode = appcode;
    }

    @Value(value = "${sms.code-length}")
    public void setCodeLength(Integer codeLength) {
        SmsConfig.codeLength = codeLength;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getAppcode() {
        return appcode;
    }

    public Integer getCodeLength() {
        return codeLength;
    }
}
