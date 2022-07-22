package com.ms.blogserver.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2022/7/21
 */
@Data
public class ArticleEditVO implements Serializable {
    private static final long serialVersionUID = -1943419268979340312L;
    private String title;
    private String content;
    private String contentMd;
    private String cover;
    private Integer type;
    private List<TagVO> tags;
}
