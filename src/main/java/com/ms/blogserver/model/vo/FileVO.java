package com.ms.blogserver.model.vo;

import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/20
 */
@Data
@ToString
public class FileVO implements BaseVO {
    private String fileName;
    private LocalDateTime time;
}
