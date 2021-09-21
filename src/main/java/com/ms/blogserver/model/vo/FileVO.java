package com.ms.blogserver.model.vo;

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
public class FileVO {
    private String fileName;
    private LocalDateTime time;
}
