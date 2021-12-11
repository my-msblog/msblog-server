package com.ms.blogserver.model.vo;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/11
 */
@Data
@ToString
public class ArchiveVO {
    private Long id;
    private LocalDateTime timestamp;
    private String context;
}
