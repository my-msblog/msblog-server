package com.ms.blogserver.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@Data
@ToString
public class ArticleCardVO {
    private Long id;
    private String title;
    private String content;
    private String cover;
    private String typeName;
    private List<TagVO> tagVOList;
}
