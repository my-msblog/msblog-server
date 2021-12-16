package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/12/16
 */
@Data
@ToString
public class ArticleCategoryVO {
    private Long id;
    private String title;
    private String cover;
    private String typeName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;
    private List<TagVO> tagVOList;
}