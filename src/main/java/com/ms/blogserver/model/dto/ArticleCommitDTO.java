package com.ms.blogserver.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2022/6/30
 */
@Data
public class ArticleCommitDTO implements Serializable {
    private static final long serialVersionUID = 1314161094854596066L;
    private String text;
    private List<Long> tagList;
    private String context;
    private Long category;
    private Long writerId;
    private String cover;
}
