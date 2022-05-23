package com.ms.blogserver.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.blogserver.core.base.BaseVO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2022/4/8
 */
@Data
public class ArticleRecommendVO implements BaseVO {
    private Long id;
    private String title;
    private String cover;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;
}
