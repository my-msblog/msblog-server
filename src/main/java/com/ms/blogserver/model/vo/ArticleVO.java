package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
@Data
public class ArticleVO implements BaseVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String title;
    private String content;
    private String contentMd;
    private String writer;
    private String cover;
    private Integer likes;
    private Integer type;
    private String typeName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;
    private Integer wordCount;
    /**
     * 阅读数量
     */
    private Integer reading;
    /**
     * 文章标签
     */
    private List<TagVO> tags;
}
