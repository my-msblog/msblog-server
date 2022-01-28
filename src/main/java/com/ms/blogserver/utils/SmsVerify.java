package com.ms.blogserver.utils;


import com.ms.blogserver.api.config.SmsConfig;
import com.ms.blogserver.core.exception.ProgramException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 验证码发送
 * @author: zhh
 * @time: 2021/5/31
 */
@Slf4j
public class SmsVerify {

    /**
     * 发送短信
     *
     * @param phone 电话
     * @return {@link Integer}
     */
    public static Integer sendSms(String phone){
        Integer code = RandomUtils.getRandomInt(SmsConfig.Api.getCodeLength());
        Map<String, String> headers = new HashMap<String, String>(12);
        headers.put("Authorization", "APPCODE " + SmsConfig.Api.getAppcode());
        Map<String, String> querys = new HashMap<String, String>(12);
        querys.put("mobile", phone);
        querys.put("param", "code:"+code);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>(12);
        try {
            HttpUtils.doPost(SmsConfig.Api.getHost(), SmsConfig.Api.getPath(), SmsConfig.Api.getMethod(),
                    headers, querys, bodys);
            log.debug("verify code:"+code);
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProgramException("SMS Send Error:"+"服务器异常，请联系管理人员");
        }
    }

}
