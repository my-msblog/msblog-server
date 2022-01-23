package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/23
 */
@Data
@ToString
public class CommentSubmitDTO {
    private Long commentId;
    private String context;
    private String replyTime;
}
