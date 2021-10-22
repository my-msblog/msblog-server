package com.ms.blogserver.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetCommentDTO extends BaseDTO{
    private Long articleId;

    public GetCommentDTO(Long articleId) {
        this.articleId = articleId;
    }

    public GetCommentDTO() {
    }
}
