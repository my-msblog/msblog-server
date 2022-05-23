package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/11
 */
@Data
public class ArchiveVO implements BaseVO {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String context;
}
