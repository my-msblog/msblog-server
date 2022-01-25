package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@Data
@ToString
public class CommentItemVO implements BaseVO {
    private Long id;
    private String respondent;
    private List<CommentItemVO> children;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime publishTime;
    private String context;
    private String publisher;
    private Boolean isLike;
    private Integer like;
    private Long articleId;
    private Long parentId;
}
