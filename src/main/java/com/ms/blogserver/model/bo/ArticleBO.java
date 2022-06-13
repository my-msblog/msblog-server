package com.ms.blogserver.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.blogserver.model.vo.TagVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/4
 */
@Data
public class ArticleBO implements Serializable {
    private static final long serialVersionUID = 4110106224293694597L;
    private Long id;
    private String title;
    private String content;
    private String cover;
    private Integer type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String typeName;
    private List<TagVO> tagVOList;
}
