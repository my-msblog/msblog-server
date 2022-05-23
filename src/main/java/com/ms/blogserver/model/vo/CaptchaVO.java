package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
@Data
@AllArgsConstructor
public class CaptchaVO implements BaseVO {
    private String key;
    private String img;


    public CaptchaVO() {
    }
}
