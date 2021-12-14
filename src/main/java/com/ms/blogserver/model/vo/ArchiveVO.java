package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String context;
}
