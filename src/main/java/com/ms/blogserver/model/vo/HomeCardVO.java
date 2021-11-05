package com.ms.blogserver.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/5
 */
@Data
@ToString
public class HomeCardVO {
    private Integer article;
    private Integer tag;
    private Integer category;
}
