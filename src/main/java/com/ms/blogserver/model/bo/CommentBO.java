package com.ms.blogserver.model.bo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@Data
public class CommentBO implements Serializable {
    private Long id;
    private String content;
    private Long articleId;
    private String commenter;
    private String respondent;
    private Long commenterId;
    private Long respondentId;
    private Long parentId;
    private Boolean isLike;
    private Integer likes;
    private LocalDateTime createTime;
}
