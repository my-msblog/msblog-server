package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/30
 */
@Data
@ToString
public class GiveLikesDTO {
    private Long userId;
    private Long commentId;
    private Boolean is;
    private LocalDateTime time;
}
