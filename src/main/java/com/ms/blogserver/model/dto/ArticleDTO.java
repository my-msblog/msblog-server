package com.ms.blogserver.model.dto;

import com.ms.blogserver.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ArticleDTO extends BaseDTO {
    private Long id;
    private Integer type;
    private LocalDateTime time;
    private Long writerId;
}
