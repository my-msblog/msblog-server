package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/10
 */
@Data
@AllArgsConstructor
public class LogVO implements BaseVO {
    private String fileName;
    private String time;
    private String context;

    public LogVO() {
    }
}
