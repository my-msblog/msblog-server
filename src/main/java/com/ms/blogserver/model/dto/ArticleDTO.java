package com.ms.blogserver.model.dto;

import com.ms.blogserver.core.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -3905677781223275861L;
    private Long id;
    private Integer type;
    private LocalDateTime time;
    private Long writerId;
}
