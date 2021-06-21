package com.ms.blogserver.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
@Data
@ToString
@AllArgsConstructor
public class CaptchaVO {
    private String key;
    private String img;


    public CaptchaVO() {
    }
}
